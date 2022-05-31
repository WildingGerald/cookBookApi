package at.myrecipes.cookBookApi.dto;

import javax.validation.constraints.NotEmpty;

public record LoginDTO(
        String userName,
        String password) {

}