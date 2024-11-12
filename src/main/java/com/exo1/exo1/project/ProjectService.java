package com.exo1.exo1.project;

import java.util.List;

public interface ProjectService {
    
    void create(String name, String description, List<Long> userIds, List<Long> taskIds);

    ProjectDto get(Long id);
    
    void update(Long id, String name, String description, List<Long> userIds, List<Long> taskIds);
    
    void delete(Long id);
}
