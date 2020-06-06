package com.neutar.tecrubesi.topic.dto;

import com.neutar.tecrubesi.topic.domain.Flag;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class AddCommentDto {

    @NotBlank(message = "Message is mandatory")
    private String message;

}
