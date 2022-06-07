package at.myrecipes.cookBookApi.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum SocialEnum {
    facebook,
    twitter,
    youtube,
    instagram;

    public static SocialEnum fromString(String s) throws IllegalArgumentException{
        return Arrays.stream(SocialEnum.values())
                .filter(v -> v.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No valid social given"));
    }
}
