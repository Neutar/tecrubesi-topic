package com.neutar.tecrubesi.topic.controller;

import com.neutar.tecrubesi.topic.controller.response.TopicDetailForUserResponse;
import com.neutar.tecrubesi.topic.controller.response.TopicForUserResponse;
import com.neutar.tecrubesi.topic.dto.*;
import com.neutar.tecrubesi.topic.mapper.TopicMapper;
import com.neutar.tecrubesi.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final TopicMapper topicMapper = TopicMapper.TOPIC_MAPPER;

    @PostMapping
    public void createTopic(@NotNull @Valid @RequestBody TopicCreateDto topicCreateDto){
        topicService.createTopic(topicCreateDto);
    }

    @PutMapping("/{topicId}")
    public void updateTopic(@PathVariable UUID topicId,  @NotNull @Valid @RequestBody TopicUpdateDto topicUpdateDto){
        topicService.updateTopic(topicId, topicUpdateDto);
    }

    @GetMapping
    public List<TopicForUserResponse> getAllTopicsForUser(@RequestParam("userId") UUID userId){
        List<TopicForUserDto> getAllTopicsForUserDtoList = topicService.getAllTopicsForUser(userId);
        return TopicMapper.TOPIC_MAPPER.mapTopicResponselListFrom(getAllTopicsForUserDtoList);
    }

    @GetMapping("/{topicId}")
    public TopicDetailForUserResponse getTopicDetailForUser(@PathVariable UUID topicId, @RequestParam("userId") UUID userId){
        TopicDetailForUserDto topicDetailForUserDto = topicService.getTopicDetailForUser(topicId, userId);
        return TopicMapper.TOPIC_MAPPER.mapTopicDetailForUserResponseFrom(topicDetailForUserDto);
    }

    @PutMapping("/{topicId}/vote")
    public void addVote(@PathVariable UUID topicId, @NotNull @Valid @RequestBody AddVoteDto addVoteDto){
        topicService.addVote(topicId, addVoteDto);
    }

    @PutMapping("/{topicId}/flag")
    public void addFlag(@PathVariable UUID topicId, @NotNull @Valid @RequestBody AddFlagDto addFlagDto){
        topicService.addFlag(topicId, addFlagDto);
    }

    @PostMapping("/{topicId}/comment")
    public void addComment(@PathVariable UUID topicId, @NotNull @Valid @RequestBody AddCommentDto addCommentDto){
        topicService.addComment(topicId, addCommentDto);
    }

    @PutMapping("/{topicId}/comment/{commentId}/flag")
    public void addFlagForComment(@PathVariable UUID topicId, @PathVariable UUID commentId, @NotNull @Valid @RequestBody AddFlagDto addFlagDto){
        topicService.addFlagForComment(topicId, commentId, addFlagDto);
    }

    @PostMapping("/{topicId}/tag")
    public void addTopicTag(@PathVariable UUID topicId, @NotNull @Valid @RequestBody AddTopicTagDto addTopicTag){
        topicService.addTopicTag(topicId, addTopicTag);
    }

    @PutMapping("/{topicId}/bookmark")
    public void addBookmark(@PathVariable UUID topicId, @NotNull @Valid @RequestBody AddBookmarkDto addBookmarkDto){
        topicService.addBookmark(topicId, addBookmarkDto);
    }

    @PutMapping("/{topicId}/follow")
    public void addFollow(@PathVariable UUID topicId, @NotNull @Valid @RequestBody AddFollowDto addFollowDto){
        topicService.addFollow(topicId, addFollowDto);
    }

}
