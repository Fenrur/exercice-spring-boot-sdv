package com.exo1.exo1.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectTaskCountService {

    Page<ProjectTaskCountDto> list(Pageable pageable);
}
