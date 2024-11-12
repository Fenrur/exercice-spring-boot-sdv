package com.exo1.exo1;

import com.exo1.exo1.project.ProjectController;
import com.exo1.exo1.project.ProjectDto;
import com.exo1.exo1.project.ProjectEntity;
import com.exo1.exo1.task.TaskController;
import com.exo1.exo1.task.TaskDto;
import com.exo1.exo1.task.TaskEntity;
import com.exo1.exo1.user.UserController;
import com.exo1.exo1.user.UserDto;
import com.exo1.exo1.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    
    public TaskDto toTaskDto(TaskEntity taskEntity) {
        return new TaskDto(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getStatut(), taskEntity.getProject().getId(), taskEntity.getUser().getId());
    }

    public TaskController.GetTaskResponseBody toGetTaskResponseBody(TaskDto taskDto) {
        return new TaskController.GetTaskResponseBody(taskDto.id(), taskDto.title(), taskDto.statut(), taskDto.projectId(), taskDto.userId());
    }

    public UserDto toUserDto(UserEntity user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getProjects().stream().map(ProjectEntity::getId).toList());
    }

    public UserController.GetUserResponseBody toGetUserResponseBody(UserDto userDto) {
        return new UserController.GetUserResponseBody(userDto.id(), userDto.name(), userDto.email(), userDto.projectIds());
    }

    public ProjectDto toDto(ProjectEntity projectEntity) {
        return new ProjectDto(projectEntity.getId(), projectEntity.getName(), projectEntity.getDescription(), projectEntity.getUsers().stream().map(UserEntity::getId).toList(), projectEntity.getTasks().stream().map(TaskEntity::getId).toList());
    }

    public ProjectController.GetProjectResponseBody toGetProjectResponseBody(ProjectDto projectDto) {
        return new ProjectController.GetProjectResponseBody(projectDto.id(), projectDto.name(), projectDto.description(), projectDto.users(), projectDto.tasks());
    }
}
