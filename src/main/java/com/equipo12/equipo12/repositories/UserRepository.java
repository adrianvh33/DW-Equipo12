package com.equipo12.equipo12.repositories;

import com.equipo12.equipo12.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String>{

}
