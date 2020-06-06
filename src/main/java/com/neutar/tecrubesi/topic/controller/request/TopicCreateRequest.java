package com.neutar.tecrubesi.topic.controller.request;

import com.neutar.tecrubesi.topic.domain.*;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class TopicCreateRequest {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    private UUID userId;

    @Builder.Default
    private List<TopicTag> tagList = new ArrayList<>();

    private Status status;
}
