package at.myrecipes.cookBookApi.dto;

import javax.validation.constraints.NotEmpty;

public record RegistrationDTO(
    @NotEmpty
    String userName,
    @NotEmpty
    String email,
    @NotEmpty
    String password) {
}