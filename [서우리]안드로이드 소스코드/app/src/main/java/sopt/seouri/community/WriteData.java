package sopt.seouri.community;

import java.util.ArrayList;

import okhttp3.MultipartBody;

/**
 * Created by yangseunghyuk on 2017-10-27.
 */

public class WriteData
{
    ArrayList<MultipartBody.Part> images;

    public WriteData(ArrayList<MultipartBody.Part> images) {
        this.images = images;
    }
}
