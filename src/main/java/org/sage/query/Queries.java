package org.sage.query;

public class Queries {

    public static class PostQueries {
        public static final String POST_BY_ID = "id = ?1 and (isDeleted is null or isDeleted = false)";
        public static final String POST_BY_USER_ID = "authorId = ?1 and (isDeleted is null or isDeleted = false)";
    }

    public static class TopicQueries {
        public static final String TOPIC_BY_ID = "id = ?1 and (isDeleted is null or isDeleted = false)";
        public static final String TOPIC_BY_SLUG = "slug = ?1 and (isDeleted is null or isDeleted = false)";
        public static final String TOPIC_BY_EVENT_ID = "eventId = ?1 and (isDeleted is null or isDeleted = false)";
    }
}
