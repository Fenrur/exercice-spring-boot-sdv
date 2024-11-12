package com.exo1.exo1.project;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.task.TaskEntity;
import com.exo1.exo1.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectDatabaseService implements ProjectService {
    
    private final ProjectRepository projectRepository;
    private final Mapper mapper;

    public ProjectDatabaseService(ProjectRepository projectRepository, Mapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void create(String name, String description, List<Long> userIds, List<Long> taskIds) {
        var userEntities = userIds.stream()
                .map(id -> UserEntity.builder().id(id).build())
                .toList();
        
        var taskEntities = taskIds.stream()
                .map(id -> TaskEntity.builder().id(id).build())
                .toList();
        
        var projectEntity = ProjectEntity.builder()
                .name(name)
                .description(description)
                .users(userEntities)
                .tasks(taskEntities)
                .build();
        
        projectRepository.save(projectEntity);
    }

    @Override
    @Transactional
    public ProjectDto get(Long id) {
        var projectEntity = projectRepository.findById(id).orElseThrow();
        
        return mapper.toDto(projectEntity);
    }

    @Override
    @Transactional
    public void update(Long id, String name, String description, List<Long> userIds, List<Long> taskIds) {
        var userEntities = userIds.stream()
                .map(userEntityId -> UserEntity.builder().id(userEntityId).build())
                .toList();
        
        var taskEntities = taskIds.stream()
                .map(taskEntityId -> TaskEntity.builder().id(taskEntityId).build())
                .toList();
        
        var projectEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .users(userEntities)
                .tasks(taskEntities)
                .build();
        
        projectRepository.save(projectEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
