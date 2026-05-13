package com.example.taskmanager;

import com.example.taskmanager.Task;
import com.example.taskmanager.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    
    @Autowired  // Constructor injection (recommended)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public Task createTask(Task task) {
        task.setId(null);  // Ensure new task doesn't have existing ID
        task.setCompleted(false);
        return taskRepository.save(task);
    }
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setCompleted(taskDetails.getCompleted());
        return taskRepository.save(existingTask);
    }
    
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
    
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }
    
    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }
}