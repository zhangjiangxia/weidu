package com.bw.movie.model.http;

import com.bw.movie.model.bean.AboutBean;
import com.bw.movie.model.bean.BeingBean;
import com.bw.movie.model.bean.BuyBean;
import com.bw.movie.model.bean.BuyRecordBean;
import com.bw.movie.model.bean.CancelFollowBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.DyAndYyIdBean;
import com.bw.movie.model.bean.EvaluationCinemaBean;
import com.bw.movie.model.bean.FilmBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.bean.HeadPicBean;
import com.bw.movie.model.bean.ListByCinemaldBean;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.bean.NearbyBean;
import com.bw.movie.model.bean.PageListBean;
import com.bw.movie.model.bean.RecommendBean;
import com.bw.movie.model.bean.RegBean;
import com.bw.movie.model.bean.UsreIdBean;
import com.bw.movie.model.bean.WechitBean;
import com.bw.movie.model.bean.WeiXinLoginBean;
import com.bw.movie.model.bean.XqRecommmendBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    //查询热门电影列表
   // http://172.17.8.100/movieApi/movie/v1/findHotMovieList?page=1&count=10
    @GET("movieApi/movie/v1/findHotMovieList")
    Observable<FilmBean> findHotMovieList_uri(@Query("page") int page, @Query("count") int count);
    //正在上映电影列表
    @GET("movieApi/movie/v1/findReleaseMovieList")
    Observable<BeingBean> findReleaseMovieList_uri(@Query("page") int page, @Query("count") int count);
    //即将上映电影列表
    @GET("movieApi/movie/v1/findComingSoonMovieList")
    Observable<AboutBean> findComingSoonMovieList_uri(@Query("page") int page, @Query("count") int count);
    //电影详情
    //http://172.17.8.100/movieApi/movie/v1/findMoviesDetail?movieId=17
    @GET("movieApi/movie/v1/findMoviesDetail")
    Observable<DetailsBean> findMoviesById_uri(@Query("movieId") String movieId);
    //查询影片评论
    //http://172.17.8.100/movieApi/movie/v1/findAllMovieComment?movieId=1&page=1&count=5
    @GET("movieApi/movie/v1/findAllMovieComment")
    Observable<MovieCommentBean> findAllMovieComment(@Query("movieId") String movieId,@Query("page")int page,@Query("count")int count);

    //推荐
   //查询推荐影院信息
    // http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas?longitude=116.30551391385724&latitude=40.04571807462411&page=1&count=10
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<RecommendBean> findRecommendCinemas(@Query("longitude")String longitude, @Query("latitude")String latitude, @Query("page")int page, @Query("count")int count);
    //查询电院信息明细
    //http://172.17.8.100/movieApi/cinema/v1/findCinemaInfo?cinemaId=10
    @GET("movieApi/cinema/v1/findCinemaInfo")
    Observable<XqRecommmendBean> findCinemaInfo(@Query("cinemaId")String cinemaId);

     //注册
    @FormUrlEncoded
    @POST("movieApi/user/v1/registerUser")
    Observable<RegBean> registerUser(@FieldMap Map<String, String> map);
    //登录
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Observable<LoginBean> login(@FieldMap Map<String, String> map);

    //根据电影ID和影院ID查询电影排期列表
    @GET("movieApi/movie/v1/findMovieListByCinemaId")
    Observable<ListByCinemaldBean> findMovieListByCinemaId(@Query("cinemaId" )int cinemasId);


    //根据电影ID和影院ID查询电影排期列表
    //http://172.17.8.100/movieApi/movie/v1/findMovieScheduleList?cinemasId=22&cinemasId=2
    @GET("movieApi/movie/v1/findMovieScheduleList")
    Observable<DyAndYyIdBean> findMovieScheduleList(@Query("cinemasId" ) int cinemasId, @Query("movieId") int movieId);


    //查询影院用户评论列表
    @GET("movieApi/cinema/v1/findAllCinemaComment")
    Observable<EvaluationCinemaBean> findAllCinemaComment(@Query("cinemaId" ) int cinemaId, @Query("page") int page, @Query("count") int count);

    //根据用户ID查询用户信息
    @GET("movieApi/user/v1/verify/getUserInfoByUserId")
    Observable<UsreIdBean> getUserInfoByUserId(@Header("userId" ) String userId, @Header("sessionId") String sessionId);

    //附近影院
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<NearbyBean> findnearbyCinemas(@Query("longitude")String longitude, @Query("latitude")String latitude, @Query("page")int page, @Query("count")int count);
    //关注电影
    @GET("movieApi/movie/v1/verify/followMovie")
    Observable<FollowBean> followMovie(@Query("movieId")int movieId);

    //取消关注电影
    @GET("movieApi/movie/v1/verify/cancelFollowMovie")
    Observable<CancelFollowBean> cancelFollowMovie(@Query("movieId")int movieId);

//    http://172.17.8.100/movieApi/movie/v1/verify/buyMovieTicket?scheduleId=479&amount=1&sign=zUk7n1F6ZhCTzHv9PvsyNg==

    //下单接口
    @FormUrlEncoded
    @POST("movieApi/movie/v1/verify/buyMovieTicket")
    Observable<BuyBean> buyMovieTicket(@Field("scheduleId") String scheduleId, @Field("amount") String amount, @Field("sign")String sign);
   //支付接口
    @FormUrlEncoded
    @POST("movieApi/movie/v1/verify/pay")
    Observable<WechitBean> pay(@Field("payType") int payType, @Field("orderId") String orderId);
   //上传用户头像
   @FormUrlEncoded
   @POST("movieApi/user/v1/verify/uploadHeadPic")
   Observable<HeadPicBean> uploadHeadPic(@Header("userId") String userId, @Header("sessionId") String sessionId);

   //查询用户关注的影片列表
    @GET("movieApi/movie/v1/verify/findMoviePageList")
    Observable<PageListBean> findMoviePageList(@Query("page") int page, @Query("count") int count);

    //用户购票记录查询列表
    @GET("movieApi/user/v1/verify/findUserBuyTicketRecordList")
    Observable<BuyRecordBean> findUserBuyTicketRecordList(@Query("page") int page, @Query("count") int count);

    //微信登录
    @FormUrlEncoded
    @POST("movieApi/user/v1/weChatBindingLogin")
    Observable<LoginBean> weChatBindingLogin(@Field("code") String code);
}
