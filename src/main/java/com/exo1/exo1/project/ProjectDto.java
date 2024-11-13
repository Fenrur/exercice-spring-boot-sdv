package com.exo1.exo1.project;

import com.exo1.exo1.task.TaskDto;
import com.exo1.exo1.user.UserDto;

import java.util.List;
import java.util.Set;

public record ProjectDto(Long id, String name, String description, Set<Long> users, Set<Long> tasks) {
}
