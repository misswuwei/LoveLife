package Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Adapter.mRecycAdaper;
import Date.DateFather;
import Date.NewsJsonDate;
import lovelife.xiangmu.wuwei.lovelife.R;
import View.programScrollView;
import Adapter.mViewpageAdapter;
import Utils.grabDateUtils;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/1/18.
 */

public class NewsFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private View mView = null;
    private List<DateFather> list;

    private mRecycAdaper mMRecycAdaper;
    private ImageView imageView;
    private programScrollView scrollView;
    private TextView toolbar_title;
    private Toolbar toolbar;
    private int height;

    private int[] newsRescous={R.mipmap.news001, R.mipmap.news002, R.mipmap.news003,R.mipmap.news004, R.mipmap.news005};
    private String[] newsTitle={"阿里云ET城市大脑落地马来西亚","EA新作 极品飞车17燃爆你的夏天","一加超越三星，成为外媒最受欢迎的手机品牌","生活在高速发展，心灵却越来越孤独","中国电信或将最早支持5g",};
    private String[] newsPath={"http://www.sohu.com/a/219942357_320333","http://www.yxdown.com/news/201205/54519.html","http://tech.ifeng.com/a/20171101/44740431_0.shtml",
    "http://www.sxgh.org.cn/particular.aspx?id=9807","http://www.techweb.com.cn/world/2017-08-29/2579799.shtml"};
    private LinearLayout mPointLinear;
    private ViewPager mViewPager;
    private List<ImageView> mImageViewslists;
    private TextView mPageNewsTitleview;
    private int disable =0;
    private boolean isrunning =false;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mNews_recycle;
    private ScrollView mScrollView;
    private List<Integer> types = new ArrayList<>();
    private int index;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 3){
               initDate();
                //初始化viewpageAdapter
                initPageAdapter();

                //swipeRefreshLayout处理下拉刷新的逻辑
                initSwipRefreshLayout();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragmant_news,null);
            //设置recyc
            initViews(mView);

//            //初始化数据
//            initDate();
        }

        initDates();

        return mView;

    }

    private void initDates() {
        mSwipeRefreshLayout.setRefreshing(grabDateUtils.NewsDatefinish);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (grabDateUtils.NewsDatefinish){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("w", "run: "+"777");
                }
                mHandler.sendEmptyMessage(3);
            }
        }).start();

    }

    private void initSwipRefreshLayout() {
        // 设置下拉进度的背景颜色，默认就是白色的
        //mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        //mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        // mSwipeRefreshLayout.setEnabled(false);
        //下拉时触发这个方法，先执行SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                //让recycview添加条目
//                NewsJsonDate newsJsonDate = new NewsJsonDate();
//                newsJsonDate.newsText = "已刷新数据";
//                newsJsonDate.type = 1;
                for(int i = 0;i < 3;i++){
                    grabDateUtils.NewsDateList.add(i+1,grabDateUtils.NewsDateList.get(29-i));
                }
                mMRecycAdaper.additem(grabDateUtils.NewsDateList);


                //设置refresh的状态
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        /************困扰了我两天的问题（一定要谨记）**********
         * 首先，没有swiperefreshlayout的时候，使用scrollview嵌套recyclecveiw时会产生滑动冲突，解决方法时使用RelativeLayout
         * 包裹住Recyclerview并设置android:descendantFocusability="blocksDescendants"
         * 2
         * 在新闻页面添加swiperefresh后发现不在顶部下拉还是会执行刷新。找了很多方法后发现原因是页面swiperefreshlayout
         * 嵌套了srollview导致二者滑动冲突。
         * 解决的方法是获取scrollview的滑动距离，当滑动距离等于0的时候说明页处于顶部，这时在将刷新状态设置为true，当滑动距离
         * 大于0的时候在将swiperefresh的状态设置为false禁止其实现刷新
         *
         */
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new  ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                mSwipeRefreshLayout.setEnabled(mScrollView.getScrollY()==0);

            }
        });


    }


    private void initPageAdapter() {
        mViewPager.setAdapter(new mViewpageAdapter(mImageViewslists));

        //设置定时器实现无限轮播
        startTimer();

    }

    private void startTimer() {
        isrunning = true;
        final Activity activity = getActivity();
        new Thread(){
            @Override
            public void run() {
                while(isrunning){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                        }
                    });
                }

            }
        }.start();

    }

    public void initDate() {
//        if (list==null){
//            list = new ArrayList<>();
//        }
//        for(int i =0;i < 20;i++){
//            NewsJsonDate newsJsonDate = new NewsJsonDate();
//            newsJsonDate.type =1;
//            newsJsonDate.newsText = "为什么执意要取消取消了3.5mm耳机孔?";
//            newsJsonDate.newsimage = R.mipmap.news1;
//            newsJsonDate.newSource = "新华社 1243评";
//            list.add(newsJsonDate);
//        }
//        mMRecycAdaper.AddList(list,DateFather.TYPE_NEWSONE);
//        mMRecycAdaper.notifyDataSetChanged();
//
        //初始化要展示的imageview
        ImageView imageView;
        mImageViewslists = new ArrayList<>();
        View pointview;
        LinearLayout.LayoutParams params;
        for(int i=0;i<5;i++){
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(newsRescous[i]);
            final int I = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("Uri", newsPath[I]);
                    intent.putExtra("Type","1");
                    startActivity(intent);
                }
            });
            mImageViewslists.add(imageView);

            //添加小白点，指示器（自己通过xml文件绘制一个圆形）
            pointview = new View(getActivity());
            pointview.setBackgroundResource(R.drawable.viewpage_point_bg);
            params = new LinearLayout.LayoutParams(10,10);//指定宽高
            if (i != 0) {
                params.leftMargin = 10;
            }if (i == 0){
                pointview.setEnabled(true);
            }else{
                pointview.setEnabled(false);
            }
            mPointLinear.addView(pointview,params);
        }
        //设置数据
        for (int i = 0;i<30;i++){
            types.add(DateFather.TYPE_NEWSONE);
        }
        mMRecycAdaper.AddList(grabDateUtils.NewsDateList,types,30);
        mMRecycAdaper.notifyDataSetChanged();
    }


    public void initViews(View view) {
        mPageNewsTitleview = view.findViewById(R.id.tv_pagenewstitle);
        //recyc
        mNews_recycle = view.findViewById(R.id.news_recycle);

        mNews_recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        /**解决Scrollview中嵌套Recycview时会发生滑动冲突的问题
         * 解决方法如下：
         * 1.在Recycview的外部加入Relativelayout并添加android:descendantFocusability="blocksDescendants"属性
         * 禁止子控件的滑动
         * 2.加入一下代码：news_recycle.setHasFixedSize(true);
         news_recycle.setNestedScrollingEnabled(false);setHasFixedSize(true)方法使得RecyclerView能够固定自身
         size不受adapter变化的影响；而setNestedScrollingeEnabled(false)方法则是进一步调用了RecyclerView内部
         NestedScrollingChildHelper对象的setNestedScrollingeEnabled(false)方法，*/
        mNews_recycle.setHasFixedSize(true);
        mNews_recycle.setNestedScrollingEnabled(false);
        mMRecycAdaper = new mRecycAdaper(getActivity());
        mNews_recycle.setAdapter(mMRecycAdaper);



        //viewpoage
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setOnPageChangeListener(this);

        mPointLinear = view.findViewById(R.id.ll_point);
        //scrollview
        mScrollView = view.findViewById(R.id.scrollview);

        //找到下拉刷新的控件
        mSwipeRefreshLayout = view.findViewById(R.id.swiprefresh);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滚动viewPage时调用
    }

    @Override
    public void onPageSelected(int position) {
        //由于条目有很多，需要做取余处理保证取到的条目数据不超出集合和数组
        int newPosition = position % newsRescous.length;
        //新的条目被选中时调用
        mPageNewsTitleview.setText(newsTitle[newPosition]);
        mPointLinear.getChildAt(disable).setEnabled(false);
        mPointLinear.getChildAt(newPosition).setEnabled(true);
        disable = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //当滚动状态被选中时调用
    }

    //当页面销毁时关闭计时器

    @Override
    public void onPause() {
        super.onPause();
        isrunning = false;
    }
}
