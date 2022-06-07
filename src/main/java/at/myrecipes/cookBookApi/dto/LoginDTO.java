package at.myrecipes.cookBookApi.dto;

import javax.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty
        String userName,
        @NotEmpty
        String password) {
}