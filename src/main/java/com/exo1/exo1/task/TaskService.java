package com.exo1.exo1.task;

public interface TaskService {
    
    void create(String title, TaskStatut statut, Long projectId, Long userId);
    
    TaskDto get(Long id);
    
    TaskDto update(Long id, String title, TaskStatut statut, Long projectId, Long userId);
    
    void delete(Long id);
}
