package cl.losmaestros.users.service;

import cl.losmaestros.users.dto.UserRequestDTO;
import cl.losmaestros.users.dto.UserResponseDTO;
import cl.losmaestros.users.exception.UserAlreadyExistsException;
import cl.losmaestros.users.exception.UserNotFoundException;
import cl.losmaestros.users.model.Rol;
import cl.losmaestros.users.model.User;
import cl.losmaestros.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO mapToDTO(User user){
        return new UserResponseDTO(
                user.getNombre(),
                user.getCorreo(),
                user.getRol()
        );
    }
    //PETICIONES GET
    //Obtener todos los usuarios
    public List<UserResponseDTO> getAll(){
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //Obtener todos los usuarios por rol
    public List<UserResponseDTO> getByRol(Rol rol){
        return userRepository.findByRol(rol).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //obtener un usuario por su correo
    public UserResponseDTO getByMail(String correo){
        return userRepository.findByCorreo(correo).map(this::mapToDTO).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
    }

    //PETICION POST
    public UserResponseDTO save(UserRequestDTO dto){
        if(userRepository.findByCorreo(dto.getCorreo()).isPresent()){
            throw new UserAlreadyExistsException("Usuario ya registrado");
        }
        User user = new User(
                dto.getNombre(),
                dto.getCorreo(),
                dto.getRol()
        );
        return mapToDTO(userRepository.save(user));
    }

    //PETICION PUT
    public UserResponseDTO update(String correo, UserRequestDTO dto) {
        return userRepository.findByCorreo(correo).map(existe -> {
            existe.setNombre(dto.getNombre());
            existe.setCorreo(dto.getCorreo());
            existe.setRol(dto.getRol());
            return mapToDTO(userRepository.save(existe));
        }).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado para actualizar"));
    }

    //PETICION DELETE
    public void deleteByCorreo(String correo){
        User user = userRepository.findByCorreo(correo).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado para borrar"));
        userRepository.delete(user);
    }
}
