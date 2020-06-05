package com.neutar.tecrubesi.topic.exception;

import java.util.UUID;

public class CommentNotFoundException extends RuntimeException {
    private static final String COMMENT_NOT_FOUND = "Comment not found. Comment id= ";

    public CommentNotFoundException(UUID commentId, UUID topicId){
        super(COMMENT_NOT_FOUND + commentId + " of Topic Id= " + topicId);
    }
}
