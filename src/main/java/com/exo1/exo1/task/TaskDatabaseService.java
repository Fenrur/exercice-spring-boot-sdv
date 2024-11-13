package com.exo1.exo1.task;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.project.ProjectRepository;
import com.exo1.exo1.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskDatabaseService implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public TaskDatabaseService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, Mapper mapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TaskDto create(String title, TaskStatut statut, Long projectId, Long userId) {
        var project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found: " + projectId));
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        var taskEntity = TaskEntity.builder()
                .title(title)
                .statut(statut)
                .project(project)
                .user(user)
                .build();

        taskRepository.save(taskEntity);
        
        return mapper.toTaskDto(taskEntity);
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
        var project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found: " + projectId));
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        var taskEntity = TaskEntity.builder()
                .id(id)
                .title(title)
                .statut(statut)
                .project(project)
                .user(user)
                .build();

        taskRepository.save(taskEntity);

        return mapper.toTaskDto(taskEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
    
    @Override
    public Page<TaskDto> list(Pageable pageable) {
        return taskRepository.findAll(pageable).map(mapper::toTaskDto);
    }
}
