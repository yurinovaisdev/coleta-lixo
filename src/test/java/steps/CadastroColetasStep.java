package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.json.JSONException;
import org.junit.Assert;
import services.CadastroColetasService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroColetasStep {

    CadastroColetasService cadastroColetasService = new CadastroColetasService();
    @Dado("que eu tenha os seguintes dados de coleta:")
    public void queEuTenhaOsSeguintesDadosDeEntrega(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroColetasService.setFieldsCollect(columns.get("campo"), columns.get("valor"));
        }

    }

    @Quando("eu enviar a requisicao para o endpoint {string} para cadastro de coletas")
    public void euEnviarARequisicaoParaOEndpointParaCadastroDeColetas(String endPoint) {
        cadastroColetasService.createCollect(endPoint);
    }

    @Então("o status code deve retornar {int}")
    public void oStatusCodeDeveRetornar(int statusCode) {
        Assert.assertEquals(statusCode, cadastroColetasService.response.statusCode());
    }

    @E("o corpo de resposta de erro de api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDeApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroColetasService.gson.fromJson(
                cadastroColetasService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }

    @Dado("que eu recupere o ID da coleta criado no contexto")
    public void queEuRecupereOIDDaColetaCriadoNoContexto() {
        cadastroColetasService.retrieveIdCollect();
    }

    @Quando("eu enviar a requisicao para com o ID para o endpoint {string} de delecao de coleta")
    public void euEnviarARequisicaoParaComOIDParaOEndpointDeDelecaoDeColeta(String endPoint) {
        cadastroColetasService.deleteCollect(endPoint);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroColetasService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws JSONException, IOException {
        Set<ValidationMessage> validateResponse = cadastroColetasService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
