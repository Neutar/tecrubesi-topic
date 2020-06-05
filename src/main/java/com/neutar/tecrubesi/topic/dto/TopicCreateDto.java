package com.neutar.tecrubesi.topic.dto;

import com.neutar.tecrubesi.topic.domain.*;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class TopicCreateDto {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    private UUID userId;

    @Builder.Default
    private List<String> tagList = new ArrayList<>();

    private Status status;
}
