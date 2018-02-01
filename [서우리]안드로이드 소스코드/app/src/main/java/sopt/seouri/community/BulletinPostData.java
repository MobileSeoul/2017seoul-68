package sopt.seouri.community;

/**
 * Created by yangseunghyuk on 2017-10-07.
 */

public class BulletinPostData {
    public String postId;
    public String userId;
    public String title;
    public String content;
    public String view_num;
    public String date;
    public String location;
    private String name;
    public String profile;

    public String getName() {
        return name;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setView_num(String view_num) {
        this.view_num = view_num;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
