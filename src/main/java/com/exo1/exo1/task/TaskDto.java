package com.exo1.exo1.task;

public record TaskDto(Long id, String title, TaskStatut statut, Long projectId, Long userId) {
}
