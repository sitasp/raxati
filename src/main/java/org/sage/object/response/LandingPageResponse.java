package org.sage.object.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.sage.object.domain.Topic;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LandingPageResponse {
    private List<Topic> liveNews;
    private List<Topic> archivedTopics;
}
