package sopt.seouri.home;

import java.util.ArrayList;

import sopt.seouri.home.networkData.DistanceRec;
import sopt.seouri.home.networkData.JobinformationData;
import sopt.seouri.home.networkData.PosterData;
import sopt.seouri.home.networkData.VillageinformationData;
import sopt.seouri.home.networkData.WeekvillageEnterpriseData;

/**
 * Created by ash on 2017-10-07.
 */

public class MainResult {
    public String message;
    public ArrayList<PosterData> poster;
    public ArrayList<WeekvillageEnterpriseData> weekvillageEnterprise;
    public ArrayList<VillageinformationData> villageinformation;
    public ArrayList<JobinformationData> jobinformation;
    public DistanceRec distanceRec;
}
