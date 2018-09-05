package View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.TextView

/**
 * 以及管家记忆自定义展示曲线的demo
 * Created by Wuwei on 2018/7/30.
 */
class CurveView: TextView {
    var mPaint : Paint
    var linePaint : Paint

    constructor(ctx : Context) : this(ctx,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,-1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int ) : super(context, attrs, defStyleAttr){
        mPaint =  Paint()
        mPaint.textSize = 8F
        mPaint.color = Color.GRAY

        linePaint = Paint()
        linePaint.strokeWidth = 8F
        linePaint.color = Color.GRAY

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        //画线
        canvas?.drawLine(10f, height.toFloat()/2,width.toFloat()*6/7,height.toFloat()/2,linePaint)
        //画圆
        for (i in 0 until 7){
            canvas?.drawCircle(i*(width.toFloat()/7),height.toFloat()/2,15f,mPaint)
        }
        //画弧线
        var circlePaint = Paint()
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = 8F
        circlePaint.color = Color.GRAY
//        canvas?.drawCircle(width.toFloat()*6/7,height.toFloat()/2+60,60F,circlePaint)
        canvas?.drawArc(width.toFloat()*6/7-60,height.toFloat()/2,width.toFloat()*6/7+60,height.toFloat()/2+120,-90F,180F,false,circlePaint)
        super.onDraw(canvas)

    }
}