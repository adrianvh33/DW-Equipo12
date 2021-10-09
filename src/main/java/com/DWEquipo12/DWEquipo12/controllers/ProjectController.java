package com.DWEquipo12.DWEquipo12.controllers;

import com.DWEquipo12.DWEquipo12.models.Project;
import com.DWEquipo12.DWEquipo12.repositories.ProjectRepository;
import com.DWEquipo12.DWEquipo12.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProjectController {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectController(UserRepository userRepository, ProjectRepository projectRepository){
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/project")
    Project newProject(@RequestBody Project project){
        List<Date> fechas=new ArrayList<Date>();
        fechas.add(new Date());
        project.setFechas(fechas);
        return projectRepository.save(project);
    }

    @GetMapping("/project/byDirector/{directorId}")
    List<Project> directorProject(@PathVariable String directorId){
        List<Project> directorProject = projectRepository.findBydirector(Integer.parseInt(directorId));
        return directorProject;
    }

    @GetMapping("/project/byIntegrante/{integranteId}")
    List<Project> integranteProject(@PathVariable String integranteId){
        List<Project> integranteProject = projectRepository.findByintegrantes(integranteId);
        return integranteProject;
    }

    @GetMapping("/setUser/{projecId}/{userId}")
    Project setUsers(@PathVariable String projecId,@PathVariable String userId){
        Project project = projectRepository.findById(projecId).orElse(null);
        List<String> integrantes = new ArrayList<>(project.getIntegrantes());
        integrantes.add(userId);
        project.setIntegrantes(integrantes);
        return projectRepository.save(project);
    }

}
