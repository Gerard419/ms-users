package cl.losmaestros.users.dto;


import cl.losmaestros.users.model.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO{

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "El correo no puede estar en blanco")
    private String correo;

    @NotNull(message = "El rol no puede estar nulo")
    private Rol rol;

    @NotBlank(message = "La contrasena no puede estar en blanco")
    private String contrasena;
}
