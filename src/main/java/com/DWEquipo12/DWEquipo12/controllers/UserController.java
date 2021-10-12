package com.DWEquipo12.DWEquipo12.controllers;

import com.DWEquipo12.DWEquipo12.models.Project;
import com.DWEquipo12.DWEquipo12.models.User;
import com.DWEquipo12.DWEquipo12.repositories.ProjectRepository;
import com.DWEquipo12.DWEquipo12.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public UserController(UserRepository userRepository,ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
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
    @GetMapping("/projec2User/{userId}/{projectId}")
    User projec2User(@PathVariable String userId,@PathVariable String projectId){
        User user = userRepository.findById(userId).orElse(null); // se busca el usuario por su id
        List<String> proyectoL = new ArrayList<>(user.getId_proyectos()); // se crea una lista a la que se le copia los proyectos que el usuario ya tiene
        proyectoL.add(projectId); // Se agrega el nuevo id de pryecto a la lista
        user.setId_proyectos(proyectoL); //Se agrega la lista al usuario
        Project project = projectRepository.findById(projectId).orElse(null);
        List<String> userL = new ArrayList<>(project.getIntegrantes());
        userL.add(userId);
        project.setIntegrantes(userL);
        projectRepository.save(project);
        return userRepository.save(user); // se guarda el usuario
    }


    @PutMapping("/reProject/{userId}/{projectId}")
    User reProject(@PathVariable String userId,@PathVariable String projectId){
        User user = userRepository.findById(userId).orElse(null); // se busca el usuario por su id
        Project project = projectRepository.findById(projectId).orElse(null);
        List<String> userL = new ArrayList<>(project.getIntegrantes());
        List<String> proyectoL = new ArrayList<>(user.getId_proyectos()); // se crea una lista a la que se le copia los proyectos que el usuario ya tiene
        userL.remove(userId);
        proyectoL.remove(projectId); // Se agrega el nuevo id de pryecto a la lista
        project.setIntegrantes(userL);
        user.setId_proyectos(proyectoL); //Se agrega la lista al usuario
        projectRepository.save(project);
        return userRepository.save(user); // se guarda el usuario
    }

    /*
    *Se crea un nuevo usuario
     */
    @PostMapping("/user") // se usa una peticion post para pedir un cuerpo Json con los datos del usuario
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
