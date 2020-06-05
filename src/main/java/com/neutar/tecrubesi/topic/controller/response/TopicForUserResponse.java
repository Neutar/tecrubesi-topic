package com.neutar.tecrubesi.topic.controller.response;

import com.neutar.tecrubesi.topic.domain.Comment;
import com.neutar.tecrubesi.topic.domain.Status;
import com.neutar.tecrubesi.topic.domain.TopicTag;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class TopicForUserResponse {
    private UUID topicId;

    private UUID userId;

    private String title;

    private Long upVoteCount;

    private Long downVoteCount;

    private boolean isBookmarked;

    private boolean isFollowed;

    private boolean isUpVoted;

    private boolean isDownVoted;

    @Builder.Default
    private List<TopicTag> tagList = new ArrayList<>();

    private Status status;
}
