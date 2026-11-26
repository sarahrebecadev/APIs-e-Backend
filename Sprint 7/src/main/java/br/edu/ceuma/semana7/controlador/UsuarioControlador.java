package br.edu.ceuma.semana7.controlador;
import br.edu.ceuma.semana7.dto.UsuarioResponse;import br.edu.ceuma.semana7.entidade.Usuario;import br.edu.ceuma.semana7.servico.UsuarioServico;
import org.springframework.http.ResponseEntity;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/usuarios") public class UsuarioControlador {
 private final UsuarioServico servico;public UsuarioControlador(UsuarioServico servico){this.servico=servico;}
 @GetMapping("/perfil") public ResponseEntity<UsuarioResponse> perfil(@AuthenticationPrincipal Usuario usuario){return ResponseEntity.ok(servico.perfil(usuario.getUsername()));}
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id){servico.excluir(id);return ResponseEntity.noContent().build();}
}