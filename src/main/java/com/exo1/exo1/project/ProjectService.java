package com.exo1.exo1.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    
    ProjectDto create(String name, String description, Set<Long> userIds, Set<Long> taskIds);

    ProjectDto get(Long id);
    
    ProjectDto update(Long id, String name, String description, Set<Long> userIds, Set<Long> taskIds);
    
    void delete(Long id);

    Page<ProjectDto> list(Pageable pageable);
}
