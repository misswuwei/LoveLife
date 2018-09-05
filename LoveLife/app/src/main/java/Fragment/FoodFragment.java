package Fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.MyFoodRecycAdapter;
import Date.TypeData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lovelife.xiangmu.wuwei.lovelife.R;
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity;

/**
 * 展示食物的Fragment
 * Created by Wuwei on 2018/3/17.
 */

public class FoodFragment extends Fragment {
    @BindView(R.id.baidu_item_takeout_icon)
    RoundedImageView mBaiduItemTakeoutIcon;
    @BindView(R.id.baidu_item_huoche_icon)
    RoundedImageView mBaiduItemHuocheIcon;
    @BindView(R.id.baidu_item_feiji_icon)
    RoundedImageView mBaiduItemFeijiIcon;
    @BindView(R.id.baidu_item_qiche_icon)
    RoundedImageView mBaiduItemQicheIcon;
    @BindView(R.id.baidu_item_travel_icon)
    RoundedImageView mBaiduItemTravelIcon;
    @BindView(R.id.baidu_item_yule_icon)
    RoundedImageView mBaiduItemYuleIcon;
    @BindView(R.id.baidu_item_food_icon)
    RoundedImageView mBaiduItemFoodIcon;
    @BindView(R.id.baidu_item_jiudian_icon)
    RoundedImageView mBaiduItemJiudianIcon;
    @BindView(R.id.baidu_movie_item_icon)
    RoundedImageView mBaiduMovieItemIcon;
    Unbinder unbinder;

    private View mView;
    private RecyclerView mShotmovie_recyc;
    private MyFoodRecycAdapter mRecycAdaper;
    private List<TypeData> type = new ArrayList<>();
    private boolean isOpen = true;
    private int itemWeight;
    private String[] itemUrl = {"https://mdianying.baidu.com/?sfrom=newnuomi&source=nuomi&sub_channel=nuomi_wap_rukou1&c=131","https://m.nuomi.com/component/maphotel/vuepage/channelpage/channelpage.html?src_from=nuomiwap&_t_=1529567613&webapp_nojump=1&_t_=1530842099","https://waimai.baidu.com/waimai/shoplist/dfdc891a41f9f1ca",
    "https://m.nuomi.com/component/cuisine-home/html/home.html?_t_=1529378787&webapp_nojump=1&_t_=1530842099","https://m.nuomi.com/component/entertainment/html/home.html?_t_=1529567613&webapp_nojump=1&_t_=1530842099","https://m.nuomi.com/component/cuisine-home/html/home.html?_t_=1529378787&webapp_nojump=1&_t_=1530842099",
    "https://flights.ctrip.com/?mkt_header=bdnm&AllianceID=108294&sid=767751&ouid=v1c5a1sm_0&popup=close&autoawaken=close&sourceid=2189","https://trains.ctrip.com/TrainBooking/SearchTrain.aspx###",
    "https://kuai.baidu.com/pc/index/index?us=nuomi_pc"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_food, null);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。

        ViewGroup viewGroup = (ViewGroup) mView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(mView);
        }

        initUI(mView);

        initRecyclerview();

        unbinder = ButterKnife.bind(this, mView);
        return mView;

    }

    private void initRecyclerview() {

        mShotmovie_recyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                TypeData typeData = new TypeData();
                if (j == 0) {
                    typeData.setType(j);
                } else {
                    typeData.setType(i);
                }
                type.add(typeData);
                Log.i("", "initRecyclerview: " + typeData.getType());
            }
        }

        mRecycAdaper = new MyFoodRecycAdapter(type, getActivity());
        mRecycAdaper.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mShotmovie_recyc.setAdapter(mRecycAdaper);

    }

    private void initUI(View view) {
        mShotmovie_recyc = view.findViewById(R.id.food_recyc);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.baidu_item_takeout_icon, R.id.baidu_item_huoche_icon, R.id.baidu_item_feiji_icon, R.id.baidu_item_qiche_icon, R.id.baidu_item_travel_icon, R.id.baidu_item_yule_icon, R.id.baidu_item_food_icon, R.id.baidu_item_jiudian_icon, R.id.baidu_movie_item_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.baidu_item_takeout_icon:
                loadUrl(itemUrl[2]);
                break;
            case R.id.baidu_item_huoche_icon:
                loadUrl(itemUrl[7]);
                break;
            case R.id.baidu_item_feiji_icon:
                loadUrl(itemUrl[6]);
                break;
            case R.id.baidu_item_qiche_icon:
                loadUrl(itemUrl[8]);
                break;
            case R.id.baidu_item_travel_icon:
                //执行动画
                itemWeight = mBaiduItemFoodIcon.getWidth()+10;
                if (isOpen){
                    startShowAnimation();
                }else {
                    dismissShowAnimation();
                }
                isOpen = !isOpen;
                break;
            case R.id.baidu_item_yule_icon:
                loadUrl(itemUrl[4]);
                break;
            case R.id.baidu_item_food_icon:
                loadUrl(itemUrl[5]);
                break;
            case R.id.baidu_item_jiudian_icon:
                loadUrl(itemUrl[1]);
                break;
            case R.id.baidu_movie_item_icon:
                loadUrl(itemUrl[0]);
                break;
        }
    }

    public void loadUrl(String Url){
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("Uri", Url);
        intent.putExtra("Type", "3");
        startActivity(intent);
    }

    private void startShowAnimation() {
        //飞机
        ObjectAnimator feiAnimator = ObjectAnimator.ofFloat(mBaiduItemFeijiIcon, "translationY",0,-itemWeight);
        ObjectAnimator feiRotation = ObjectAnimator.ofFloat(mBaiduItemFeijiIcon, "rotation",0,360);
        //火车
        ObjectAnimator huoAnimator = ObjectAnimator.ofFloat(mBaiduItemHuocheIcon, "translationX",0,-itemWeight);
        ObjectAnimator huoRotation = ObjectAnimator.ofFloat(mBaiduItemHuocheIcon, "rotation",0,-360);
        //汽车
        ObjectAnimator qiAnimator = ObjectAnimator.ofFloat(mBaiduItemQicheIcon, "translationX",0,itemWeight);
        ObjectAnimator qiRotation = ObjectAnimator.ofFloat(mBaiduItemQicheIcon, "rotation",0,360);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(150);
        animatorSet.playTogether(feiAnimator,feiRotation,huoAnimator,huoRotation,qiAnimator,qiRotation);
        animatorSet.start();
    }

    private void dismissShowAnimation() {
        //飞机
        ObjectAnimator feiAnimator = ObjectAnimator.ofFloat(mBaiduItemFeijiIcon, "translationY",-itemWeight,0);
        ObjectAnimator feiRotation = ObjectAnimator.ofFloat(mBaiduItemFeijiIcon, "rotation",itemWeight,0);
        //火车
        ObjectAnimator huoAnimator = ObjectAnimator.ofFloat(mBaiduItemHuocheIcon, "translationX",-itemWeight,0);
        ObjectAnimator huoRotation = ObjectAnimator.ofFloat(mBaiduItemHuocheIcon, "rotation",-360,0);
        //汽车
        ObjectAnimator qiAnimator = ObjectAnimator.ofFloat(mBaiduItemQicheIcon, "translationX",itemWeight,0);
        ObjectAnimator qiRotation = ObjectAnimator.ofFloat(mBaiduItemQicheIcon, "rotation",360,0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(150);
        animatorSet.playTogether(feiAnimator,feiRotation,huoAnimator,huoRotation,qiAnimator,qiRotation);
        animatorSet.start();
    }


}
