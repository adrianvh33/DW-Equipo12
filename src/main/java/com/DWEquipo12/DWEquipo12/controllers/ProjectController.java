package com.DWEquipo12.DWEquipo12.controllers;

import com.DWEquipo12.DWEquipo12.models.Avance;
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
    private final UserController userController;

    public ProjectController(UserRepository userRepository, ProjectRepository projectRepository,UserController userController){
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.userController = userController;
    }


    /*
    * Se crea un nuevo proyecto
     */
    @PostMapping("/project") // con un post se pide el cuerpo en formato Json
    Project newProject(@RequestBody Project project){
        project.setFecha_inicio(new Date()); // se ingresa la lista de fecha al proyecto
        if(project.getIntegrantes().isEmpty()){
            System.out.println("The List is empty");
        }else{
            List<String> integrantes = new ArrayList<>(project.getIntegrantes());
            if(integrantes != null ){
                integrantes.add(project.getDirector()); // se agrega el nuevo id a la lista
                project.setIntegrantes(integrantes);
            }
        }

        User user = userRepository.findById(project.getDirector()).orElse(null);
        if (user != null){
            if (user.getId_proyectos() == null ){
                List<String> pList = new ArrayList<>();
                pList.add(project.getId_proyecto());
                user.setId_proyectos(pList);
                userRepository.save(user);
            }else {
                List<String> pList = new ArrayList<>(user.getId_proyectos());
                pList.add(project.getId_proyecto());
                user.setId_proyectos(pList);
                userRepository.save(user);
            }
        }
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
        Project project = projectRepository.findById(projecId).orElse(null); // se encuentra el proyecto con el i
        List<String> integrantes = new ArrayList<>(project.getIntegrantes()); // se copian los integrantes del proyecto a una lista
        integrantes.add(userId); // se agrega el nuevo id a la lista
        project.setIntegrantes(integrantes); // se guarda la lista en el proyectp
        return projectRepository.save(project); // se guarda el proyecto
    }

    @GetMapping("/allProjects")
    public @ResponseBody Iterable<Project> getAllProjects() {
        //Esto retorna a JSON o XML with los projects
        return projectRepository.findAll();
    }

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
        if (project.getFecha_inicio() != null){
            project2.setFecha_inicio(project.getFecha_inicio());
        }
        if (project.getFecha_final() != null){
            project2.setFecha_final(project.getFecha_final());
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

    @DeleteMapping("/deleteProject/{id}")
    String deleteProject(@PathVariable String id){
        Project project = projectRepository.findById(id).orElse(null);
        if (project== null){
            return "El projecto no existe";
        }else{
            List<String> integrantes = new ArrayList<>(project.getIntegrantes());
            String projectID = project.getId_proyecto();
            for (String integrante :integrantes){
                userController.reProject(integrante,projectID);
            }
            projectRepository.delete(project);
            return "Proyecto eliminado";
        }
    }

    @PutMapping("/addAdvance/{projectId}")
    Project addAdvance(@PathVariable String projectId,@RequestBody Avance avance){
        Project project = projectRepository.findById(projectId).orElse(null);
        List<String> proyectoL = new ArrayList<>(project.getAvances());
        proyectoL.add(avance.getAvance());
        project.setAvances(proyectoL);
        return projectRepository.save(project);
    }

    @PutMapping("/editAdvance/{projectId}/{indexAvance}")
    Project editAdvance(@PathVariable String projectId,@PathVariable String indexAvance,@RequestBody Avance avance){
        Project project = projectRepository.findById(projectId).orElse(null);
        List<String> proyectoL = new ArrayList<>(project.getAvances());
        proyectoL.set(Integer.parseInt(indexAvance),avance.getAvance());
        project.setAvances(proyectoL);
        return projectRepository.save(project);
    }

    @DeleteMapping("/deleteAdvance/{projectId}/{indexAvance}")
    Project deleteAdvance(@PathVariable String projectId,@PathVariable String indexAvance){
        Project project = projectRepository.findById(projectId).orElse(null);
        List<String> proyectoL = new ArrayList<>(project.getAvances());
        proyectoL.remove(Integer.parseInt(indexAvance));
        project.setAvances(proyectoL);
        return projectRepository.save(project);
    }



}
