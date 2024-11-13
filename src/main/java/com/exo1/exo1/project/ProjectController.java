package com.exo1.exo1.project;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.Page;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/project")
public record ProjectController(ProjectService projectService, Mapper mapper) {
    
    public record CreateProjectRequestBody(String name, String description, Optional<Set<Long>> userIds, Optional<Set<Long>> taskIds) { }
    public record CreateProjectResponseBody(Long id, String name, String description, Set<Long> userIds, Set<Long> taskIds) { }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProjectResponseBody create(@RequestBody CreateProjectRequestBody body) {
        var projectDto = projectService.create(body.name(), body.description(), body.userIds().orElse(Set.of()), body.taskIds().orElse(Set.of()));
        return mapper.toCreateProjectResponseBody(projectDto);
    }
    
    public record GetProjectResponseBody(Long id, String name, String description, Set<Long> userIds, Set<Long> taskIds) { }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProjectResponseBody get(@PathVariable Long id) {
        var projectDto = projectService.get(id);
        return mapper.toGetProjectResponseBody(projectDto);
    }
    
    public record UpdateProjectRequestBody(String name, String description, Optional<Set<Long>> userIds, Optional<Set<Long>> taskIds) { }
    public record UpdateProjectResponseBody(Long id, String name, String description, Set<Long> userIds, Set<Long> taskIds) { }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateProjectResponseBody update(@RequestBody UpdateProjectRequestBody body, @PathVariable Long id) {
        var projectDto = projectService.update(id, body.name(), body.description(), body.userIds().orElse(Set.of()), body.taskIds().orElse(Set.of()));
        return mapper.toUpdateProjectResponseBody(projectDto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<GetProjectResponseBody> list(@PathParam("page") Integer page, @PathParam("size") Integer size) {
        var pageable = Pageable.ofSize(size).withPage(page);
        
        return Page.from(
                projectService
                        .list(pageable)
                        .map(mapper::toGetProjectResponseBody)
        );
    }
}
