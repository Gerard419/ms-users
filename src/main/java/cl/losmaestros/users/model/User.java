package cl.losmaestros.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(nullable = false,length = 150,unique = true)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Column(nullable = false,length = 255)
    private String contrasena;
}
