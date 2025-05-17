package org.sage.object.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Topic {
    private String           id;
    @NotBlank
    private String           title;
    private String           description;
    private Long             numberOfViews;
    private Long             numberOfLikes;
    private String           slug;
    private String           status;
    private Boolean          isDeleted;
    private LocalDateTime    createdAt;
    private LocalDateTime    updatedAt;
    private LocalDateTime    deletedAt;
    private String           createdBy;
    private String           updatedBy;
    private String           deletedBy;
}
