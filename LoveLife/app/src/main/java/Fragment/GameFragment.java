package Fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import Adapter.mRecycAdaper;
import Adapter.gamePageAdapter;
import Date.DateFather;
import Date.GameJsonDate;
import Utils.grabDateUtils;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/1/25.
 */

public class GameFragment extends Fragment {
    View mView;
    private RecyclerView mRecyclerView;
    private List<GameJsonDate> lists;
    private Adapter.mRecycAdaper mRecycAdaper;
    private List<Integer> types = new ArrayList<>();
    private UltraViewPager mUl_page;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView= inflater.inflate(R.layout.fragment_game,null);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup viewGroup = (ViewGroup) mView.getParent();
        if (viewGroup!=null){
            viewGroup.removeView(mView);
        }else{
            initView(mView);

            initRecycle();

            initDate();
        }

        return mView;
    }

    private void initDate() {
        setPage();

        for (int i = 0;i<grabDateUtils.GameDateList.size();i++){
            types.add(DateFather.TYPE_Game);
        }

        //抓取网络数据加载在游戏主页
        mRecycAdaper.AddList(grabDateUtils.GameDateList,types,30);
        mRecycAdaper.notifyDataSetChanged();

    }

    private void setPage() {
        mUl_page.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

    //内置indicator初始化
        mUl_page.initIndicator();
    //设置indicator样式
        mUl_page.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        mUl_page.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //构造indicator,绑定到UltraViewPager
        mUl_page.getIndicator().build();
        //设定页面循环播放
        mUl_page.setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        mUl_page.setAutoScroll(4000);
        mUl_page.setMultiScreen(0.8f);
        mUl_page.setAutoMeasureHeight(true);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.game001);list.add(R.mipmap.tap1);list.add(R.mipmap.tap2);
        mUl_page.setAdapter(new gamePageAdapter(getActivity(),list));
    }

    private void initRecycle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecycAdaper = new mRecycAdaper(getActivity());
        mRecyclerView.setAdapter(mRecycAdaper);
    }

    private void initView(View view) {
        mRecyclerView = mView.findViewById(R.id.game_recycle);
        mUl_page = mView.findViewById(R.id.ul_page);
    }

}

