package lovelife.xiangmu.wuwei.lovelife;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.view.ViewHelper;

import lovelife.xiangmu.wuwei.lovelife.EyeView;
import lovelife.xiangmu.wuwei.lovelife.R;


public class PullLayout extends ScrollView {

    private View rl_top;
    private View ll_weather;
    private View ll_content;
    private TextView tv;
    private EyeView ev;
    private ObjectAnimator oa;
    private float lastY = -1;
    private float detalY = -1;
    private int range;
    private int tvHeight;
    private int tvWidth;
    private boolean isTouchOrRunning;
    private boolean isActionCancel;

    public PullLayout(Context context) {
        super(context);
    }

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setVerticalScrollBarEnabled(false);
        rl_top = findViewById(R.id.rl_top);
        ll_content = findViewById(R.id.ll_content);
        tv = (TextView) findViewById(R.id.tv);
        ev = (EyeView) findViewById(R.id.ev);
        ll_weather = findViewById(R.id.ll_weather);

        /**OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被
         *  ViewTreeObserver监听到，这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。
         *  ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
         */
       //当rl_top控件滑动时
        rl_top.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                /**移除之前已经注册的视图绘制回调函数。
                 *参数 victim 将要被移除的回调函数
                 *异常 IllegalStateException       如果isAlive() 返回false
                 */
                rl_top.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //获取天气控件的高
                range = rl_top.getHeight();
                //设置最大可滑动距离
                scrollTo(0, range);
                //设置控件的高
                rl_top.getLayoutParams().height = range;
            }
        });
        tv.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                tv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                tvHeight = tv.getHeight();
                tvWidth = tv.getWidth();
                ViewHelper.setTranslationY(ll_content, tvHeight);
            }
        });

        ev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });

    }


    /**onInterceptTouchEvent()是用于处理事件（类似于预处理，当然也可以不处理）并改变事件的传递方向，也就是决定是否允许Touch事件继续向下（子控件）传递，返回
     * True（代表事件在当前的viewGroup中会被处理），则向下传递之路被截断（所有子控件将没有机会参与Touch事件），同时把事件传递给当前的控件的onTouchEvent()处理*/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isActionCancel = false;
                isTouchOrRunning = true;
                lastY = ev.getY();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (oa != null && oa.isRunning()) {
            //给眼睛view添加Action
            ev.setAction(MotionEvent.ACTION_UP);
            isActionCancel = true;
        }
        if (isActionCancel && ev.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        if (ev.getActionIndex() != 0 && getScrollY() < range) {
            ev.setAction(MotionEvent.ACTION_UP);
            isActionCancel = true;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                isTouchOrRunning = true;
                if (getScrollY() != 0) {
                    detalY = 0;
                    lastY = ev.getY();
                } else {
                    detalY = ev.getY() - lastY;
                    if (detalY > 0) {
                        setT((int) -detalY / 5);
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isTouchOrRunning = false;
                if (getScrollY() < range) {
                    if (detalY != 0) {
                        reset();
                    } else {
                        toggle();
                    }
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //这里绘制ScrollChanged的左上
        super.onScrollChanged(l, t, oldl, oldt);
        //当滑动距离top高于控件高度时，不响应其他事件
        if (t > range) {
            return;
        } else if (!isTouchOrRunning && t != range) {
            scrollTo(0, range);
        } else {
            animateScroll(t);
        }
    }

    public void setT(int t) {
        scrollTo(0, t);
        if (t < 0) {
            animatePull(t);
        }
    }

    private void animateScroll(int t) {
        float percent = (float) t / range;
        //给天气控件设置动画
        ViewHelper.setTranslationY(rl_top, t);
        /** 关于 ViewHelper.setTranslationY（view,float)函数的解释。这里的view 是您要移动哪个View 就是
         * 哪个东西你要将他在界面上进行活动呢？ float是指你移动的距离 ，（假定参考坐标 最开始的位置中心位
         * 置为0）。那么ViewHelper.setTranslationY(view,100)就是把view向下（比最原始的位置）移动100*/
        ViewHelper.setTranslationY(ll_content, tvHeight * percent);
        ViewHelper.setScaleX(tv, 2 - percent);
        ViewHelper.setScaleY(tv, 2 - percent);
        ViewHelper.setTranslationX(tv, tvWidth * (1 - percent) / 2f);
        ViewHelper.setTranslationY(tv, t + tvHeight * (1 - percent) / 2f);
        ViewHelper.setTranslationY(ev, -t / 2);
        ViewHelper.setTranslationY(ll_weather, -t / 2);
        ev.setRadius((int) (range * 0.25f * (1 - percent)));
        tv.setTextColor(evaluate(percent, Color.WHITE, Color.BLACK));
    }

    private void animatePull(int t) {
        rl_top.getLayoutParams().height = range - t;
        rl_top.requestLayout();
        float percent = (float) t / range;
        ViewHelper.setScaleX(ev, 1 - percent);
        ViewHelper.setScaleY(ev, 1 - percent);
        ViewHelper.setScaleX(tv, 2 - percent);
        ViewHelper.setScaleY(tv, 2 - percent);
        ViewHelper.setTranslationX(tv, tvWidth * (1 - percent) / 2f);
        ViewHelper.setTranslationY(ll_weather, t / 2);
    }

    private Integer evaluate(float fraction, Object startValue, Integer endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }

    public void toggle() {
        if (isOpen()) {
            close();
        } else {
            open();
        }
    }

    private Status status;

    public enum Status {
        Open, Close;
    }

    public boolean isOpen() {
        return status == Status.Open;
    }

    private void reset() {
        if (oa != null && oa.isRunning()) {
            return;
        }
        oa = ObjectAnimator.ofInt(this, "t", (int) -detalY / 5, 0);
        oa.setDuration(150);
        oa.start();
    }

    public void close() {
        if (oa != null && oa.isRunning()) {
            return;
        }
        oa = ObjectAnimator.ofInt(this, "t", getScrollY(), range);
        oa.setInterpolator(new DecelerateInterpolator());
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isTouchOrRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isTouchOrRunning = false;
                status = Status.Close;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        oa.setDuration(250);
        oa.start();
    }

    public void open() {
        if (oa != null && oa.isRunning()) {
            return;
        }
        oa = ObjectAnimator.ofInt(this, "t", getScrollY(), (int) (-getScrollY() / 2.2f), 0);
        oa.setInterpolator(new DecelerateInterpolator());
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isTouchOrRunning = true;

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isTouchOrRunning = false;
                status = Status.Open;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        oa.setDuration(400);
        oa.start();
    }

}
