package at.myrecipes.cookBookApi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record AuthenticationResponse(String authToken) {
}
