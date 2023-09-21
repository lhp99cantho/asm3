package com.example.as3.Service;

import com.example.as3.Entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Task createOrUpdateTask(Task task, int id);
    boolean deleteTaskById(int id);
    List<Task> getTasksByUserId(int userId);
    Task getTaskById(int taskId);

}
