package com.exo1.exo1.project;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.task.TaskRepository;
import com.exo1.exo1.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectDatabaseService implements ProjectService {
    
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public ProjectDatabaseService(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository, Mapper mapper) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProjectDto create(String name, String description, Set<Long> userIds, Set<Long> taskIds) {
        var userEntities = userIds.stream()
                .map(userId -> userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId)))
                .collect(Collectors.toSet());

        var taskEntities = taskIds.stream()
                .map(taskId -> taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found: " + taskId)))
                .collect(Collectors.toSet());
        
        var projectEntity = ProjectEntity.builder()
                .name(name)
                .description(description)
                .users(userEntities)
                .tasks(taskEntities)
                .build();
        
        projectRepository.save(projectEntity);
        
        return mapper.toProjectDto(projectEntity);
    }

    @Override
    public ProjectDto get(Long id) {
        var projectEntity = projectRepository.findById(id).orElseThrow();
        
        return mapper.toProjectDto(projectEntity);
    }

    @Override
    @Transactional
    public ProjectDto update(Long id, String name, String description, Set<Long> userIds, Set<Long> taskIds) {
        var userEntities = userIds.stream()
                .map(userId -> userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId)))
                .collect(Collectors.toSet());

        var taskEntities = taskIds.stream()
                .map(taskId -> taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found: " + taskId)))
                .collect(Collectors.toSet());
        
        var projectEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .users(userEntities)
                .tasks(taskEntities)
                .build();
        
        projectRepository.save(projectEntity);
        
        return mapper.toProjectDto(projectEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
    
    @Override
    public Page<ProjectDto> list(Pageable pageable) {
        return projectRepository.findAll(pageable).map(mapper::toProjectDto);
    }
}
