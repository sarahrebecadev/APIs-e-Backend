package br.edu.ceuma.semana7.controlador;
import br.edu.ceuma.semana7.dto.*;import br.edu.ceuma.semana7.servico.UsuarioServico;import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;
@RestController public class AuthControlador {
 private final UsuarioServico servico;public AuthControlador(UsuarioServico servico){this.servico=servico;}
 @PostMapping("/register") public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(servico.registrar(r));}
 @PostMapping("/login") public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest r){return ResponseEntity.ok(servico.login(r));}
}