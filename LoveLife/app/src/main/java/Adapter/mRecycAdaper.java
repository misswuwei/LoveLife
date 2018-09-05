package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import Date.DateFather;
import Date.GameJsonDate;
import Date.NewsJsonDate;
import Date.info;
import Holder.FoodTypeHolder;
import Holder.GameTitleTypeHoder;
import Holder.GameTypeHoder;
import Holder.HomeGameitemType;
import Holder.NewsTitleTypeHoder;
import Holder.NewsTypeHolder;
import Holder.WeatherTypeHolder;
import OnClickListener.mRecycViewOnClickListener;

import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;
import Utils.showPopupWindows;

/**
 * Created by Wuwei on 2018/1/25.
 */

public class mRecycAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //接受传递的数据
    private List<? extends DateFather> lists = new ArrayList<>();
    private List<Integer> type;
    private int counts;
    private Context mContext;
    //记录差值
    private int cha = 0;

    public void AddList(List<? extends DateFather> list,List<Integer> types,int count){
        lists = list;
        type = types;
        counts = count;

    }
    private LayoutInflater mLayoutInflater;
    public mRecycAdaper(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    //根据类型返回相应的viewhoulder
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == DateFather.TYPE_NEWSONE){
            return new NewsTypeHolder(mLayoutInflater.inflate(R.layout.item_news, parent, false), new mRecycViewOnClickListener() {
                @Override
                public void onRecycViewItemClickListener(int position) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    if (counts == 15){
                        intent.putExtra("Uri",((NewsJsonDate)lists.get(position-1)).Uri);
                        intent.putExtra("Type","1");
                    }else{
                        intent.putExtra("Uri",((NewsJsonDate)lists.get(position)).Uri);
                        intent.putExtra("Type","1");
                    }

                    mContext.startActivity(intent);
                }

                @Override
                public void onRecycViewDownloadItemClickListener(int position) {

                }

                @Override
                public void onRecycViewLongClickListener(int position, View view, int type) {
                    if(counts == 15){
                        showPopupWindows.ShowPopupWindow(view,R.layout.view_popupwindows,mContext,29-position+5,type);
                    }else {
                        showPopupWindows.ShowPopupWindow(view,R.layout.view_popupwindows,mContext,position,type);
                    }
                }
            });
        }
        if (viewType == DateFather.TYPE_Game) {
            return new GameTypeHoder(mLayoutInflater.inflate(R.layout.fragment_game_item, parent, false), new mRecycViewOnClickListener() {
                @Override
                public void onRecycViewItemClickListener(int positon) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("Uri", ((GameJsonDate) lists.get(positon)).Uri);
                    intent.putExtra("Type","3");
                    mContext.startActivity(intent);
                }

                @Override
                public void onRecycViewDownloadItemClickListener(int position) {
                    showPopupWindows.ShowDownloadPopupWindow(View.inflate(mContext, R.layout.activity_home, null), R.layout.view_download_popupwindows, mContext, position, 3);
                }

                @Override
                public void onRecycViewLongClickListener(int position, View view, int type) {
                    showPopupWindows.ShowPopupWindow(view, R.layout.view_popupwindows, mContext, position, type);
                }
            });
        }

        if (viewType == DateFather.TYPE_WERTHER) {
            return new WeatherTypeHolder(mLayoutInflater.inflate(R.layout.weather_item,parent,false));
        }
        if (viewType == DateFather.TYPE__NEWS_TITLE) {
            return new NewsTitleTypeHoder(mLayoutInflater.inflate(R.layout.item_home_newstitle,parent,false));
        }
        if (viewType == DateFather.TYPE_Game_TITLE) {
            return new GameTitleTypeHoder(mLayoutInflater.inflate(R.layout.item_home_gametitle,parent,false));
        }
        if (viewType == DateFather.TYPE_Game_Home_item) {
            return new HomeGameitemType(mLayoutInflater.inflate(R.layout.item_home_game, parent, false), new mRecycViewOnClickListener() {
                @Override
                public void onRecycViewItemClickListener(int position) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("Uri",((GameJsonDate)lists.get(position-2)).Uri);
                    intent.putExtra("Type","6");
                    mContext.startActivity(intent);
                }

                @Override
                public void onRecycViewDownloadItemClickListener(int position) {

                }

                @Override
                public void onRecycViewLongClickListener(int position, View view, int type) {
                    showPopupWindows.ShowPopupWindow(view, R.layout.view_popupwindows, mContext, position, type);
                }
            });
        }

        if (viewType == DateFather.TYPE_FOOD){
            return new FoodTypeHolder(mLayoutInflater.inflate(R.layout.item_food,parent,false));
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {

        return type.get(position);
    }

    //处理事件的逻辑
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (counts!=15){
            if (getItemViewType(position)==DateFather.TYPE_NEWSONE){
                ((NewsTypeHolder)holder).buileHoulder(lists.get(position),position);
            }
            if (getItemViewType(position) == GameJsonDate.TYPE_Game){
                ((GameTypeHoder)holder).buileHoulder(lists.get(position),position);
            }
            if (getItemViewType(position)==DateFather.TYPE_FOOD){
                ((FoodTypeHolder)holder).buileHoulder(lists.get(position),position);
            }
        }
        else if(counts==15){
            if (getItemViewType(position)==DateFather.TYPE_NEWSONE){
                ((NewsTypeHolder)holder).buileHoulder(lists.get(position-1),position);
            }
            if (getItemViewType(position) == DateFather.TYPE_Game){
                ((GameTypeHoder)holder).buileHoulder(lists.get(position-2),position);
            }
            if (getItemViewType(position)==DateFather.TYPE_WERTHER){
                ((WeatherTypeHolder)holder).buileHoulder(lists.get(position),position);
            }
            if (getItemViewType(position)==DateFather.TYPE__NEWS_TITLE){
                ((NewsTitleTypeHoder)holder).buileHoulder(null,position);
            }
            if (getItemViewType(position)==DateFather.TYPE_Game_TITLE){
                ((GameTitleTypeHoder)holder).buileHoulder(null,position);
            }
            if (getItemViewType(position)==DateFather.TYPE_Game_Home_item){
                ((HomeGameitemType)holder).buileHoulder(lists.get(position-2),position);
            }
        }
    }


    @Override
    public int getItemCount() {
        return counts;
    }

    //添加一个增加条目的方法
    public void additem(List<DateFather> datelist){
        lists = datelist;
        notifyDataSetChanged();
    }

}

