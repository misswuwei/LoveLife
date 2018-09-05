package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Wuwei on 2018/7/6.
 */

public class RoundView extends RelativeLayout {
    public RoundView(Context context) {
        super(context);
    }

    public RoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        float cirX = width / 2;
        float cirY = width / 2;
        float radius = width / 2;
        canvas.drawCircle(cirX, cirY, radius, mPaint);
        super.onDraw(canvas);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }
}
