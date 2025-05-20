package org.sage.object.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private String          id;
    private String          authorId;
    @NotBlank
    private String          content;
    private String          parentPostId;
    private String          resharedPostId;
    private String          visibility;
    public Long             numberOfLikes;
    public Long             numberOfViews;
    public String           status;
    public List<String>     mediaUrls = new ArrayList<>();
    private Boolean         isDeleted;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;
    private LocalDateTime   deletedAt;
    private String          createdBy;
    private String          updatedBy;
    private String          deletedBy;
}
