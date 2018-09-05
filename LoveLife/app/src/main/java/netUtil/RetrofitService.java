package netUtil;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by YuLiang on 2018/4/3.
 * <p>
 * Retrofit 存储构建请求 API 的接口
 */

public interface RetrofitService {

    /**
     * 商店相关接口
     **/

    //商店最新课程列表
//    @Headers("Cache-Control: public, max-age=" + 24 * 3600)
    @GET("shop-course/index-newest")
    Observable<JsonObject> getShopCourseNewest(@Header("Access-Token") String access_token,
                                               @Query("expand") String expand);

    //获取指定课程的详细信息
    @GET("shop-course/view")
    Observable<JsonObject> getShopCourseView(@Header("Access-Token") String access_token,
                                             @Query("cid") String cid, @Query("expand") String expand);

    //根据课程的 商店课程ID 获取章节
    @Headers("Content-Type:application/json")
    @POST("section/view")
    Observable<JsonObject> getSection(@Header("Access-Token") String access_token, @Body RequestBody requestBody,
                                      @Query("expand") String expand);

    //获取热门评论(课程或章节)
    @GET("comment/course-hottest-index")
    Observable<JsonObject> getHotComment(@Header("Access-Token") String access_token,
                                         @Query("entity_id") String id, @Query("expand") String expand);

    //获取最新评论(课程或章节)
    @GET("comment/course-newest-index")
    Observable<JsonObject> getNewComment(@Header("Access-Token") String access_token,
                                         @Query("entity_id") String id, @Query("expand") String expand);

    //课程点赞
    @Headers("Content-Type:application/json")
    @POST("shop-course/like")
    Observable<JsonObject> likeCourse(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //课程取消点赞
    @Headers("Content-Type:application/json")
    @POST("shop-course/dislike")
    Observable<JsonObject> dislikeCourse(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //评论课程
    @Headers("Content-Type:application/json")
    @POST("comment/course")
    Observable<JsonObject> commentCourse(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //评论章节
    @Headers("Content-Type:application/json")
    @POST("comment/section")
    Observable<JsonObject> commentSection(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //点赞评论
    @Headers("Content-Type:application/json")
    @POST("comment/like")
    Observable<JsonObject> likeComment(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //获取商店中卡片列表
    @Headers("Content-Type:application/json")
    @POST("card/view")
    Observable<JsonObject> getShopCard(@Header("Access-Token") String access_token,
                                       @Body RequestBody requestBody);

    //获取加密之后的音频地址
    @GET("misc/qiniu-private-url")
    Observable<JsonObject> getPrivateUrl(@Header("Access-Token") String access_token,
                                         @Query("filename") String filename, @Query("expires") int expires);

    /**************************************支付功能*********************************************/
    //支付宝，获取订单签名
    @Headers("Content-Type:application/json")
    @POST("alipay/sign")
    Observable<JsonObject> getAliPaySign(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //微信，获取订单签名
    @Headers("Content-Type:application/json")
    @POST("weixin-pay/sign")
    Observable<JsonObject> getWeiXinPaySign(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //查看订单（验证支付是否成功）
    @GET("payment-journal/view")
    Observable<JsonObject> getPaymentView(@Header("Access-Token") String access_token,
                                          @Query("id") String id, @Query("expand") String expand);

    //微信，查询微信订单是否成功
    @GET("weixin-pay/query-order")
    Observable<JsonObject> getWeiXinPayQuery(@Header("Access-Token") String access_token,
                                             @Query("payment_id") String payment_id);

    //购买免费课程
    @Headers("Content-Type:application/json")
    @POST("shop-course/free-course")
    Observable<JsonObject> buyFreeCourse(@Header("Access-Token") String access_token,
                                         @Body RequestBody requestBody);

    //根据课程ID获取课程的卡包
    @GET("package/course-package")
    Observable<JsonObject> getCoursePackage(@Header("Access-Token") String access_token, @Query("cid") String cid);


    /****************************************注册登录*************************************************/
    @Headers("Content-Type:application/json")
    @POST("user/login")
    Observable<JsonObject> login(@Body RequestBody requestBody);

    //发送验证码
    @POST("user/send-captcha")
    Observable<JsonObject> send(@Body RequestBody body);

    //验证码注册
    @POST("user/captcha-register")
    Observable<JsonObject> verificationCodeRegister(@Body RequestBody body);

    //验证码登陆
    @POST("user/captcha-login")
    Observable<JsonObject> verificationCodeLogin(@Body RequestBody body);

    //第三方登陆(友盟)
    @POST("user/social-login")
    Observable<JsonObject> socialLogin(@Body RequestBody body);

    //重置密码
    @POST("user/reset-password")
    Observable<JsonObject> resetPassword(@Body RequestBody body);

    //修改密码
    @POST("user-info/init-password")
    Observable<JsonObject> initPassword(@Header("Access-Token") String access_token, @Body RequestBody body);

    //验证密码
    @POST("user-info/validate-password")
    Observable<JsonObject> comfirmPassword(@Header("Access-Token") String access_token, @Body RequestBody body);

    //获取服务器用户的数据（同步暂时不用）
    @GET("user-info/view")
    Observable<JsonObject> getServerUserDate(@Header("Access-Token") String access_token);

    //判断手机是否注册过
    @GET("user/is-new-phone")
    Observable<JsonObject> checkPhoneState(@Query("phone") String phone);

    //发送验证码登陆状态下
    @POST("user-info/send-captcha")
    Observable<JsonObject> sendCaptcha(@Header("Access-Token") String access_token, @Body RequestBody body);

    //修改绑定手机
    @POST("user-info/change-phone")
    Observable<JsonObject> modifyPhone(@Header("Access-Token") String access_token, @Body RequestBody body);

    //获取用户信息（根据token）
    @GET("user-info/view")
    Observable<JsonObject> getUserInfo(@Header("Access-Token") String access_token);

    //应用更新（检测是否有新版本）
    @GET("misc/latest-version")
    Observable<JsonObject> getServiceVersionCode(@Header("Access-Token") String access_token, @Query("os") int android);

    /****************************** 同步上传 ***********************************/

    //同步自制卡片、卡包信息
    @Headers("Content-Type:application/json")
    @POST("sync/diy-package")
    Observable<JsonObject> syncDIYInfo(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //修改卡片所在的卡包
    @Headers("Content-Type:application/json")
    @POST("sync/card-package-relation")
    Observable<JsonObject> syncCardChangePackage(@Header("Access-Token") String access_token,
                                                 @Body RequestBody requestBody);

    //卡片退出学习
    @Headers("Content-Type:application/json")
    @POST("sync/card-quit-learn")
    Observable<JsonObject> syncCardQuitLearn(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //同步卡片记忆信息
    @Headers("Content-Type:application/json")
    @POST("sync/card-memory")
    Observable<JsonObject> syncCardMemory(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //获取向七牛同步图片时，需要的token信息
    @GET("misc/qiniu-token")
    Observable<JsonObject> getQiNiuToken(@Header("Access-Token") String access_token);

    //同步用户数据
    @Headers("Content-Type:application/json")
    @POST("user-info/set-detail")
    Observable<JsonObject> syncUserData(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    //章节学习信息
    @Headers("Content-Type:application/json")
    @POST("sync/course-study-record")
    Observable<JsonObject> syncSectionMemory(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    /****************************** 同步下载 ***********************************/
    //同步下载卡包
    @GET("package/index")
    Observable<JsonObject> syncDownSelfPackage(@Header("Access-Token") String access_token);

    //同步下载卡片
    @GET("package/diy-cards")
    Observable<JsonObject> syncDownSelfCard(@Header("Access-Token") String access_token,
                                            @Query("puuid") String packageUuid,
                                            @Query("expand") String expand,
                                            @Query("page") int page);

    //获取最近学习的课程
    @GET("user-course/study-record")
    Observable<JsonObject> syncDownRecentCourse(@Header("Access-Token") String access_token);

    //同步下载课程卡包
    @GET("package/course-package-index")
    Observable<JsonObject> syncDownCoursePackage(@Header("Access-Token") String access_token);

    //同步下载课程卡片
    @GET("package/course-cards")
    Observable<JsonObject> syncDownCourseCard(@Header("Access-Token") String access_token,
                                              @Query("puuid") String puuid);

    //获取章节的学习信息
    @GET("user-course/section-study-records")
    Observable<JsonObject> syncDownSectionStudy(@Header("Access-Token") String access_token,
                                                @Query("cid") String cid, @Query("fields") String fields);

    /**********************************个人界面*******************************/
    //获取用户购买的课程
    @GET("user-course/index")
    Observable<JsonObject> getUserCourse(@Header("Access-Token") String access_token,
                                         @Query("expand") String expand);

    @GET("user-course/refund-index")
    Observable<JsonObject> getUserRefundCourse(@Header("Access-Token") String access_token,
                                               @Query("expand") String expand);

    //退款申请
    //同步卡片记忆信息
    @Headers("Content-Type:application/json")
    @POST("user-course/refund")
    Observable<JsonObject> refundApply(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    /*******************************下载文件**********************************/
    //下载文件的接口，可以实现断点续传
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Header("RANGE") String start, @Url String fileUrl);

    //下载图片
    @Streaming
    @GET
    Observable<ResponseBody> downloadImage(@Url String imageUrl);
    /****************************** 意见反馈 ***********************************/
    //返回用户反馈的聊天列表
    @GET("feedback/records")
    Observable<JsonObject> getFeekBack(@Header("Access-Token") String access_token, @Query("count") int count);

    //添加反馈内容
    @POST("feedback/create")
    Observable<JsonObject> postFeekBack(@Header("Access-Token") String access_token, @Body RequestBody requestBody);

    /*********************************课程更新*********************************************/
    @GET("upgrade/course")
    Observable<JsonObject> upgradeCourse(@Header("Access-Token") String access_token,
                                         @Query("cid") String cid, @Query("no") int no);
}
