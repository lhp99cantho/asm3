package com.example.as3.Controller;

import com.example.as3.Entity.Task;
import com.example.as3.Entity.User;
import com.example.as3.Security.UserSecurity;
import com.example.as3.Service.TaskService;
import com.example.as3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class ApiTask {
    @Autowired
    protected TaskService taskService;

    @Autowired
    protected UserService userService;
    @GetMapping("/")
    public List<Task> getTasks() {
        int userId = userService.getUserIdFromReq();
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return tasks;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> createTask (@RequestBody Task task) {
        int userId = userService.getUserIdFromReq();
        Task rs = taskService.createOrUpdateTask(task, userId);
        return rs != null ? ResponseEntity.ok("Created task.")
                : ResponseEntity.ofNullable("Create task failed.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Task task = taskService.getTaskById(id);
        return task != null ? ResponseEntity.ok(task)
                : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        int userId = userService.getUserIdFromReq();
        Task rs = taskService.createOrUpdateTask(task, userId);
        return rs != null ? ResponseEntity.ok(rs)
                : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Delete user - D
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") int id) {
        boolean rs = taskService.deleteTaskById(id);
        return rs ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
