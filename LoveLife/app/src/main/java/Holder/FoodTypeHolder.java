package Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Date.DateFather;
import Date.FoodJsonDate;
import Utils.OkHttpUtils;
import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * Created by Wuwei on 2018/4/18.
 */

public class FoodTypeHolder extends absHolder implements View.OnClickListener,View.OnLongClickListener{

//    private final TextView mFood_name;
//    private final TextView mFood_hint;
//    private final TextView mFood_money;
//    private final TextView mFood_local;
//    private final TextView mFood_number;
//    private final ImageView mFood_image;

    public FoodTypeHolder(View itemView) {
        super(itemView);
//        mFood_name = itemView.findViewById(R.id.food_name);
//        mFood_hint = itemView.findViewById(R.id.food_hint);
//        mFood_money = itemView.findViewById(R.id.food_money);
//        mFood_local = itemView.findViewById(R.id.food_local);
//        mFood_number = itemView.findViewById(R.id.food_number);
//        mFood_image = itemView.findViewById(R.id.food_image);
    }

    @Override
    public void buileHoulder(DateFather date, int position) {
//        mFood_name.setText(((FoodJsonDate)OkHttpUtils.foodDate.get(position)).FoodName);
//        mFood_hint.setText(((FoodJsonDate)OkHttpUtils.foodDate.get(position)).FoodHint);
//        mFood_money.setText(((FoodJsonDate)OkHttpUtils.foodDate.get(position)).Money);
//        mFood_local.setText(((FoodJsonDate)OkHttpUtils.foodDate.get(position)).Local);
//        mFood_number.setText(((FoodJsonDate)OkHttpUtils.foodDate.get(position)).Sold);
//        mFood_image.setImageBitmap(((FoodJsonDate)OkHttpUtils.foodDate.get(position)).Bitmap);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
