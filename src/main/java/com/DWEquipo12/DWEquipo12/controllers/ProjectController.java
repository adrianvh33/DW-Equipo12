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
public class ProjectController {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectController(UserRepository userRepository, ProjectRepository projectRepository){
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    /*
    * Se crea un nuevo proyecto
     */
    @PostMapping("/project") // con un post se pide el cuerpo en formato Json
    Project newProject(@RequestBody Project project){
        List<Date> fechas=new ArrayList<Date>();
        fechas.add(new Date()); // se añade la fecha de inicio a una lista
        project.setFechas(fechas); // se ingresa la lista de fecha al proyecto
        return projectRepository.save(project); // se guarda el proyecto
    }

    /*
    * Se entregan los proyectos por  director
     */
    @GetMapping("/project/byDirector/{directorId}")
    List<Project> directorProject(@PathVariable String directorId){
        List<Project> directorProject = projectRepository.findBydirector(directorId);
        return directorProject;
    }

    /*
    * Se entregan los proyectos por integrante
     */
    @GetMapping("/project/byIntegrante/{integranteId}")
    List<Project> integranteProject(@PathVariable String integranteId){
        List<Project> integranteProject = projectRepository.findByintegrantes(integranteId);
        return integranteProject;
    }

    /*
    * Se agrega un usuario a un proyecto usando los ids
     */
    @GetMapping("/setUser/{projecId}/{userId}")
    Project setUsers(@PathVariable String projecId,@PathVariable String userId){
        Project project = projectRepository.findById(projecId).orElse(null); // se encuentra el proyecto con el id
        List<String> integrantes = new ArrayList<>(project.getIntegrantes()); // se copian los integrantes del proyecto a una lista
        integrantes.add(userId); // se agrega el nuevo id a la lista
        project.setIntegrantes(integrantes); // se guarda la lista en el proyectp
        return projectRepository.save(project); // se guarda el proyecto
    }

    /* TODO
    *   Crear GetMapping que muestre todos los proyectos*/


    /* TODO
     *   Crear GetMapping que muestre un proyecto por id*/
    @GetMapping("/projects/{projectId}")
    Optional<Project> getProjects(@PathVariable String projectId){
        return projectRepository.findById(projectId);
    }


    /*
    * Se edita un proyecto existente
     */
    @PutMapping("/editProject/{id}")
    Project editProject(@PathVariable String id,@RequestBody Project project){
        Project project2 = projectRepository.findById(id).orElse(null);
        if (project.getNombre() != null){
            project2.setNombre(project.getNombre());
        }
        if (project.getIntegrantes() != null){
            project2.setIntegrantes(project.getIntegrantes());
        }
        if (project.getDirector() != null){
            project2.setDirector(project.getDirector());
        }
        if (project.getPresupuesto() != null){
            project2.setPresupuesto(project.getPresupuesto());
        }
        if (project.getObjetivos() != null){
            project2.setObjetivos(project.getObjetivos());
        }
        if (project.getEstado() != null){
            project2.setEstado(project.getEstado());
        }
        if (project.getFechas() != null){
            project2.setFechas(project.getFechas());
        }
        if (project.getNotas_desempeno() != null){
            project2.setNotas_desempeno(project.getNotas_desempeno());
        }
        if (project.getEstadoFase()!= null){
            project2.setEstadoFase(project.getEstadoFase());
        }
        if (project.getAvances()!= null){
            project2.setAvances(project.getAvances());
        }
        return projectRepository.save(project2); // se guarda el proyecto
    }


}
