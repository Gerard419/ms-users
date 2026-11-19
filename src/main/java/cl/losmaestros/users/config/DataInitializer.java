package cl.losmaestros.users.config;

import cl.losmaestros.users.model.Rol;
import cl.losmaestros.users.model.User;
import cl.losmaestros.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        if(userRepository.count()>0){
            log.info(">>>DataInititalizer: La BD ya tiene datos, se omite la carga inicial.");
            return;
        }
        log.info(">>> DataInitializer: BD vacia detectada, insertando datos de prueba...");
        userRepository.save(new User("Cliente","cliente@correo.com", Rol.USUARIO));
        userRepository.save(new User("Admin","admin@losmaestros.cl", Rol.ADMIN));
        userRepository.save(new User("Vendedor","vendedor@losmaestros.cl", Rol.VENDEDOR));
        log.info(">>> DataInitializer: Usuarios insertados correctamente");
    }
}
