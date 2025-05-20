package org.sage.object.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.sage.entity.PostEntity;
import org.sage.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private String          authorId;
    private String          content;
    private String          parentPostId;
    private String          resharedPostId;
    private String          visibility;
    public Long             numberOfLikes;
    public Long             numberOfViews;
    public String           status;
    public List<String>     mediaUrls = new ArrayList<>();
}
