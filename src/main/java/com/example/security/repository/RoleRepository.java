package com.example.security.repository;

import com.example.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(nativeQuery = true,value = "select * from Role u where u.role_name = :roleName")
    Role getByRoleName(@Param("roleName") String roleName);
}
