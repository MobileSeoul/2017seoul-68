package sopt.seouri.network;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import sopt.seouri.ask.AskData;
import sopt.seouri.ask.AskPostResult;
import sopt.seouri.ask.AskResult;
import sopt.seouri.community.BulletinAddCommentData;
import sopt.seouri.community.BulletinAddCommentResult;
import sopt.seouri.community.BulletinAddPostResult;
import sopt.seouri.community.FindBulletinDetailResult;
import sopt.seouri.community.FindBulletinResult;
import sopt.seouri.community.SearchBulletinResult;
import sopt.seouri.community.SendSearchBulletinData;
import sopt.seouri.home.MainResult;
import sopt.seouri.home.networkData.JobRegData;
import sopt.seouri.home.networkData.JobResult;
import sopt.seouri.home.networkData.MainData;
import sopt.seouri.login.LoginData;
import sopt.seouri.login.LoginResult;
import sopt.seouri.login.SignupData;
import sopt.seouri.login.SignupResult;
import sopt.seouri.mypage.networkData.MyPageData;
import sopt.seouri.mypage.networkData.MyPageResult;
import sopt.seouri.search.category.SearchCategoryResult;
import sopt.seouri.search.detail.SearchDetailResult;
import sopt.seouri.search.searchvillage.SearchVillageName;
import sopt.seouri.search.searchvillage.SearchVillageResult;


/**
 * Created by 김지원 on 2017-10-05.
 */
//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJidHRiNkBuYXZlci5jb20iLCJpYXQiOjE1MDkxMTMxMTIsImV4cCI6MTUxMTcwNTExMn0.XrrMQQ5mEqR2yD35JrXFwdYKBowUsbLoEZ9ORKZhZqU
public interface NetworkService {
    /*성현이 API*/
    @GET("question/{userId}")
    Call<AskResult> getAskResult(@Header("token")String token,@Path("userId")String userId);
    @POST("question")
    Call<AskPostResult> getAskPostResult(@Header("token")String token, @Body AskData askData);
    @POST("job")
    Call<JobResult> getJobResult(@Header("token")String token,@Body JobRegData jobRegData);
    @POST("member/mypage")
    Call<MyPageResult> getMyPageResult(@Header("token")String token,@Body MyPageData myPageData);
    @POST("member")
    Call<SignupResult> getsignupResult(@Body SignupData signupData);
    @POST("member/login")
    Call<LoginResult> getLoginResult(@Body LoginData loginData);
    @POST("home")
    Call<MainResult> getMainResult(@Header("token")String token,@Body MainData mainData);

    /*검색 API**/
    // 카테고리별 마을기업 조회
    @GET("villageEnterprise/list/{location}")
    Call<SearchCategoryResult> getSearchCategoryResult(@Header("token")String token,@Path("location") String location);

    // 특정 마을기업 조회
    @GET("villageEnterprise/detail/{villageEnterpriseId}")
    Call<SearchDetailResult> getSearchDetailResult(@Header("token")String token,@Path("villageEnterpriseId") String villageEnterpriseId);

//    // 검색 팝업
//    @GET("villageEnterprise/{name}")
//    Call<SearchPopupResult> getSearchPopupResult(@Path("name") String name);

//    // 검색 팝업을 위한 데이터 받아오기
//    @GET("villageEnterprise/total")
//    Call<SearchPopupResult> getSearchPopupResult(@Header("token")String token);

    // 툴바 검색
    @POST("villageEnterprise/search")
    Call<SearchVillageResult> getSearchVillageResult(@Header("token")String token, @Body SearchVillageName name);

    /////////////////////////////////////////  커뮤니티

    // 게시글 조회
    @GET("community/list/{location}")
    Call<FindBulletinResult> getFindBulletinResult(@Header("token")String token,@Path("location") String location);

    //게시글 상세 조회
    @GET("community/{postId}")
    Call<FindBulletinDetailResult> getFindBulletinDetailResult(@Header("token")String token,@Path("postId") String postId);

    //게시글 작성
//    @Multipart
//    @POST("community")
//    Call<BulletinAddPostResult> getBulletinAddPostResult(@Header("token") String token,
//                                                         @Body BulletinAddPostData bulletinPostData, List<RequestBody> images);

//    @Multipart
//    @POST("community")
//    Call<BulletinAddPostResult> getBulletinAddPostResult(@Header("token") String token,
//                                                         @Part("userId") RequestBody userId,
//                                                         @Part("title") RequestBody title,
//                                                         @Part("content") RequestBody content,
//                                                         @Part MultipartBody.Part images,
//                                                         @Part("location") RequestBody location);

    @Multipart
    @POST("community")
    Call<BulletinAddPostResult> getBulletinAddPostResult(@Header("token") String token,
                                                         @Part("userId") RequestBody userId,
                                                         @Part("title") RequestBody title,
                                                         @Part("content") RequestBody content,
                                                         @Part MultipartBody.Part images,
                                                         @Part("location") RequestBody location);

//    @Multipart
//    @POST("community")
//    Call<BulletinAddPostResult> getBulletinAddPostResult(@Header("token") String token,
//                                                         @Part("userId") RequestBody userId,
//                                                         @Part("title") RequestBody title,
//                                                         @Part("content") RequestBody content,
//                                                         @Part MultipartBody.Part images[],
//                                                         @Part("location") RequestBody location);


//    @Multipart
//    @POST("community")
//    Call<BulletinAddPostResult> getBulletinAddPostResult(@Header("token") String token,
//                                                         @Part("userId") RequestBody userId,
//                                                         @Part("title") RequestBody title,
//                                                         @Part("content") RequestBody content,
//                                                         @Part("location") RequestBody location,
//                                                         @Part("images") RequestBody images);

//    Call<BulletinAddPostResult> getBulletinAddPostResult(@Body BulletinAddPostData bulletinAddPostData);

    //댓글 작성
    @POST("community/comment")
    Call<BulletinAddCommentResult> getBulletinAddCommentResult(@Header("token")String token,@Body BulletinAddCommentData bulletinAddCommentData);

    //게시글 검색
    @POST("community/search")
    Call<SearchBulletinResult> getSearchBulletinResult(@Header("token")String token,@Body SendSearchBulletinData sendSearchBulletinData);
}
