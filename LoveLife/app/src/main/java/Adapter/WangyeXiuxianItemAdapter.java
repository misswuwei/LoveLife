package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import Date.meiHomeXiuxian;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * 百度糯米休闲页面的Adapter
 * Created by Wuwei on 2018/6/27.
 */

public class WangyeXiuxianItemAdapter extends BaseQuickAdapter<meiHomeXiuxian,BaseViewHolder>{
    public WangyeXiuxianItemAdapter(int layoutResId, @Nullable List<meiHomeXiuxian> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final meiHomeXiuxian item) {
        helper.setText(R.id.home_xiuxian_name,item.getName());
        helper.setImageBitmap(R.id.home_xiuxian_image,item.getBitmap());
        helper.setText(R.id.home_xiuxian_comment,item.getComment());
        helper.setText(R.id.home_xiuxian_sale,item.getSale());
        helper.setOnClickListener(R.id.home_xiuxian_item,new View.OnClickListener() {
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
