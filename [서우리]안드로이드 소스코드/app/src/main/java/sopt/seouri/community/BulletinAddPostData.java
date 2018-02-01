package sopt.seouri.community;

import java.util.ArrayList;

/**
 * Created by yangseunghyuk on 2017-10-08.
 */

public class BulletinAddPostData
{
    public String userId;
    public String title;
    private String content;
    public ArrayList<ImageData> images;
    public String location;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
