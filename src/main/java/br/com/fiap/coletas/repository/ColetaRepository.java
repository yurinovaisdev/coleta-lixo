package br.com.fiap.coletas.repository;

import br.com.fiap.coletas.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
