package View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Wuwei on 2018/1/25.
 */

public class MyScrollView extends ScrollView {
    private ScrollViewListener mScrollviewListener =null;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //坐标变化的方法
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollviewListener!=null){
            mScrollviewListener.onScrollChanged(this,l,t,oldl,oldt);
        }
    }

    protected void SetScrollViewListener(ScrollViewListener ScrollviewListener) {
        mScrollviewListener = ScrollviewListener;
    }

    public interface ScrollViewListener{
        void onScrollChanged(MyScrollView scrollView,int l, int t, int oldl, int oldt);
    }
}
