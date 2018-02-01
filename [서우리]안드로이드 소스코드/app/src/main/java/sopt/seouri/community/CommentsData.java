package sopt.seouri.community;

/**
 * Created by yangseunghyuk on 2017-10-08.
 */

public class CommentsData
{
    public String commentId;
    public String postId;
    public String userId;
    private String content;
    public String date;
    private String name;

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
