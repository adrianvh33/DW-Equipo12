package com.DWEquipo12.DWEquipo12.controllers;

import com.DWEquipo12.DWEquipo12.models.User;
import com.DWEquipo12.DWEquipo12.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<Integer> id_proyectos = new ArrayList<>();
        User newUser01 = new User("001",1144088597,"usuario","prueba1","p1@gmai.com"
                                 ,"123456","estudiante"
                                 ,318000, "ingenieralectronica",new Date(),id_proyectos);
        this.userRepository.save(newUser01);

    }
    @GetMapping("/users/{userId}")
    Optional<User> getUsers(@PathVariable String userId){
        return userRepository.findById(userId);
    }

    @GetMapping("/setProject/{userId}/{projecId}")
    User setProject(@PathVariable String userId,@PathVariable String projecId){
        User user = userRepository.findById(userId).orElse(null);
        List<Integer> proyecto = new ArrayList<>(user.getId_proyectos());
        proyecto.add(Integer.parseInt(projecId));
        user.setId_proyectos(proyecto);
        return userRepository.save(user);
    }
}
