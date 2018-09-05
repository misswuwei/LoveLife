package Adapter;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import Date.FoodJsonDate;
import Date.TypeData;
import Date.meiHomeMovie;
import Utils.OkHttpUtils;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/5/2.
 */

public class MyFoodRecycAdapter extends BaseMultiItemQuickAdapter<TypeData, BaseViewHolder> {

    private final int HOME_MOVIE_TITLE = 0;
    public static final int HOME_MOVIE_ITEM = 1;
    private final int HOME_XIUXIAN_ITEM = 2;
    private final int HOME_MARRY_ITEM = 3;

    private Context mContext;

    public MyFoodRecycAdapter(@Nullable List<TypeData> data, Context context) {
        super(data);
        addItemType(HOME_MOVIE_ITEM, R.layout.wangye_hone_movie_recycler);
        addItemType(HOME_MOVIE_TITLE, R.layout.wangye_home_title);
        addItemType(HOME_XIUXIAN_ITEM, R.layout.wangye_hone_movie_recycler);
        addItemType(HOME_MARRY_ITEM, R.layout.wangye_hone_movie_recycler);
        mContext = context;
    }


    //初始化布局
    @Override
    protected void convert(BaseViewHolder viewHolder, TypeData item) {
        switch (viewHolder.getItemViewType()) {
            case HOME_MOVIE_ITEM:
                initMovieItem(viewHolder);
                break;
            case HOME_MOVIE_TITLE:
                initTitle(viewHolder, viewHolder.getLayoutPosition());
                break;
            case HOME_XIUXIAN_ITEM:
                initXiuXianItem(viewHolder);
                break;
            case HOME_MARRY_ITEM:
                initMarryItem(viewHolder);
        }

    }

    private void initMarryItem(BaseViewHolder viewHolder) {
        RecyclerView recyclerView = viewHolder.getView(R.id.home_movie_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        WangyeMarryAdapter adapter = new WangyeMarryAdapter(R.layout.item_home_marry, OkHttpUtils.HomeMarryData);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);
    }

    private void initXiuXianItem(BaseViewHolder viewHolder) {
        RecyclerView recyclerView = viewHolder.getView(R.id.home_movie_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        WangyeXiuxianItemAdapter adapter = new WangyeXiuxianItemAdapter(R.layout.item_home_xiuxian, OkHttpUtils.HomeXiuXianData);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);
    }

    private void initTitle(BaseViewHolder viewHolder,int posotion) {
        if (posotion == 0) {
            viewHolder.setText(R.id.title_name,"院线上映");
        }else{
            viewHolder.setText(R.id.title_name,"休闲娱乐");
        }

    }

    private void initMovieItem(BaseViewHolder viewHolder) {
        RecyclerView recyclerView = viewHolder.getView(R.id.home_movie_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        WangyeMovieItemAdapter adapter = new WangyeMovieItemAdapter(R.layout.item_food, OkHttpUtils.HomeMovieData);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);
    }
}