package View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Wuwei on 2018/1/29.
 * 显示沉浸式滑动页面
 */

public class programScrollView extends ScrollView {
    //定义一个滑动监听
    private ScrollViewListener progarmListener = null;

    //实现两个参数的构造方法
    public programScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        if (progarmListener==null){
            progarmListener = scrollViewListener;
        }


    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (progarmListener!=null){
            progarmListener.onScrollChanged(this,l,t,oldl,oldt);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(programScrollView programScrollView, int l, int t, int oldl, int oldt);
    }
}
