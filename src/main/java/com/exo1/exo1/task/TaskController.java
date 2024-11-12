package com.exo1.exo1.task;

import com.exo1.exo1.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public record TaskController(TaskService taskService, Mapper mapper) {

    public record CreateTaskRequestBody(String title, TaskStatut statut, Long projectId, Long userId) { }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateTaskRequestBody body) {
        taskService.create(body.title(), body.statut(), body.projectId(), body.userId());
    }
    
    public record GetTaskResponseBody(Long id, String title, TaskStatut statut, Long projectId, Long userId) { }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTaskResponseBody get(@PathVariable Long id) {
        var taskDto = taskService.get(id);
        return mapper.toGetTaskResponseBody(taskDto);
    }
    
    public record PutTaskRequestBody(String title, TaskStatut statut, Long projectId, Long userId) { }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestParam PutTaskRequestBody body, @PathVariable Long id) {
        taskService.update(id, body.title(), body.statut(), body.projectId(), body.userId());
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
