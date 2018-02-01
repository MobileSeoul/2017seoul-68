package sopt.seouri.ask;

/**
 * Created by ash on 2017-10-17.
 */

public class MyQuestion {
    public int queId;
    public String userId;
    public String title;
    public String content;
    public String date;
    public String name;
    public String profile;

    public MyQuestion(int queId, String userId, String title, String content, String date, String name, String profile) {
        this.queId = queId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.name = name;
        this.profile = profile;
    }
}
