package lovelife.xiangmu.wuwei.lovelife;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import netUtil.RetrofitFactory;
import netUtil.baseObserver;

/**
 * Created by Wuwei on 2018/5/12.
 */

public class AboutAvtivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


    }
}
