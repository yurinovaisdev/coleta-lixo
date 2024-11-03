package br.com.fiap.coletas.controller;

import br.com.fiap.coletas.dto.ColetaDto;
import br.com.fiap.coletas.dto.ColetaExibicaoDto;
import br.com.fiap.coletas.dto.ErrorResponseDto;
import br.com.fiap.coletas.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    @Autowired
    private ColetaService service;

    @GetMapping("/health")
    public String healthCheck() {
        return "O serviço está UP!";
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid ColetaDto coletaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(error -> {
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.joining("; "));

            return ResponseEntity.badRequest().body(new ErrorResponseDto(errorMessage));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(coletaDto));
    }

    @GetMapping("{numeroColeta}")
    public ResponseEntity<ColetaExibicaoDto> buscarPorNumero(@PathVariable Long numeroColeta){
        return ResponseEntity.ok(service.buscarPorNumero(numeroColeta));
    }

    @GetMapping
    public ResponseEntity<List<ColetaExibicaoDto>> listarTodasAsColetas(){
        return ResponseEntity.ok(service.listarTodasAsColetas());
    }

    @DeleteMapping("{numeroColeta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long numeroColeta){
        service.excluir(numeroColeta);
    }

    @PutMapping
    public ColetaExibicaoDto atualizar(@RequestBody ColetaDto coletaDto){
        return service.atualizar(coletaDto);
    }

}
