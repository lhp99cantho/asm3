package com.example.as3.Service;

import com.example.as3.Entity.User;
import com.example.as3.Repostiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createOrUpdateUser(User user);
    List<User> getAllUsers();
    User getUserByUsername(String username);
    User getUserById(int id);
    boolean deleteUserById(int id);
    int getUserIdFromReq();
}
