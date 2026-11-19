package cl.losmaestros.users.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(nullable = false,length = 150,unique = true)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    public User(String nombre, String correo, Rol rol){
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

}
