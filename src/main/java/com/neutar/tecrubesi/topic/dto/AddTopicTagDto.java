package com.neutar.tecrubesi.topic.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class AddTopicTagDto {
    @NotNull(message = "Name is mandatory")
    private String name;
}
