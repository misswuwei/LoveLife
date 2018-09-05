package View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.jar.Attributes

/**
 * Created by Wuwei on 2018/8/1.
 */
class DrawPointView : View {
    var pointList: List<Int>? = null
    var mPaint: Paint? = null
    var mlinePaint : Paint? = Paint()
    var x: Float? = 0f
    var y: Float? = 0f

    init {
        mPaint = Paint()
        mPaint!!.strokeWidth = 10F
        mPaint!!.color = Color.BLACK

        mlinePaint?.color = Color.BLACK
        mlinePaint?.strokeWidth = 3F
    }

    constructor(ctx: Context) : this(ctx, null)
    constructor(ctx: Context, attr: AttributeSet?) : this(ctx, attr, -1)
    constructor(ctx: Context, attr: AttributeSet?, res: Int) : super(ctx, attr, -1)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //根据点的个数画点
        //画线
        canvas?.drawLine(0f,height!!/2.toFloat(),width.toFloat(),height!!/2.toFloat(),mPaint)
        //画圆
        x = x!! + (width / 7).toFloat()
        y = y!! + height/ 2 / 7
        for (i in 0 until 8) {
            canvas!!.drawCircle(x!! * i, y!! * (7-i), 5F, mPaint)
            Log.i("DrawPointView", "执行了" + x!! * i + "  " + y!! * (7-i))
        }

    }
}