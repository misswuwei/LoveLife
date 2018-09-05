package Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Date.DateFather;
import Date.FoodJsonDate;
import Date.WeatherJsonDate;
import Date.meiHomeMarry;
import Date.meiHomeMovie;
import Date.meiHomeXiuxian;
import okhttp3.*;

/**
 * Created by Wuwei on 2018/2/24.
 */

public enum OkHttpUtils {
    INSTANCE;
    //封装类
    private OkHttpClient mOkHttpClient;
    public String path;
    private Context mContext;
    public static List<DateFather> weatherDate;
    public static List<meiHomeMovie> HomeMovieData = new ArrayList<>();
    public static List<meiHomeXiuxian> HomeXiuXianData = new ArrayList<>();
    public static List<meiHomeMarry> HomeMarryData = new ArrayList<>();
    private Document FoodDocument;

    //下载文件的方法
    public void download(String Url, final String filepath, final Context context) {
        mContext = context;

        //拿到Request
        final Request request = new Request.Builder().url(Url).build();

        mOkHttpClient = new OkHttpClient();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //SD卡能用
                    path = Environment.getExternalStorageDirectory().getAbsolutePath();
                } else {
                    path = context.getFilesDir().getAbsolutePath();
                }
                File file = new File(path, "download.apk");
                if (file != null) {
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[2048];
                    int lenth = 0;
                    while ((lenth = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, lenth);
                    }
                    fileOutputStream.flush();
                    inputStream.close();
                    installApk(file);
                }
            }
        });
    }

        protected void installApk(File file) {
            Log.i("开始安装", "installApk: ");
            // TODO Auto-generated method stub
            //调用系统安装apk的方法，使用隐式意图进行配对
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            //设置安装文件的路径
            intent.setData(Uri.fromFile(file));

            //设置安装文件的类型
            intent.setType("application/vnd.android.package-archive");

            //使用第二种方法开始activity，为了让用户点击返回后不停留在版本更新页面,
            mContext.startActivity(intent);
        }



    public void getDataAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://wea.uc.cn/v2/get_weather_express.php?v=2&uc_param_str=dnvebipf&dn=17278618012-bc8209e1&ve=11.8.6.966&bi=999&pf=145&vkey=@ucbrowser@&date=2018-02-10&vcode=67aa7a140c96af617e3507e40650eae1&sel_city=AAOJJ4YKPxungc0%2BMNUL2Ujj").get();
                    String s1 = document.select("body").text();
                    String s2 = s1.substring(s1.indexOf("["),s1.indexOf(",\"past\""));
                    JSONArray jsonArray = new JSONArray(s2);

                    if (weatherDate == null){
                        weatherDate = new ArrayList<>();
                    }

                    for (int i = 0;i < 3;i++){
                        JSONObject jsonObjects = jsonArray.getJSONObject(i);
                        WeatherJsonDate weatherJsonDate = new WeatherJsonDate();
                        weatherJsonDate.date =jsonObjects.getString("date");
                        weatherJsonDate.weather=jsonObjects.getString("desc");
                        weatherJsonDate.high_temper =jsonObjects.getString("high_temper");
                        weatherJsonDate.low_temper =jsonObjects.getString("low_temper");
                        weatherDate.add(weatherJsonDate);
//                        Log.i("zz", "run: "+weatherJsonDate.date+"  "+weatherJsonDate.weather+"  "+weatherJsonDate.high_temper+weatherJsonDate.low_temper);
//                        int humidity =jsonObjects.getInt("humidity");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //获取百度糯米首页信息
    public void getHomeMovie() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("https://bj.nuomi.com/").get();
                    Elements all = document.select("ul.item-list");//所有条目数据
                    //电影
                    Elements movieName = all.select("div.movie-title");
                    Elements movieImage = all.select("img.movie-image");
                    Elements allUrl = all.select("a");
                    //休闲
                    Elements xiuName = all.select("div.yule-title");
                    Elements xiuImage = all.select("img.yule-image");
                    Elements xiuComment = all.select("span.comment");
                    Elements xiuSale = all.select("span.has-sale");
                    //结婚
                    Elements travelName = all.select("div.word-main");
                    Elements travelHint = all.select("div.word-title");
                    Elements travelImg = all.select("img");

                    //获取标题链接
                    Elements itemUrl = document.select("li.nav-item");

                    for (int i=0 ; i< movieName.size() ; i++){
                        meiHomeMovie meiHomeMovie = new meiHomeMovie();
                        meiHomeMovie.setName(movieName.get(i).text());
                        meiHomeMovie.setUrl("http:"+allUrl.get(i).attr("href").toString());
                        meiHomeMovie.setBitmap(getBitmap(movieImage.get(i).attr("src").toString()));
                        HomeMovieData.add(meiHomeMovie);
                    }
                    //休闲数据
                    for (int i =0; i<xiuName.size() ; i++){
                        meiHomeXiuxian xiuxian = new meiHomeXiuxian();
                        xiuxian.setName(xiuName.get(i).text());
                        xiuxian.setUrl(allUrl.get(movieImage.size()+i).attr("href").toString());//注意索引值
                        xiuxian.setBitmap(getBitmap(xiuImage.get(i).attr("src").toString()));
                        xiuxian.setComment(xiuComment.get(i).text());
                        xiuxian.setSale(xiuSale.get(i).text());
                        HomeXiuXianData.add(xiuxian);
                    }
                    //旅游数据
                    for (int i = 0; i < travelName.size() ; i++){
                        meiHomeMarry marry = new meiHomeMarry();
                        marry.setName(travelName.get(i).text());
                        marry.setHint(travelHint.get(i).text());
//                        String uri = travelImg.get(xiuName.size()+travelName.size()-1+i).attr("src").toString();
                        marry.setBitmap(getBitmap("http:"+travelImg.get(xiuName.size()+travelName.size()-1+i).attr("src").toString()));//注意索引值
                        marry.setUri(allUrl.get(xiuName.size()+travelName.size()+4+i).attr("href").toString());
                        HomeMarryData.add(marry);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static Bitmap getBitmap(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        }
        return null;
    }
}


