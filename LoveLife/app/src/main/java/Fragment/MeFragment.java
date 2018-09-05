package Fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * Created by Wuwei on 2018/5/3.
 */

public class MeFragment extends Fragment {
    int i=0;
    private TextView mTv_icon;
    private Button mBt_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,null);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        mTv_icon = view.findViewById(R.id.tv_icon);
        mBt_login = view.findViewById(R.id.bt_login);

        startAnimation(mTv_icon);
    }

    private void startAnimation(TextView tv_icon) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tv_icon,"alpha",0f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tv_icon,"scaleY",1f,1.5f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alpha).with(scaleY).after(alpha).with(scaleY).after(alpha).with(scaleY);
        alpha.start();
        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                i++;
                if (i==3){
                    mBt_login.setVisibility(View.VISIBLE);
//                    move();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void move() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBt_login,"translationY",mBt_login.getTranslationY(),mBt_login.getTranslationY()-100);
        animator.setDuration(2000);
        animator.start();
    }
}
