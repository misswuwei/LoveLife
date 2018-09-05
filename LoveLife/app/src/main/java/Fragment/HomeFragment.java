package Fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Date.WeatherItemData;
import View.WeatherYubaoItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * 首页展示的Fragment
 * Created by Wuwei on 2018/1/18.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.ll_home_layout)
    LinearLayout mLlHomeLayout;
    Unbinder unbinder;
    @BindView(R.id.weather_icon)
    ImageView mWeatherIcon;
    @BindView(R.id.weather_rl_today)
    WeatherYubaoItem mWeatherRlToday;
    @BindView(R.id.weather_rl_tomorrow)
    WeatherYubaoItem mWeatherRlTomorrow;
    @BindView(R.id.weather_after_tomorrow)
    WeatherYubaoItem mWeatherAfterTomorrow;
    @BindView(R.id.weather_location_icon)
    ImageView mWeatherLocationIcon;
    @BindView(R.id.weather_loaction_name)
    TextView mWeatherLoactionName;
    @BindView(R.id.weather_kongqi_iocn)
    RoundedImageView mWeatherKongqiIocn;
    @BindView(R.id.weather_wendu_text)
    TextView mWeatherWenduText;
    @BindView(R.id.weather_shidu_icon)
    ImageView mWeatherShiduIcon;
    @BindView(R.id.weather_shidu_text)
    TextView mWeatherShiduText;
    @BindView(R.id.weather_fengli_icon)
    ImageView mWeatherFengliIcon;
    @BindView(R.id.weather_fengli_text)
    TextView mWeatherFengliText;
    @BindView(R.id.weather_hint_item)
    TextView mWeatherHintItem;
    @BindView(R.id.weather_hint_text)
    TextView mWeatherHintText;
    @BindView(R.id.weather_tv_name)
    TextView mWeatherTvName;
    @BindView(R.id.weather_yubao_text)
    TextView mWeatherYubaoText;
    private View mView;
    private boolean load = true;
    private WeatherYubaoItem[] mYubaoItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home_layout, null);
        }
        initWeatherDate();
        unbinder = ButterKnife.bind(this, mView);
        mYubaoItem = new WeatherYubaoItem[]{mWeatherRlToday, mWeatherRlTomorrow, mWeatherAfterTomorrow};
        return mView;
    }

    public void initWeatherDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://tianqi.moji.com/weather/china/beijing/beijing").get();
                    //背景
                    final Drawable drawable = new BitmapDrawable(getBitmap(document.select("div").get(4).attr("data-url")));
                    setInfo(mLlHomeLayout, drawable);
                    //标题所有
                    Elements titleAll = document.select("div.wea_info");
                    setInfo(mWeatherWenduText, titleAll.select("div.wea_weather").select("em").text());
                    setInfo(mWeatherIcon, getBitmap(titleAll.select("img").attr("src")));
//                    setInfo(mWeatherTvName,titleAll.select(titleAll.select("img").attr("alt").toString()));
                    setInfo(mWeatherShiduText,titleAll.select("span").text());
                    setInfo(mWeatherHintText,titleAll.select("div.wea_tips").select("em").text());
                    setInfo(mWeatherFengliText,titleAll.select("div.wea_about").select("em").text());
                    Log.i("", "mata: " + titleAll.select("img").attr("alt").toString());

                    //天气图片
                    setBitmap(mWeatherIcon, getBitmap(document.select("div.grid").select("img").attr("src").toString()));
                    Elements yubaoAll = document.select("div.wrap").select("ul.days");
                    for (int i = 0; i < yubaoAll.size(); i++) {
                        WeatherItemData weatherItemData = new WeatherItemData();
                        weatherItemData.setBitmap(getBitmap(yubaoAll.get(i).select("img").attr("src")));
                        weatherItemData.setDay(yubaoAll.get(i).select("a").text());
                        weatherItemData.setWertherText(yubaoAll.get(i).select("img").attr("alt"));
                        String wendu = yubaoAll.get(i).select("li").text();
                        weatherItemData.setWendu(wendu.substring(5, 15));
                        weatherItemData.setFengli(yubaoAll.get(i).select("em").text() + yubaoAll.get(i).select("b").text());
                        setYuBaoData(mYubaoItem[i], weatherItemData);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    <T> void setInfo(final View view, final T info) {
        getActivity().runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                if (view instanceof LinearLayout) {
                    view.setBackground((Drawable) info);
                } else if (view instanceof TextView) {
                    ((TextView) view).setText((String) info);
                } else if (view instanceof ImageView) {
                    ((ImageView) view).setImageBitmap((Bitmap) info);
                }
            }
        });
    }

    private void setYuBaoData(final WeatherYubaoItem view, final WeatherItemData yubaoItem) {
        getActivity().runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                view.initDate(yubaoItem);
            }
        });

    }

    void setBitmap(final ImageView view, final Bitmap drawable) {
        getActivity().runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                view.setImageBitmap(drawable);
            }
        });
    }

    public static Bitmap getBitmap(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = null;
            if (conn.getContentLength() > 100000)//获取下载文件的大小
            {
                Log.i("", "getBitmap: " + conn.getContentLength());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = conn.getContentLength() / 50000;//缩放比例
                bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                options.inBitmap = bitmap;
            } else {
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            inputStream.close();
            return bitmap;
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
