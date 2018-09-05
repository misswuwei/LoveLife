package lovelife.xiangmu.wuwei.lovelife

import android.app.Activity
import android.os.Bundle
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Wuwei on 2018/8/1.
 */
open class BaseActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun setThread(observable: Observable<JsonObject>):Observable<JsonObject> = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
}