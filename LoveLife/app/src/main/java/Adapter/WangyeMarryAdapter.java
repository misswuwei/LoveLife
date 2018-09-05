package Adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import Date.meiHomeMarry;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/6/27.
 */

public class WangyeMarryAdapter extends BaseQuickAdapter<meiHomeMarry,BaseViewHolder> {
    public WangyeMarryAdapter(int layoutResId, @Nullable List<meiHomeMarry> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final meiHomeMarry item) {
        helper.setText(R.id.home_marry_name,item.getName());
        helper.setText(R.id.home_marry_hint,item.getHint());
        helper.setImageBitmap(R.id.home_marry_image,item.getBitmap());
        helper.setOnClickListener(R.id.home_marry_recycler, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("Uri", item.getUri());
                intent.putExtra("Type", "3");
                mContext.startActivity(intent);
            }
        });

    }
}
