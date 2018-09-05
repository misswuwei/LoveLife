package Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Date.DateFather;
import Date.GameJsonDate;
import Date.NewsJsonDate;
import Date.ReviewData;
import Date.SprcialData;
import lovelife.xiangmu.wuwei.lovelife.HomeActivity;

import static android.widget.Toast.*;

/**
 * 抓取网页数据的工具类
 * Created by Wuwei on 2018/2/10.
 */

public class grabDateUtils {
    //存放游戏条目数据
    public static List<DateFather> GameDateList;
    //存放游戏条目数据
    public static List<DateFather> NewsDateList;
    //收藏游戏的集合
    public static List<DateFather> dateList = new ArrayList<>();
    //存放专题数据
    public static List<SprcialData> specialList = new ArrayList<>();

    //存放最新评论
    public static List<ReviewData> reviewList = new ArrayList<>();


    public static int windowsHeight;
    public static int windowsWeith;

    public static boolean Datefinish = true;
    public static boolean NewsDatefinish = true;
    public static boolean GameHomeDatefinish = true;

    public static void initDate() {
        OkHttpUtils.INSTANCE.getDataAsync();

        initGamedate();

        initNewsDate();

//        initHomeGameDate();

        OkHttpUtils.INSTANCE.getHomeMovie();

    }

    private static void initGamedate() {
        //存放游戏页面的数据
        //耗时操作在开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //打开一个链接拿到documet对象
                    Document document = Jsoup.connect("https://www.taptap.com/top/download").get();

                    //查找taptap网页并获取数据,<div class="top-card-left">包含图片和title
                    Elements srcAndtitle = document.select("div.top-card-left");
                    //<p class="card-middle-description">包含游戏context
                    Elements context = document.select("p.card-middle-description");
                    //包含游戏的flag <div class="card-tags">
                    Elements flag = document.select("div.card-tags");
                    if (GameDateList == null) {
                        GameDateList = new ArrayList<>();
                    }
                    for (int i = 0; i < 30; i++) {

                        GameJsonDate gameJsonDate = new GameJsonDate();
                        //获取标题
                        gameJsonDate.gameitem_name = srcAndtitle.get(i).select("a").select("img").attr("title");
                        String path = srcAndtitle.get(i).select("a").select("img").attr("src");
                        gameJsonDate.gameitem_img = getBitmap(path);
                        gameJsonDate.path = path;
                        int j = flag.get(i).childNodeSize()/2;
                        switch (j){
                            case 3:
                                gameJsonDate.game_flag1 = flag.get(i).child(0).text();
                                gameJsonDate.game_flag2 = flag.get(i).child(1).text();
                                gameJsonDate.game_flag3 = flag.get(i).child(2).text();
                                break;
                            case 2:
                                gameJsonDate.game_flag1 = flag.get(i).child(0).text();
                                gameJsonDate.game_flag2 = flag.get(i).child(1).text();
                                gameJsonDate.game_flag3 = "趣味";
                                break;
                            case 1:
                                gameJsonDate.game_flag1 = flag.get(i).child(0).text();
                                gameJsonDate.game_flag2 = "益智";
                                gameJsonDate.game_flag3 = "趣味";
                                break;
                            case 0:
                                gameJsonDate.game_flag1 = "休闲";
                                gameJsonDate.game_flag2 = "益智";
                                gameJsonDate.game_flag3 = "趣味";
                        }

//                        if (gameJsonDate.gameitem_name.equals("史上最坑爹的游戏16")){
//                            gameJsonDate.game_flag2 = "益智";
//                            gameJsonDate.game_flag3 = "趣味";
//                        }else {
//
//                        }

                        //获取游戏简介
                        gameJsonDate.gameitem_content = context.get(i).text();
                        //获取网址
                        gameJsonDate.Uri = srcAndtitle.get(i).select("a").attr("href");
                        GameDateList.add(gameJsonDate);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Datefinish = false;
            }
        }).start();
    }

    private static void initNewsDate() {
        //耗时操作在开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //打开一个链接拿到documet对象
                    Document document = Jsoup.connect("http://news.qq.com").get();

                    //查找腾讯新闻,<div class="Q-tpWrap">，包含链接和图片
                    Elements httpAndsrc = document.select("div.Q-tpWrap");

                    //查找腾讯新闻,<div class="text">，包含标题,链接
                    Elements titleanhttp = document.select("div.text");
                    if (NewsDateList == null) {
                        NewsDateList = new ArrayList<>();
                    }
                    for (int i = 0; i < 30; i++) {
                        NewsJsonDate newsJsonDate = new NewsJsonDate();
                        //赋予标题
                        newsJsonDate.newsText = titleanhttp.get(i).select("em").select("a").text();
                        //赋予图片
                        String path = httpAndsrc.get(i).select("a").select("img").attr("src");
                        newsJsonDate.path = path;
                        newsJsonDate.newsimage = getBitmap(path);
                        //赋予链接
                        newsJsonDate.Uri = httpAndsrc.get(i).select("a").attr("href");
                        NewsDateList.add(newsJsonDate);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                NewsDatefinish = false;
            }
        }).start();
    }

    //获取taptap首页数据
    public static void initHomeGameDate(final List<GameJsonDate> ShouyeDateList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //打开一个链接拿到documet对象
                try {
                    Document document = Jsoup.connect("https://www.taptap.com").get();
                    //首页游戏条目
                    Elements name = document.select("h2");
                    Elements src = document.select("a.feed-rec-image");
                    Elements context = document.select("p.index");
                    Elements Uri = document.select("div.feed-rec-header");

                    //专题数据
                    Elements specialItem = document.select("ul.index-event-body");
                    Elements specialUrl = specialItem.select("a");
                    Elements specialImg = specialItem.select("img");//图片和名称

                    //最新评价
                    Document anli = Jsoup.connect("https://www.taptap.com/review/new").get();
                    Elements reviewAll = anli.select("div.taptap-review-block");
                    Elements appInfo = reviewAll.select("div.review-block-app");
                    Elements reviewBody = reviewAll.select("div.block-contents-text");
                    Elements reviewOrder = reviewAll.select("p.block-contents-author");

                    //初始化游戏条目数据
                    for (int i = 2;i<name.size();i++){
                        GameJsonDate gameJsonDate = new GameJsonDate();
                        gameJsonDate.gameitem_name = name.get(i).text();
                        gameJsonDate.gameitem_img = getBitmap(src.get(i).select("img").attr("data-src"));
                        gameJsonDate.gameitem_content = context.get(i).text();
                        gameJsonDate.Uri = Uri.get(i).select("a").attr("href");
                        ShouyeDateList.add(gameJsonDate);

                    }

                    //初始化首页专题数据
                    for (int i = 0; i < specialUrl.size() ; i++ ){
                        SprcialData sprcialData = new SprcialData();
                        sprcialData.setUrl(specialUrl.get(i).attr("href"));
                        sprcialData.setBitmap(getBitmap(specialImg.get(i).attr("src")));
                        specialList.add(sprcialData);
                    }

                    //初始化最新评价数据
                    for (int i = 0; i < appInfo.size(); i++){
                        ReviewData reviewData = new ReviewData();
                        reviewData.setAppName(appInfo.get(i).select("a.flex-text").text());
                        reviewData.setAppUrl(appInfo.get(i).select("a.flex-text").attr("href"));
                        reviewData.setBitmap(getBitmap(appInfo.get(i).select("img").attr("src")));
                        reviewData.setItemUrl(reviewBody.get(i).select("a").attr("href"));
                        reviewData.setReviewContent(reviewBody.get(i).select("a").text());
                        reviewData.setUserName(reviewOrder.get(i).text());
                        reviewList.add(reviewData);
                    }
                    GameHomeDatefinish = false;
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
            Bitmap bitmap = null;
            if (conn.getContentLength()>100000)//获取下载文件的大小
            {
                Log.i("", "getBitmap: "+conn.getContentLength());
                BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = conn.getContentLength()/50000;//缩放比例
                    bitmap = BitmapFactory.decodeStream(inputStream,null,options);
                    options.inBitmap = bitmap;
            }else{
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            inputStream.close();

            return bitmap;
        }
        return null;
    }

}

