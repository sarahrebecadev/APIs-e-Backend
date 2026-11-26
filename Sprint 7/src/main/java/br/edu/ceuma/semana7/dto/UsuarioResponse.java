package br.edu.ceuma.semana7.dto;
import br.edu.ceuma.semana7.entidade.Usuario;
import br.edu.ceuma.semana7.entidade.Perfil;
public record UsuarioResponse(Long id,String nome,String email,Perfil perfil) {
 public static UsuarioResponse de(Usuario u){return new UsuarioResponse(u.getId(),u.getNome(),u.getEmail(),u.getPerfil());}
}