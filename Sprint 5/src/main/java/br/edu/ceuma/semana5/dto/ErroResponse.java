package br.edu.ceuma.semana5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErroResponse {
    private int status;
    private String error;
    private String message;
    private List<DetalheErro> details;
    private Instant timestamp;

    @Getter
    @AllArgsConstructor
    public static class DetalheErro {
        private String field;
        private String message;
    }
}