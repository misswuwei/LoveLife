package netUtil;

import io.reactivex.Observable;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 将所有的网络请求都封装在netService
 * Created by Wuwei on 2018/6/26.
 */

public interface netService {

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

    //获取商店最新列表
    @GET("shop-course/index-newest")
    io.reactivex.Observable<JsonObject> getShopCouse(@Header("Access-token") String access_token, @Query("expand") String expand);
}
