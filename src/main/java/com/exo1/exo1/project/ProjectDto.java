package com.exo1.exo1.project;

import com.exo1.exo1.task.TaskDto;
import com.exo1.exo1.user.UserDto;

import java.util.List;

public record ProjectDto(Long id, String name, String description, List<Long> users, List<Long> tasks) {
}
