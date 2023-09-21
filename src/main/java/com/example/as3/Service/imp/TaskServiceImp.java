package com.example.as3.Service.imp;

import com.example.as3.Entity.Task;
import com.example.as3.Entity.User;
import com.example.as3.Repostiory.TaskRepository;
import com.example.as3.Repostiory.UserRepository;
import com.example.as3.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected UserRepository userRepository;

    @Override
    public Task createOrUpdateTask(Task task, int id) {
        try {
            Optional<User> rs = userRepository.findById(id);
            if (rs.isPresent()) {
                task.setUser(rs.get());
                return taskRepository.save(task);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                return user.get().getListTasks();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task getTaskById(int taskId) {
        try {
            Optional<Task> task = taskRepository.findById(taskId);
            return task.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteTaskById(int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
