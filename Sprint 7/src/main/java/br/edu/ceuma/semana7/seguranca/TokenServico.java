package br.edu.ceuma.semana7.seguranca;
import br.edu.ceuma.semana7.entidade.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class TokenServico {
 private final SecretKey chave; private final long expiracao;
 public TokenServico(@Value("${app.jwt.secret}") String segredo,@Value("${app.jwt.expiration-ms}") long expiracao){
  this.chave=Keys.hmacShaKeyFor(Decoders.BASE64.decode(segredo));this.expiracao=expiracao;
 }
 public String gerar(Usuario u){Date agora=new Date();return Jwts.builder().subject(u.getEmail())
  .claim("userId",u.getId()).claim("perfil",u.getPerfil().name()).issuedAt(agora)
  .expiration(new Date(agora.getTime()+expiracao)).signWith(chave).compact();}
 public String extrairEmail(String token){return Jwts.parser().verifyWith(chave).build().parseSignedClaims(token).getPayload().getSubject();}
 public boolean valido(String token,String email){return extrairEmail(token).equals(email);}
 public long getExpiracaoSegundos(){return expiracao/1000;}
}