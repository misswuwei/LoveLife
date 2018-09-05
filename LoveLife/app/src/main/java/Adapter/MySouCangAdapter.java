package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import Utils.grabDateUtils;
import Utils.showPopupWindows;

import Date.DateFather;
import Date.NewsJsonDate;
import Date.info;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/5/13.
 */

public class MySouCangAdapter extends BaseQuickAdapter<info,BaseViewHolder> {
    private List<info> lists;
    private View mView;
    private Context mContext;
    private RelativeLayout mRl_item;

    public MySouCangAdapter(Context ctx,int layoutResId, @Nullable List<info> data) {
        super(layoutResId, data);
        mView = LayoutInflater.from(ctx).inflate(layoutResId,null);
        lists = data;
        mContext = ctx;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final info item) {
        helper.setText(R.id.soucang_title,item.getTitle());
        ImageView imageView = mView.findViewById(R.id.soucang_img);
//        imageView.setImageBitmap(((NewsJsonDate)grabDateUtils.NewsDateList.get(0)).newsimage);
        Glide.with(mContext).load(item.path).into((ImageView) helper.getView(R.id.soucang_img));
        helper.setOnClickListener(R.id.soucang_bt, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("Uri", item.Uri);
                intent.putExtra("Type","1");
                mContext.startActivity(intent);
            }
        });
        mRl_item = helper.getView(R.id.rl_item);
        mRl_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showPopupWindows.ShowDelectPopupWindow(helper.itemView,R.layout.view_deletepopupwindows,mContext,helper.getLayoutPosition());

                return false;
            }
        });
    }
}
