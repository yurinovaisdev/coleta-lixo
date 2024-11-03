package br.com.fiap.coletas.exception;

public class ColetaNaoEncotradaException extends RuntimeException {
    public ColetaNaoEncotradaException(String mensagem) {
        super(mensagem);
    }
}
