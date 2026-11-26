package br.edu.ceuma.semana7.excecao;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.authentication.BadCredentialsException;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;import java.util.LinkedHashMap;import java.util.Map;
@RestControllerAdvice public class TratamentoErros {
 @ExceptionHandler(EmailJaCadastradoException.class) public ResponseEntity<Map<String,Object>> duplicado(EmailJaCadastradoException e){return erro(HttpStatus.CONFLICT,e.getMessage());}
 @ExceptionHandler(BadCredentialsException.class) public ResponseEntity<Map<String,Object>> credenciais(BadCredentialsException e){return erro(HttpStatus.UNAUTHORIZED,"E-mail ou senha inválidos.");}
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,Object>> validacao(MethodArgumentNotValidException e){return erro(HttpStatus.BAD_REQUEST,"Os dados enviados são inválidos.");}
 @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<Map<String,Object>> naoEncontrado(IllegalArgumentException e){return erro(HttpStatus.NOT_FOUND,e.getMessage());}
 private ResponseEntity<Map<String,Object>> erro(HttpStatus s,String m){Map<String,Object> b=new LinkedHashMap<>();b.put("status",s.value());b.put("message",m);b.put("timestamp",Instant.now());return ResponseEntity.status(s).body(b);}
}