package Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import Date.DateFather;
import Date.NewsJsonDate;

/**
 * Created by Wuwei on 2018/1/28.
 */

public abstract class absHolder extends RecyclerView.ViewHolder{
    public absHolder(View itemView) {
        super(itemView);
    }
    public abstract void buileHoulder(DateFather date,int position);

}
