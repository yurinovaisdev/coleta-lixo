package services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.ColetaModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroColetasService {

    final ColetaModel coletaModel = new ColetaModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    String idCollect;
    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsCollect(String field, String value){
        switch (field){
            case "nomeMotorista" -> coletaModel.setNomeMotorista(value);
            case "veiculoColeta" -> coletaModel.setVeiculoColeta(value);
            case "statusColeta" -> coletaModel.setStatusColeta(value);
            case "dataColeta" -> coletaModel.setDataColeta(value);
            default -> throw new IllegalStateException("Campo inválido" + field);
        }
    }

    public void createCollect(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(coletaModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodySend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdCollect() {
        idCollect = String.valueOf(gson.fromJson(response.jsonPath().prettify(), ColetaModel.class).getNumeroColeta());
    }

    public void  deleteCollect(String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idCollect);
        response = given()
                .when()
                .delete(url)
                .then()
                .extract()
                .response();

    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido da coleta" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-coleta.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONTokener tokener = new JSONTokener(jsonContent);
        return new JSONObject(tokener);
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {

        // Obter o corpo da resposta como String e converter para JSONObject
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        System.out.println("Response Body: " + response.getBody().asString());

        // Configurar o JsonSchemaFactory e criar o JsonSchema
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        // Converter o JSON de resposta para JsonNode
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        // Validar o JSON de resposta contra o esquema
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);

        return schemaValidationErrors;

    }


}
