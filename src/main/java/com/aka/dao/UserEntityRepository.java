package com.aka.dao;

import com.aka.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByMail(String mail);
}
