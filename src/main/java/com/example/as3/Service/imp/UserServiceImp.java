package com.example.as3.Service.imp;

import com.example.as3.Config.JwtConfig;
import com.example.as3.Entity.User;
import com.example.as3.Repostiory.UserRepository;
import com.example.as3.Security.UserSecurity;
import com.example.as3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JwtConfig jwtConfig;

    @Override
    public User createOrUpdateUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            if (user != null) {
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);
                return userRepository.save(user);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            user = userRepository.getUserByUsername(username);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = userRepository.findAll();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getUserIdFromReq() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        User user = userSecurity.getUser();
        return user.getUserId();
    }

}
