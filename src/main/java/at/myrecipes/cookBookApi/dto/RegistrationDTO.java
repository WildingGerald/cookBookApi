package at.myrecipes.cookBookApi.dto;

import javax.validation.constraints.NotEmpty;

public record RegistrationDTO(
    @NotEmpty
    String username,
    @NotEmpty
    String email,
    @NotEmpty
    String password) {
}