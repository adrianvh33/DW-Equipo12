package com.DWEquipo12.DWEquipo12.controllers;

import com.DWEquipo12.DWEquipo12.models.Project;
import com.DWEquipo12.DWEquipo12.models.User;
import com.DWEquipo12.DWEquipo12.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<String> id_proyectos = new ArrayList<>();
        User newUser01 = new User("001",1144088597,"usuario","prueba1","p1@gmai.com"
                                 ,"123456","estudiante"
                                 ,318000, "ingenieralectronica",new Date(),id_proyectos);
        this.userRepository.save(newUser01);

    }

    /*
    * Se obtiene un usuario por su id
    */
    @GetMapping("/users/{userId}")
    Optional<User> getUsers(@PathVariable String userId){
        return userRepository.findById(userId);
    }

    /*
    * Se agrega un id de proyecto a un usuario
     */
    @GetMapping("/setProject/{userId}/{projecId}")
    User setProject(@PathVariable String userId,@PathVariable String projecId){
        User user = userRepository.findById(userId).orElse(null); // se busca el usuario por su id
        List<String> proyecto = new ArrayList<>(user.getId_proyectos()); // se crea una lista a la que se le copia los proyectos que el usuario ya tiene
        proyecto.add(projecId); // Se agrega el nuevo id de pryecto a la lista
        user.setId_proyectos(proyecto); //Se agrega la lista al usuario
        return userRepository.save(user); // se guarda el usuario
    }

    /*
    *Se crea un nuevo usuario
     */
    @PostMapping("/newUser") // se usa una peticion post para pedir un cuerpo Json con los datos del usuario
    User newUser(@RequestBody User user){
        user.setFecha_ingreso(new Date()); // se agrega la fecha al usuario
        return userRepository.save(user); // seguarda el usuario
    }

    /* TODO
     *   Crear GetMapping que muestre todos los usuarios*/



    /*
    * se edita un usuario
     */
    @PutMapping("/editUser/{id}")
    User editUser (@PathVariable String id,@RequestBody User user){
        User user2 =  userRepository.findById(id).orElse(null);
        if (user.getNombre() != null){
            user2.setNombre(user.getNombre());
        }
        if (user.getApellido() != null){
            user2.setApellido(user.getApellido());
        }
        if (user.getCorreo() != null){
            user2.setCorreo(user.getCorreo());
        }
        if (user.getPassword() != null){
            user2.setPassword(user.getPassword());
        }
        if (user.getRole() != null){
            user2.setRole(user.getRole());
        }
        if (user.getTelefono() != null){
            user2.setTelefono(user.getTelefono());
        }
        if (user.getFecha_ingreso() != null){
            user2.setFecha_ingreso(user.getFecha_ingreso());
        }
        if (user.getId_proyectos() != null){
            user2.setId_proyectos(user.getId_proyectos());
        }
        return userRepository.save(user2);
    }
}
