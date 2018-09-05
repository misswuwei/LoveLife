package netUtil;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 封装一个网络请求的类（Retrofit+Rxjava2.0）
 * Created by Wuwei on 2018/6/26.
 */

public class RetrofitFactory {

    //base_url
    private String BASE_Url = "https://api.wupup.com/v1/";
    public static netService instance;
    public static RetrofitService sRetrofitService;


    /**
     * 构造方法,初始化Retrofit
     */
    public RetrofitFactory(){
        //添加拦截器和访问服务器需要的文件
        Interceptor interceptor = new Interceptor(){
            @Override
            public Response intercept(Chain chain) throws IOException {
                JSONObject object = new JSONObject();
                try{
                    //添加访问的手机的信息,方便做适配
                    object.put("os","android");
                    object.put("os-version","");
                    object.put("app-version","");
                    object.put("phone-model","");
                    object.put("ram","");
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                Request builder = chain.request().newBuilder().header("user-agrent","").build();
                return chain.proceed(builder);
            }
        };

        //初始化okHttp
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().
                addInterceptor(interceptor).//定义好的拦截器
                connectTimeout(60000, TimeUnit.MILLISECONDS).//链接超时时间
                readTimeout(5000,TimeUnit.MILLISECONDS).//读取超时时间
                writeTimeout(5000,TimeUnit.MILLISECONDS).//写入超时时间
                build();

        //初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_Url).
                addConverterFactory(GsonConverterFactory.create()).//将请求的结果转成实体类
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                client(okHttpClient).build();

        instance = retrofit.create(netService.class);
    }

    /**
     * 单例获取 retrofitService
     *
     * @return
     */
    public static netService getRetrofitService() {
        if (instance == null) {
            Log.i("RetrofitFactory", "getRetrofitService: 执行了");
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    new RetrofitFactory();
                }
            }
        }
        return instance;
    }

}
