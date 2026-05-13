package com.example.taskmanager;

import com.example.taskmanager.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query methods (Spring Data JPA derives implementation)
    List<Task> findByCompleted(Boolean completed);
    
    List<Task> findByTitleContainingIgnoreCase(String keyword);
}