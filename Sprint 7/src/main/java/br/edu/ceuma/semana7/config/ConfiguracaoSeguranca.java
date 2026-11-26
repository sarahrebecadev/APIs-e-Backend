package br.edu.ceuma.semana7.config;
import br.edu.ceuma.semana7.repositorio.UsuarioRepositorio;import br.edu.ceuma.semana7.seguranca.FiltroJwt;
import org.springframework.beans.factory.annotation.Value;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.http.SessionCreationPolicy;import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;import org.springframework.security.config.Customizer;
@Configuration @EnableMethodSecurity
public class ConfiguracaoSeguranca {
 @Bean public UserDetailsService userDetailsService(UsuarioRepositorio repo){return email->repo.findByEmail(email).orElseThrow(()->new org.springframework.security.core.userdetails.UsernameNotFoundException("Usuário não encontrado."));}
 @Bean public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean public DaoAuthenticationProvider authenticationProvider(UserDetailsService uds,PasswordEncoder encoder){DaoAuthenticationProvider p=new DaoAuthenticationProvider();p.setUserDetailsService(uds);p.setPasswordEncoder(encoder);return p;}
 @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http,FiltroJwt filtro,DaoAuthenticationProvider provider)throws Exception{
  http.csrf(csrf->csrf.disable()).cors(Customizer.withDefaults()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authenticationProvider(provider).authorizeHttpRequests(a->a.requestMatchers("/register","/login","/swagger-ui/**","/v3/api-docs/**","/api-docs").permitAll()
   .requestMatchers(HttpMethod.DELETE,"/usuarios/**").hasRole("ADMIN").anyRequest().authenticated())
   .addFilterBefore(filtro,UsernamePasswordAuthenticationFilter.class);return http.build();}
 @Bean public CommandLineRunner criarAdminInicial(UsuarioRepositorio repo,PasswordEncoder encoder,@Value("${app.admin.email:}")String email,@Value("${app.admin.password:}")String senha){
  return args->{if(email!=null&&!email.isBlank()&&senha!=null&&!senha.isBlank()&&!repo.existsByEmail(email.toLowerCase())){
   var admin=new br.edu.ceuma.semana7.entidade.Usuario();admin.setNome("Administrador");admin.setEmail(email.toLowerCase());admin.setSenhaHash(encoder.encode(senha));admin.setPerfil(br.edu.ceuma.semana7.entidade.Perfil.ADMIN);repo.save(admin);}};
 }
}