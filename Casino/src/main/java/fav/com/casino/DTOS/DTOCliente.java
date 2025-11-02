package fav.com.casino.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOCliente {
    private String nombre;
    @NotBlank(message = "El email no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El email no puede estar vacío")
    private String password;
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    private String correoElectronico;
    @NotBlank(message = "El email no puede estar vacío")
    private String pais;
}
