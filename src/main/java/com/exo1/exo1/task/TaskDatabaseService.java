package com.exo1.exo1.task;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.project.ProjectEntity;
import com.exo1.exo1.project.ProjectRepository;
import com.exo1.exo1.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskDatabaseService implements TaskService {
    
    private final TaskRepository taskRepository;
    private final Mapper mapper;

    public TaskDatabaseService(TaskRepository taskRepository, Mapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void create(String title, TaskStatut statut, Long projectId, Long userId) {
        var taskEntity = TaskEntity.builder()
                .title(title)
                .statut(statut)
                .project(ProjectEntity.builder().id(projectId).build())
                .user(UserEntity.builder().id(userId).build())
                .build();
        
        taskRepository.save(taskEntity);
    }

    @Override
    @Transactional
    public TaskDto get(Long id) {
        var taskEntity = taskRepository.findById(id).orElseThrow();
        
        return mapper.toTaskDto(taskEntity);
    }

    @Override
    @Transactional
    public TaskDto update(Long id, String title, TaskStatut statut, Long projectId, Long userId) {
        var taskEntity = taskRepository.findById(id).orElseThrow();
        
        taskEntity.setTitle(title);
        taskEntity.setStatut(statut);
        taskEntity.setProject(ProjectEntity.builder().id(projectId).build());
        taskEntity.setUser(UserEntity.builder().id(userId).build());
        
        taskRepository.save(taskEntity);
        
        return mapper.toTaskDto(taskEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
