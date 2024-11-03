package br.com.fiap.coletas.dto;

import br.com.fiap.coletas.model.Coleta;
import br.com.fiap.coletas.model.StatusColeta;

import java.time.LocalDate;

public record ColetaExibicaoDto(
        Long numeroColeta,
        String nomeMotorista,
        String veiculoColeta,
        StatusColeta statusColeta,
        LocalDate dataColeta
) {
    public ColetaExibicaoDto(Coleta coleta) {
        this(
                coleta.getNumeroColeta(),
                coleta.getNomeMotorista(),
                coleta.getVeiculoColeta(),
                coleta.getStatusColeta(),
                coleta.getDataColeta()
        );
    }
}
