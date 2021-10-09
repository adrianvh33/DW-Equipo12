package com.equipo12.equipo12.repositories;
import com.equipo12.equipo12.models.Project;
import com.equipo12.equipo12.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository <Project,String> {
    List<Project> findBydirector (Integer director);
    List<Project> findByintegrantes (String integrantes);
}
