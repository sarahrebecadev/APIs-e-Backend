package br.edu.ceuma.semana7.seguranca;
import jakarta.servlet.FilterChain;import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class FiltroJwt extends OncePerRequestFilter {
 private final TokenServico tokens;private final UserDetailsService usuarios;
 public FiltroJwt(TokenServico tokens,UserDetailsService usuarios){this.tokens=tokens;this.usuarios=usuarios;}
 @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String header=req.getHeader("Authorization");
  if(header!=null&&header.startsWith("Bearer ")){try{String token=header.substring(7);String email=tokens.extrairEmail(token);
   if(email!=null&&SecurityContextHolder.getContext().getAuthentication()==null){UserDetails u=usuarios.loadUserByUsername(email);
    if(tokens.valido(token,u.getUsername())){var auth=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
     auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));SecurityContextHolder.getContext().setAuthentication(auth);}}
  }catch(RuntimeException ex){SecurityContextHolder.clearContext();}}
  chain.doFilter(req,res);
 }
}