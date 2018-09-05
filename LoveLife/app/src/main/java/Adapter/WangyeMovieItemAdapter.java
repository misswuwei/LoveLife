package Adapter;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import Date.meiHomeMovie;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/6/27.
 */

public class WangyeMovieItemAdapter extends BaseQuickAdapter<meiHomeMovie,BaseViewHolder> {
    public WangyeMovieItemAdapter(int layoutResId, @Nullable List<meiHomeMovie> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder,final meiHomeMovie item) {
        viewHolder.setImageBitmap(R.id.home_movie_image, item.getBitmap());
        viewHolder.setText(R.id.home_movie_name, item.getName());
        viewHolder.setOnClickListener(R.id.rl_home_movie, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("Uri", item.getUrl());
                intent.putExtra("Type", "3");
                mContext.startActivity(intent);
            }
        });
    }
}
