package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColetaModel {
    @Expose(serialize = false)
    private int numeroColeta;
    @Expose
    private String nomeMotorista;
    @Expose
    private String veiculoColeta;
    @Expose
    private String statusColeta;
    @Expose
    private String dataColeta;
}