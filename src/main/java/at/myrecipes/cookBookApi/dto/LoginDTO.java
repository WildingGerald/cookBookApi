package at.myrecipes.cookBookApi.dto;

import javax.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty
        String username,
        @NotEmpty
        String password) {
}