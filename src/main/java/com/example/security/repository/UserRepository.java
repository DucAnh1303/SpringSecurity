package com.example.security.repository;

import com.example.security.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String userName);

    User findAllById(int id);

    @Query(nativeQuery = true,value = "select * from User u where u.user_name = :userName")
    List<User> getUserName(@Param("userName") String userName);

}
