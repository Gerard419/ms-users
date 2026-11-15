package cl.losmaestros.users.controller;

import cl.losmaestros.users.dto.UserRequestDTO;
import cl.losmaestros.users.dto.UserResponseDTO;
import cl.losmaestros.users.model.Rol;
import cl.losmaestros.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;

    //obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>>getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    //obtener todos los usuarios por su rol
    @GetMapping("rol/{rol}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByRol(@PathVariable Rol rol){
        return ResponseEntity.ok(userService.getByRol(rol));
    }

    //obtener usuario por su correo
    @GetMapping("correo/{correo}")
    public ResponseEntity<UserResponseDTO> getByCorreo(@PathVariable String correo){
        return ResponseEntity.ok(userService.getByMail(correo));
    }
    //crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto){
        return ResponseEntity.status(201).body(userService.save(dto));
    }
    //actualizar un usuario
    @PutMapping("correo/{correo}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String correo, @Valid @RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.update(correo, dto));
    }
    //borrar un usuario por su correo
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable String correo){
        userService.deleteByCorreo(correo);
        return ResponseEntity.noContent().build();
    }
}