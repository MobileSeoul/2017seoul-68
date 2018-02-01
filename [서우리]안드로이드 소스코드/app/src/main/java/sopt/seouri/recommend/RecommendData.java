package sopt.seouri.recommend;

/**
 * Created by ash on 2017-10-08.
 */

public class RecommendData {
    public String commpanyName1;
    public String commpanyComment1;
    public String commpanyName2;
    public String commpanyComment2;
    public String commpanyName3;
    public String commpanyComment3;
    public int recommendComment;
    public int recommendName;
    public int recommendImg;
    public int commpanyID1;
    public int commpanyID2;
    public int commpanyID3;

    public RecommendData(String commpanyName1, String commpanyComment1, String commpanyName2, String commpanyComment2, String commpanyName3, String commpanyComment3, int recommendComment, int recommendName, int recommendImg, int commpanyID1, int commpanyID2, int commpanyID3) {
        this.commpanyName1 = commpanyName1;
        this.commpanyComment1 = commpanyComment1;
        this.commpanyName2 = commpanyName2;
        this.commpanyComment2 = commpanyComment2;
        this.commpanyName3 = commpanyName3;
        this.commpanyComment3 = commpanyComment3;
        this.recommendComment = recommendComment;
        this.recommendName = recommendName;
        this.recommendImg = recommendImg;
        this.commpanyID1 = commpanyID1;
        this.commpanyID2 = commpanyID2;
        this.commpanyID3 = commpanyID3;
    }
}
