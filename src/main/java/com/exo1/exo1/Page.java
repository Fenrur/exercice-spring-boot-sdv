package com.exo1.exo1;

import java.util.List;

public record Page<T>(List<T> content, int pageNumber, int pageSize, int totalPages, long totalElements, boolean isFirstPage, boolean isLastPage, boolean isEmpty) {
    
    public static <T> Page<T> from(org.springframework.data.domain.Page<T> page) {
        return new Page<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements(), page.isFirst(), page.isLast(), page.isEmpty());
    }
}
