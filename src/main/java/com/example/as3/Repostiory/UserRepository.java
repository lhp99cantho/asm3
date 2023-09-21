package com.example.as3.Repostiory;

import com.example.as3.Entity.Task;
import com.example.as3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUsername(String username);
}
