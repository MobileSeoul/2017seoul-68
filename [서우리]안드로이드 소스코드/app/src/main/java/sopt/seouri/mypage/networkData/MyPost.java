package sopt.seouri.mypage.networkData;

/**
 * Created by ash on 2017-10-08.
 */

public class MyPost {
    public int postId;
    public int userId;
    public String title;
    public String content;
    public int view_num;
    public String date;
    public int location;
    public String name;
    public String profile;

    public MyPost(int postId, int userId, String title, String content, int view_num, String date, int location, String name, String profile) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.view_num = view_num;
        this.date = date;
        this.location = location;
        this.name = name;
        this.profile = profile;
    }
}
