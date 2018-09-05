package netUtil

import android.util.Log
import com.google.gson.JsonObject
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *网路请求回调的类
 * Created by Wuwei on 2018/8/1.
 */
abstract class BaseObserver : Observer<JsonObject> {

    override fun onSubscribe(d: Disposable) {
    }

    override fun onComplete() {
    }

    override fun onNext(request : JsonObject) {
        Log.i("onNext","执行了"+request.toString())
        if (request.get("status").toString().equals("200")) onSuccess(request) else onFail(request)
    }

    override fun onError(e: Throwable) {
        Log.i("onError","BaseObserver执行了"+e.message)
    }


    abstract fun onSuccess(requeas : JsonObject)
    abstract fun onFail(requeas : JsonObject)

}