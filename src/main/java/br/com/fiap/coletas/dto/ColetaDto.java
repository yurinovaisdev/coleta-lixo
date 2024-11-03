package br.com.fiap.coletas.dto;


import br.com.fiap.coletas.model.StatusColeta;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ColetaDto {

    private Long numeroColeta;

    @NotNull(message = "Nome do veículo não pode ser nulo")
    @Size(max = 25, message = "Nome do veiculo deve ter no máximo 25 caracteres")
    private String nomeMotorista;

    @NotNull(message = "Nome do coletador não pode ser nulo")
    @Size(max = 25, message = "Nome do coletador deve ter no máximo 25 caracteres")
    private String veiculoColeta;

    @NotNull(message = "Status da Coleta não pode ser nulo")
    private StatusColeta statusColeta;

    @NotNull(message = "Data de coleta não pode ser nula")
    private LocalDate dataColeta;

}
