package org.sage.object.domain;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private String   id;
    @NotBlank
    private String   name;
    private String   details;
    private String   topicId;
    private Long     numberOfLikes;
    private Long     numberOfViews;
    private String   slug;
    private String   status;
    private Boolean          isDeleted;
    private LocalDateTime    createdAt;
    private LocalDateTime    updatedAt;
    private LocalDateTime    deletedAt;
    private String           createdBy;
    private String           updatedBy;
    private String           deletedBy;
}
