package View

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import lovelife.xiangmu.wuwei.lovelife.R
import Date.WeatherItemData;
import android.util.Log

/**
 * 首页天气预报的条目控件
 * Created by Wuwei on 2018/7/6.
 */
class WeatherYubaoItem : RelativeLayout {

    var mContext:Context? = null
    var mView : View? = null
    var day : TextView? = null
    var weatherIcon : ImageView? = null
    var weatherText : TextView? = null
    var weatherWendu : TextView? = null
    var weatherFengli : TextView? = null

    constructor(ctx : Context) : this(ctx,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,-1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int ) : super(context, attrs, defStyleAttr){
        mView = View.inflate(context,R.layout.home_weather_yubao_item,this)
        initView()
        Log.i("WeatherYubaoItem","执行了")
        mContext = context
    }

    fun initView(){
        day = mView?.findViewById<TextView>(R.id.weather_yubao_today)
        weatherIcon = mView?.findViewById<ImageView>(R.id.weather_yubao_today_icon)
        weatherText = mView?.findViewById<TextView>(R.id.weather_yubao_today_wer)
        weatherWendu = mView?.findViewById<TextView>(R.id.weather_yubao_today_wendu)
        weatherFengli = mView?.findViewById<TextView>(R.id.weather_yubao_today_fengli)
    }

    fun initDate(item : WeatherItemData?){
        Log.i("initDate","执行了")
        day?.setText(item?.day)
        weatherWendu?.setText(item?.wendu)
        weatherText?.setText(item?.wertherText)
        weatherFengli?.setText(item?.fengli)
        weatherIcon?.setImageBitmap(item?.bitmap)
    }
}