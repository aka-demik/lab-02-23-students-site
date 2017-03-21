package com.aka.dao;

import org.springframework.data.repository.CrudRepository;
import com.aka.entitys.UserEntity;

import javax.jws.soap.SOAPBinding;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByMail(String mail);
}
