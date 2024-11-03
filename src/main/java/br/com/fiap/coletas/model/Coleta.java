package br.com.fiap.coletas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_coletas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_coleta")
    private Long numeroColeta;

    @Column(name = "nome_motorista")
    private String nomeMotorista;

    @Column(name = "veiculo_coleta")
    private String veiculoColeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_coleta")
    private StatusColeta statusColeta;

    @Column(name = "data_coleta")
    private LocalDate dataColeta;

}
