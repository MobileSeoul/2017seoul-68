package sopt.seouri.community;

/**
 * Created by yangseunghyuk on 2017-10-08.
 */

public class BulletinAddCommentData
{
    public int userId;
    public String postId;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
