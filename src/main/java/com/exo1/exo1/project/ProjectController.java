package com.exo1.exo1.project;

import com.exo1.exo1.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public record ProjectController(ProjectService projectService, Mapper mapper) {
    
    public record CreateProjectRequestBody(String name, String description, Optional<List<Long>> userIds, Optional<List<Long>> taskIds) { }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateProjectRequestBody body) {
        projectService.create(body.name(), body.description(), body.userIds().orElse(List.of()), body.taskIds().orElse(List.of()));
    }
    
    public record GetProjectResponseBody(Long id, String name, String description, List<Long> userIds, List<Long> taskIds) { }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProjectResponseBody get(@PathVariable Long id) {
        var projectDto = projectService.get(id);
        return mapper.toGetProjectResponseBody(projectDto);
    }
    
    public record PutProjectRequestBody(String name, String description, Optional<List<Long>> userIds, Optional<List<Long>> taskIds) { }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PutProjectRequestBody body, @PathVariable Long id) {
        projectService.update(id, body.name(), body.description(), body.userIds().orElse(List.of()), body.taskIds().orElse(List.of()));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
