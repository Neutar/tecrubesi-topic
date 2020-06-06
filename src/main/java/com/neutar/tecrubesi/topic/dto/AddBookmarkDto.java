package com.neutar.tecrubesi.topic.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class AddBookmarkDto {
    @NotNull(message = "User id is mandatory")
    private UUID userId;
}
