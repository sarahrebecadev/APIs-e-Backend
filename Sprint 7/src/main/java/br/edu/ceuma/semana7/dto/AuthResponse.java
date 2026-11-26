package br.edu.ceuma.semana7.dto;
public record AuthResponse(String token,String tipo,long expiraEmSegundos,UsuarioResponse usuario) {}