package cl.losmaestros.users.repository;

import cl.losmaestros.users.model.Rol;
import cl.losmaestros.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByRol(Rol rol);
    Optional<User> findByCorreo(String correo);
}
