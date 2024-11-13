package com.exo1.exo1.task;

import com.exo1.exo1.Page;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/task")
public class ProjectTaskCountController {
    
    private final ProjectTaskCountService projectTaskCountService;

    public ProjectTaskCountController(ProjectTaskCountService projectTaskCountService) {
        this.projectTaskCountService = projectTaskCountService;
    }

    @GetMapping("/count")
    public Page<ProjectTaskCountDto> count(@PathParam("page") Integer page, @PathParam("size") Integer size) {
        return Page.from(
                projectTaskCountService.list(Pageable.ofSize(size).withPage(page))
        );
    }
}
