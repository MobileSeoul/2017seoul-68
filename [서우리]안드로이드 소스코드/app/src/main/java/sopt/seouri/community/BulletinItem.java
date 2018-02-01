package sopt.seouri.community;

/**
 * Created by yangseunghyuk on 2017-09-30.
 */

public class BulletinItem
{
    private String B_writer;
    private String B_title;
    private String B_date;
    private int B_views;

    public  BulletinItem(String writer,String title,String date,int views)
    {
        B_writer = writer;
        B_title = title;
        B_date = date;
        B_views = views;
    }

    public int getB_views() {
        return B_views;
    }

    public String getB_date() {
        return B_date;
    }

    public String getB_title() {
        return B_title;
    }

    public String getB_writer() {
        return B_writer;
    }

    public void setB_date(String b_date) {
        B_date = b_date;
    }

    public void setB_title(String b_title) {
        B_title = b_title;
    }

    public void setB_views(int b_views) {
        B_views = b_views;
    }

    public void setB_writer(String b_writer) {
        B_writer = b_writer;
    }
}
