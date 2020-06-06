package com.neutar.tecrubesi.topic.dto;

import com.neutar.tecrubesi.topic.domain.VoteType;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class AddVoteDto {
    @NotNull(message = "User id is mandatory")
    private UUID userId;

    @NotNull(message = "Vote type is mandatory")
    private VoteType voteType;
}
