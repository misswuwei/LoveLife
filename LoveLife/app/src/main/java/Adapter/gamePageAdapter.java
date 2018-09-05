package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * Created by Wuwei on 2018/5/3.
 */

public class gamePageAdapter extends PagerAdapter {
    private Context mContext;
    private List<Integer> mList;
    private LayoutInflater mLayoutInflater;
    private String[] path={"https://www.taptap.com/app/10056","https://www.taptap.com/app/57161","https://www.taptap.com/app/44764"};
    public gamePageAdapter(Context context, List<Integer> list){
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mLayoutInflater.inflate(R.layout.game_page,container,false);
        ImageView imageView = view.findViewById(R.id.game_page_icon);
        imageView.setBackgroundResource(mList.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("Uri", path[position]);
                intent.putExtra("Type","3");
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
