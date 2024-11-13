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

import java.util.stream.Collectors;

@Component
public class Mapper {
    
    public TaskDto toTaskDto(TaskEntity taskEntity) {
        return new TaskDto(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getStatut(), taskEntity.getProject().getId(), taskEntity.getUser().getId());
    }

    public TaskController.GetTaskResponseBody toGetTaskResponseBody(TaskDto taskDto) {
        return new TaskController.GetTaskResponseBody(taskDto.id(), taskDto.title(), taskDto.statut(), taskDto.projectId(), taskDto.userId());
    }

    public TaskController.CreateTaskResponseBody toCreateTaskResponseBody(TaskDto taskDto) {
        return new TaskController.CreateTaskResponseBody(taskDto.id(), taskDto.title(), taskDto.statut(), taskDto.projectId(), taskDto.userId());
    }

    public TaskController.UpdateTaskResponseBody toUpdateTaskResponseBody(TaskDto update) {
        return new TaskController.UpdateTaskResponseBody(update.id(), update.title(), update.statut(), update.projectId(), update.userId());
    }

    public UserDto toUserDto(UserEntity user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getProjects().stream().map(ProjectEntity::getId).collect(Collectors.toSet()));
    }

    public UserController.GetUserResponseBody toGetUserResponseBody(UserDto userDto) {
        return new UserController.GetUserResponseBody(userDto.id(), userDto.name(), userDto.email(), userDto.projectIds());
    }

    public UserController.CreateUserResponseBody toCreateUserResponseBody(UserDto userDto) {
        return new UserController.CreateUserResponseBody(userDto.id(), userDto.name(), userDto.email(), userDto.projectIds());
    }

    public UserController.UpdateUserResponseBody toUpdateUserResponseBody(UserDto update) {
        return new UserController.UpdateUserResponseBody(update.id(), update.name(), update.email(), update.projectIds());
    }

    public ProjectDto toProjectDto(ProjectEntity projectEntity) {
        return new ProjectDto(projectEntity.getId(), projectEntity.getName(), projectEntity.getDescription(), projectEntity.getUsers().stream().map(UserEntity::getId).collect(Collectors.toSet()), projectEntity.getTasks().stream().map(TaskEntity::getId).collect(Collectors.toSet()));
    }

    public ProjectController.GetProjectResponseBody toGetProjectResponseBody(ProjectDto projectDto) {
        return new ProjectController.GetProjectResponseBody(projectDto.id(), projectDto.name(), projectDto.description(), projectDto.users(), projectDto.tasks());
    }

    public ProjectController.CreateProjectResponseBody toCreateProjectResponseBody(ProjectDto projectDto) {
        return new ProjectController.CreateProjectResponseBody(projectDto.id(), projectDto.name(), projectDto.description(), projectDto.users(), projectDto.tasks());
    }

    public ProjectController.UpdateProjectResponseBody toUpdateProjectResponseBody(ProjectDto projectDto) {
        return new ProjectController.UpdateProjectResponseBody(projectDto.id(), projectDto.name(), projectDto.description(), projectDto.users(), projectDto.tasks());
    }
}
