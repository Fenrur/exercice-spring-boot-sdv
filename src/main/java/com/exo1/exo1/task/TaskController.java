package com.exo1.exo1.task;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.Page;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public record TaskController(TaskService taskService, Mapper mapper) {

    public record CreateTaskRequestBody(String title, TaskStatut statut, Long projectId, Long userId) { }
    public record CreateTaskResponseBody(Long id, String title, TaskStatut statut, Long projectId, Long userId) { }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTaskResponseBody create(@RequestBody CreateTaskRequestBody body) {
        var taskDto = taskService.create(body.title(), body.statut(), body.projectId(), body.userId());
        return mapper.toCreateTaskResponseBody(taskDto);
    }
    
    public record GetTaskResponseBody(Long id, String title, TaskStatut statut, Long projectId, Long userId) { }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTaskResponseBody get(@PathVariable Long id) {
        var taskDto = taskService.get(id);
        return mapper.toGetTaskResponseBody(taskDto);
    }
    
    public record UpdateTaskRequestBody(String title, TaskStatut statut, Long projectId, Long userId) { }
    public record UpdateTaskResponseBody(Long id, String title, TaskStatut statut, Long projectId, Long userId) { }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateTaskResponseBody update(@RequestParam UpdateTaskRequestBody body, @PathVariable Long id) {
        var update = taskService.update(id, body.title(), body.statut(), body.projectId(), body.userId());
        return mapper.toUpdateTaskResponseBody(update);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<GetTaskResponseBody> list(@PathParam("page") Integer page, @PathParam("size") Integer size) {
        var pageable = Pageable.ofSize(size).withPage(page);
        
        return Page.from(
                taskService
                        .list(pageable)
                        .map(mapper::toGetTaskResponseBody)
        );
    }
}
