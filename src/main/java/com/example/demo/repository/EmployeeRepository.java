package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<User,Integer>{
    @Query(value = "SELECT u.* FROM users u LEFT JOIN roles r ON u.role_id = r.role_id WHERE r.role_id = 1", nativeQuery = true)
    List<User> findUsersByRoleId();
}
