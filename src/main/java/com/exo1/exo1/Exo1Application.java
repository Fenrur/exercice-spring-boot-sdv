package com.exo1.exo1;

import com.exo1.exo1.project.ProjectService;
import com.exo1.exo1.task.ProjectTaskCountRepository;
import com.exo1.exo1.task.TaskService;
import com.exo1.exo1.task.TaskStatut;
import com.exo1.exo1.user.UserDto;
import com.exo1.exo1.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class Exo1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Exo1Application.class, args);
	}
	
	private final ProjectTaskCountRepository repository;

	public Exo1Application(ProjectTaskCountRepository repository) {
		this.repository = repository;
	}

	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info(repository.findAll().toString());
//		var userDto1 = userService.create("John Doe", "johndoe.email.com", Set.of());
//		var userDto2 = userService.create("Eric Dupont", "ericdupont.email.com", Set.of());
//		var userDto3 = userService.create("Alice Martin", "alicemartin.email.com", Set.of());
//
//		var projectDto1 = projectService.create("Project 1", "Description 1", Set.of(userDto1.id(), userDto2.id()), Set.of());
//		var projectDto2 = projectService.create("Project 2", "Description 2", Set.of(userDto2.id(), userDto3.id()), Set.of());
//		var projectDto3 = projectService.create("Project 3", "Description 3", Set.of(userDto1.id()), Set.of());
//
//		var taskDto1 = taskService.create("Task 1", TaskStatut.DONE, projectDto1.id(), userDto1.id());
//		var taskDto2 = taskService.create("Task 2", TaskStatut.IN_PROGRESS, projectDto1.id(), userDto2.id());
//		var taskDto3 = taskService.create("Task 3", TaskStatut.TODO, projectDto2.id(), userDto2.id());
//		var taskDto4 = taskService.create("Task 4", TaskStatut.DONE, projectDto2.id(), userDto3.id());
//		var taskDto5 = taskService.create("Task 5", TaskStatut.TODO, projectDto3.id(), userDto1.id());
//		var taskDto6 = taskService.create("Task 6", TaskStatut.IN_PROGRESS, projectDto3.id(), userDto1.id());
//		var taskDto7 = taskService.create("Task 7", TaskStatut.DONE, projectDto3.id(), userDto1.id());
//		var taskDto8 = taskService.create("Task 8", TaskStatut.TODO, projectDto3.id(), userDto1.id());
	}
}
