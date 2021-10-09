package com.DWEquipo12.DWEquipo12.repositories;
import com.DWEquipo12.DWEquipo12.models.Project;
import com.DWEquipo12.DWEquipo12.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository <Project,String> {
    List<Project> findBydirector (String director); // usando la variable director de la clase Project se crea un Query que entrega una lista de proyectos
    List<Project> findByintegrantes (String integrantes); // usando la variable integrantes de la clase Project se crea un Query que entrega una lista de proyectos
}
