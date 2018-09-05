package netUtil;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 封装Retrofit的Observer结合Rxjava
 * 当发送网络请求时，返回的结果会先从onNext方法执行
 * Created by Wuwei on 2018/6/26.
 */

public abstract class baseObserver<T> implements Observer<JSONObject> {

    private JSONObject mJSONObject;
    private String TAG = "baseObserver";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JSONObject response) {
        try {
            mJSONObject = new JSONObject(response.toString());
            switch (mJSONObject.getInt("status")){
                case 200:
                    //请求成功
                    onHandlerSeccess(mJSONObject);
                    break;
                case 403:
                    //Token错误
                    onHandlerFail(mJSONObject);
                    break;
                case 400:
                    //错误的请求
                    onHandlerFail(mJSONObject);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //访问过程中的其他错误
    @Override
    public void onError(Throwable error) {
        Log.i(TAG, "onError: "+error.toString());
    }

    @Override
    public void onComplete() {

    }

    //暴漏在外部的请求成功时调用的方法
    public abstract void onHandlerSeccess(JSONObject response);

    //发送失败时调用的方法
    public abstract void onHandlerFail(JSONObject response);
}
