package com.DWEquipo12.DWEquipo12.repositories;
import com.DWEquipo12.DWEquipo12.models.Project;
import com.DWEquipo12.DWEquipo12.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository <Project,String> {
    List<Project> findBydirector (Integer director);
    List<Project> findByintegrantes (String integrantes);
}
