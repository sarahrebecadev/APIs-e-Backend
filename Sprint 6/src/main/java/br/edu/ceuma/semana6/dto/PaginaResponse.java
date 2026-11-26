package br.edu.ceuma.semana6.dto;

import java.util.List;

public record PaginaResponse<T>(List<T> data, Meta meta) {
    public record Meta(long totalItems, int itemsPerPage, int currentPage, int totalPages,
                       String first, String prev, String next, String last) {}
}