package br.edu.ceuma.semana5.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlunoRequest {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O formato do e-mail está incorreto.")
    private String email;
    @Min(value = 1, message = "A idade deve ser maior que zero.")
    private int idade;
}