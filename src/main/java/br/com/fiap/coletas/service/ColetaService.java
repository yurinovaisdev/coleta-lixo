package br.com.fiap.coletas.service;

import br.com.fiap.coletas.dto.ColetaDto;
import br.com.fiap.coletas.dto.ColetaExibicaoDto;
import br.com.fiap.coletas.exception.ColetaNaoEncotradaException;
import br.com.fiap.coletas.model.Coleta;
import br.com.fiap.coletas.repository.ColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    public ColetaExibicaoDto criar(ColetaDto coletaDto) {
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaDto, coleta);
        Coleta coletaCriada = coletaRepository.save(coleta);

        return new ColetaExibicaoDto(coletaCriada);
    }

    public ColetaExibicaoDto buscarPorNumero(Long numeroColeta) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(numeroColeta);

        if (coletaOptional.isPresent()) {
            return new ColetaExibicaoDto(coletaOptional.get());
        } else {
            throw new ColetaNaoEncotradaException("Coleta não encontrada!");
        }
    }

    public List<ColetaExibicaoDto> listarTodasAsColetas() {
        return coletaRepository
                .findAll()
                .stream()
                .map(ColetaExibicaoDto::new)
                .toList();
    }

    public void excluir(Long numeroColeta) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(numeroColeta);

        if (coletaOptional.isPresent()) {
            coletaRepository.delete(coletaOptional.get());
        } else {
            throw new RuntimeException("Coleta não encontrada!");
        }
    }

    public ColetaExibicaoDto atualizar(ColetaDto coletaDto) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(coletaDto.getNumeroColeta());

        if (coletaOptional.isPresent()) {
            Coleta coleta = new Coleta();
            BeanUtils.copyProperties(coletaDto, coleta);
            Coleta coletaAtualizada = coletaRepository.save(coleta);

            return new ColetaExibicaoDto(coletaAtualizada);

        } else {
            throw new RuntimeException("Coleta não encontrada!");
        }
    }

}













