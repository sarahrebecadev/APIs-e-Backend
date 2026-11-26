package br.edu.ceuma.semana5.excecao;

import br.edu.ceuma.semana5.dto.ErroResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(MethodArgumentNotValidException ex) {
        List<ErroResponse.DetalheErro> detalhes = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                detalhes.add(new ErroResponse.DetalheErro(erro.getField(), erro.getDefaultMessage())));
        return resposta(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Os dados fornecidos são inválidos.", detalhes);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> tratarJsonInvalido(HttpMessageNotReadableException ex) {
        return resposta(HttpStatus.BAD_REQUEST, "INVALID_JSON", "O corpo da requisição contém um JSON inválido ou malformado.", List.of());
    }

    @ExceptionHandler(AlunoJaExisteException.class)
    public ResponseEntity<ErroResponse> tratarDuplicidade(AlunoJaExisteException ex) {
        return resposta(HttpStatus.CONFLICT, "RESOURCE_CONFLICT", ex.getMessage(), List.of());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return resposta(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), List.of());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErroResponse> tratarRotaNaoEncontrada(NoResourceFoundException ex) {
        return resposta(HttpStatus.NOT_FOUND, "ROUTE_NOT_FOUND", "A rota solicitada não foi encontrada.", List.of());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponse> tratarIntegridade(DataIntegrityViolationException ex) {
        return resposta(HttpStatus.CONFLICT, "DATA_CONFLICT", "Não foi possível concluir a operação devido a um conflito de dados.", List.of());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroInterno(Exception ex) {
        LOGGER.error("Erro interno não tratado na API.", ex);
        return resposta(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "Erro interno no servidor. Tente novamente mais tarde.", List.of());
    }

    private ResponseEntity<ErroResponse> resposta(HttpStatus status, String codigo, String mensagem,
                                                   List<ErroResponse.DetalheErro> detalhes) {
        ErroResponse corpo = new ErroResponse(status.value(), codigo, mensagem, detalhes, Instant.now());
        return ResponseEntity.status(status).body(corpo);
    }
}