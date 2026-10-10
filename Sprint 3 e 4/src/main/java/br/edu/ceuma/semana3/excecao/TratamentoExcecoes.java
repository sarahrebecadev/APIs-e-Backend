package br.edu.ceuma.semana3.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class TratamentoExcecoes {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratarAlunoNaoEncontrado(
            AlunoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", exception.getMessage()));
    }
}
