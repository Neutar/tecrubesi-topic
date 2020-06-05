package com.neutar.tecrubesi.topic.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class AddFlagDto {
    @NotNull(message = "User id is mandatory")
    private UUID userId;

    @NotNull(message = "Reason is mandatory")
    private String reason;
}
