package View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Wuwei on 2018/1/1.
 */

    @SuppressLint("AppCompatCustomView")
    public class myButton extends Button {
        //设置画笔
    Paint mPaint = new Paint();
    public myButton(Context context) {
       this(context,null);
    }

    public myButton(Context context, AttributeSet attrs) {
        this(context, attrs,1);
    }

    public myButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#ffffff"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(0,0,getWidth()/2,mPaint);

    }
}
