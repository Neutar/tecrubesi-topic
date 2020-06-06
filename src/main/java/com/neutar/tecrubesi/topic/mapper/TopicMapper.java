package com.neutar.tecrubesi.topic.mapper;

import com.neutar.tecrubesi.topic.controller.response.TopicDetailForUserResponse;
import com.neutar.tecrubesi.topic.controller.response.TopicForUserResponse;
import com.neutar.tecrubesi.topic.domain.*;
import com.neutar.tecrubesi.topic.dto.*;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface TopicMapper {
    TopicMapper TOPIC_MAPPER = Mappers.getMapper(TopicMapper.class);

    @Mapping(source = "tagList", target = "tagList", qualifiedByName = "createTagList")
    Topic mapTopicFrom(TopicCreateDto topicCreateDto);

    @Named("createTagList")
    default List<TopicTag> createTagList(List<String> tagList){
        return tagList.stream().map(tag -> TopicTag.builder().name(tag).build()).collect(Collectors.toList());
    }

    Topic mapTopicFrom(TopicUpdateDto topicUpdateDto);

    Vote mapVoteFrom(AddVoteDto addVoteDto);

    Comment mapCommentFrom(AddCommentDto addCommentDto);

    Flag mapFlagFrom(AddFlagDto addFlagDto);

    TopicTag mapTopicTagFrom(AddTopicTagDto addTopicTag);

    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "topic.userId", target = "userId")
    @Mapping(source = "topic.voteList", target = "upVoteCount", qualifiedByName = "upVoteCount")
    @Mapping(source = "topic.voteList", target = "downVoteCount", qualifiedByName = "downVoteCount")
    @Mapping(source = "topic.bookmarkedUserIdList", target = "isBookmarked", qualifiedByName = "isUserInvolved")
    @Mapping(source = "topic.followedUserIdList", target = "isFollowed", qualifiedByName = "isUserInvolved")
    @Mapping(source = "topic.voteList", target = "isUpVoted", qualifiedByName = "isUpVoted")
    @Mapping(source = "topic.voteList", target = "isDownVoted", qualifiedByName = "isDownVoted")
    TopicForUserDto mapTopicForUserDtoFrom(Topic topic, @Context UUID userId);

    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "topic.userId", target = "userId")
    @Mapping(source = "topic.voteList", target = "upVoteCount", qualifiedByName = "upVoteCount")
    @Mapping(source = "topic.voteList", target = "downVoteCount", qualifiedByName = "downVoteCount")
    @Mapping(source = "topic.bookmarkedUserIdList", target = "isBookmarked", qualifiedByName = "isUserInvolved")
    @Mapping(source = "topic.followedUserIdList", target = "isFollowed", qualifiedByName = "isUserInvolved")
    @Mapping(source = "topic.voteList", target = "isUpVoted", qualifiedByName = "isUpVoted")
    @Mapping(source = "topic.voteList", target = "isDownVoted", qualifiedByName = "isDownVoted")
    @Mapping(source = "topic.flagList", target = "isFlagged", qualifiedByName = "isFlagged")
    TopicDetailForUserDto mapTopicDetailForUserDtoFrom(Topic topic, @Context UUID userId);

    @Named("upVoteCount")
    default Long upVoteCount(List<Vote> voteList){
        return voteList.stream().filter((vote) -> vote.getVoteType() == VoteType.UPVOTE).count();
    }

    @Named("downVoteCount")
    default Long downVoteCount(List<Vote> voteList){
        return voteList.stream().filter((vote) -> vote.getVoteType() == VoteType.DOWNVOTE).count();
    }

    @Named("isUserInvolved")
    default boolean isUserInvolved(List<UUID> list, @Context UUID userId){
        return list.contains(userId);
    }

    @Named("isUpVoted")
    default boolean isUpVoted(List<Vote> voteList, @Context UUID userId){
        return isVoted(voteList, userId, VoteType.UPVOTE);
    }

    @Named("isDownVoted")
    default boolean isDownVoted(List<Vote> voteList, @Context UUID userId){
        return isVoted(voteList, userId, VoteType.DOWNVOTE);
    }

    default boolean isVoted(List<Vote> voteList, UUID userId, VoteType voteType) {
        return voteList.stream().filter(vote -> vote.getVoteType() == voteType).anyMatch(vote -> vote.getUserId().equals(userId));
    }

    @Named("isFlagged")
    default boolean isFlagged(List<Flag> flagList, @Context UUID userId){
        return flagList.stream().anyMatch(flag -> flag.getUserId().equals(userId));
    }

    List<TopicForUserResponse> mapTopicResponselListFrom(List<TopicForUserDto> getAllTopicsForUserDtoList);

    TopicDetailForUserResponse mapTopicDetailForUserResponseFrom(TopicDetailForUserDto topicDetailForUserDto);
}
