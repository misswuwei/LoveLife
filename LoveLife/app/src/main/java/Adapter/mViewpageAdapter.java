package Adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Wuwei on 2018/1/29.
 * 所以PageAdapter的使用其实就是三个方法：
 * 1.创建并返回需要显示的条目，将view添加到容器Viewpage中
 * 2.指定销毁的条目（当条目不可见时销毁）
 * 3.指定复用规则，归档写法
 */

public class mViewpageAdapter extends PagerAdapter{
    //定义了一个imageView数组
    private List<ImageView> mImageViews;

    public mViewpageAdapter(List<ImageView> mImageView){
        //自动生成构造函数存根
        mImageViews =mImageView;

    }
    @Override
    public int getCount() {
        //为了实现无限循环，这里设置一个比较大的值
        return mImageViews.size()*1000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        /**该方法决定了当手指滑动到下一个显示的条目又滑会原来的potion时是否需要复用view，需要返回一个判断规则
         * 源码推荐一下写法：直接判断其引用的地址是否相等*/
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //需要销毁的对象，固定写法
        container.removeView((View) object);

    }

    /**该函数返回需要展示的条目内容，创建条目
     * */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container：容器，也就是viewPager     positon：当前需要展示的条目位置

        //把需要展示的imageview添加到Viewgrounp中
        ImageView imageView = mImageViews.get(position % mImageViews.size());
        container.addView(imageView);

        return imageView;//该句必须重写，否则在源码中会报错
    }


}
