package com.exo1.exo1.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    
    TaskDto create(String title, TaskStatut statut, Long projectId, Long userId);
    
    TaskDto get(Long id);
    
    TaskDto update(Long id, String title, TaskStatut statut, Long projectId, Long userId);
    
    void delete(Long id);

    Page<TaskDto> list(Pageable pageable);
}
