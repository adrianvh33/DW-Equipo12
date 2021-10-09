package com.DWEquipo12.DWEquipo12.repositories;

import com.DWEquipo12.DWEquipo12.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String>{

}
