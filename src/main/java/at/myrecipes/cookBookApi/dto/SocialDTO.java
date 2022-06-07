package at.myrecipes.cookBookApi.dto;


import at.myrecipes.cookBookApi.util.SocialEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public record SocialDTO(
        @NotEmpty(message = "Website can't be empty")
        String socialMedia,
        @NotEmpty
        String profileLink) {
}
