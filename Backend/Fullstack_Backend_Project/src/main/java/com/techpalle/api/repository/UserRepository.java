package com.techpalle.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techpalle.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
