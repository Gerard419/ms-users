package cl.losmaestros.users.dto;

import cl.losmaestros.users.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO{
    private String nombre;
    private String email;
    private Rol rol;
}