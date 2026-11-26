package br.edu.ceuma.semana7.servico;
import br.edu.ceuma.semana7.dto.*;import br.edu.ceuma.semana7.entidade.*;import br.edu.ceuma.semana7.excecao.EmailJaCadastradoException;
import br.edu.ceuma.semana7.repositorio.UsuarioRepositorio;import br.edu.ceuma.semana7.seguranca.TokenServico;
import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioServico {
 private final UsuarioRepositorio repo;private final PasswordEncoder encoder;private final AuthenticationManager manager;private final TokenServico tokens;
 public UsuarioServico(UsuarioRepositorio repo,PasswordEncoder encoder,AuthenticationManager manager,TokenServico tokens){this.repo=repo;this.encoder=encoder;this.manager=manager;this.tokens=tokens;}
 @Transactional public UsuarioResponse registrar(RegistroRequest r){if(repo.existsByEmail(r.email().toLowerCase()))throw new EmailJaCadastradoException();
  Usuario u=new Usuario();u.setNome(r.nome());u.setEmail(r.email().toLowerCase());u.setSenhaHash(encoder.encode(r.senha()));u.setPerfil(Perfil.COMUM);
  return UsuarioResponse.de(repo.save(u));}
 public AuthResponse login(LoginRequest r){manager.authenticate(new UsernamePasswordAuthenticationToken(r.email().toLowerCase(),r.senha()));
  Usuario u=repo.findByEmail(r.email().toLowerCase()).orElseThrow();return new AuthResponse(tokens.gerar(u),"Bearer",tokens.getExpiracaoSegundos(),UsuarioResponse.de(u));}
 public UsuarioResponse perfil(String email){return UsuarioResponse.de(repo.findByEmail(email).orElseThrow());}
 public void excluir(Long id){if(!repo.existsById(id))throw new IllegalArgumentException("Usuário não encontrado.");repo.deleteById(id);}
}