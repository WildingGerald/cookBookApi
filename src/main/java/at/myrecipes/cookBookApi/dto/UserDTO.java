package at.myrecipes.cookBookApi.dto;

public record UserDTO(
        String username,
        String email,
        String firstname,
        String lastname,
        String description) {
}
