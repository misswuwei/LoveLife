package View

import Utils.ThreadPoolUtil
import Utils.UIUtil
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import lovelife.xiangmu.wuwei.lovelife.R


/**
 * Created by Wuwei on 2018/9/4.
 */
class HintLayout : LinearLayout{
    var array: ArrayList<String>? = null
    var mContext : Context? = null
    var width: Int? = 0
    var height: Int? = 0
    var left: Int? = 26


    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,-1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        mContext = context
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }
//
//    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
    fun addData(data : ArrayList<String>){

        array = data
        for ( i in 0..data.size-1){
            var textview = getTextView(data.get(i))
            textview.measure(0,0)
            val layoutParams = LinearLayout.LayoutParams(textview.measuredWidth, textview.measuredHeight)
            layoutParams.topMargin = textview.measuredHeight * i
            layoutParams.leftMargin = left!!
            left = -textview.measuredWidth
            addView(textview,layoutParams)
        }
    }

    fun getTextView(string : String): TextView {
        var textview :TextView = TextView(mContext)
        textview.setText(string)
        textview.setTextColor(mContext!!.getResources().getColor(R.color.my_text_gray))
        textview.setTextSize(2, 13F)
        val leftDrawable = mContext!!.getResources().getDrawable(R.drawable.ic_circle_find_label)
        leftDrawable.setBounds(0, 0, leftDrawable.minimumWidth, leftDrawable.minimumHeight)
        textview.setCompoundDrawables(leftDrawable, null, null, null)
        textview.setCompoundDrawablePadding(10)
        return textview
    }

}