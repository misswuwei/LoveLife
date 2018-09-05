package Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import Utils.SPUtil;
import lovelife.xiangmu.wuwei.lovelife.CouCangActivity;
import lovelife.xiangmu.wuwei.lovelife.LoginActivity;
import lovelife.xiangmu.wuwei.lovelife.*;
import lovelife.xiangmu.wuwei.lovelife.R;
import Utils.grabDateUtils;

/**
 * Created by Wuwei on 2018/2/14.
 */

public class UserFragment extends Fragment {
    private View mView;
    private Button mBt_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView= inflater.inflate(R.layout.fragment_user_layout,null);
        }
                //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。

        ViewGroup viewGroup = (ViewGroup) mView.getParent();
        if (viewGroup!=null){
            viewGroup.removeView(mView);
        }
        initUI(mView);
        return mView;
    }

    private void initUI(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_user);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(grabDateUtils.windowsWeith,grabDateUtils.windowsHeight));

        LinearLayout soucang = view.findViewById(R.id.soucang);
        LinearLayout xiazai = view.findViewById(R.id.xiazai);
        LinearLayout shezhi = view.findViewById(R.id.shezhi);
        LinearLayout about = view.findViewById(R.id.about);
        soucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtil.getBoolean(getActivity(),"UserLogin",false)){
                    startActivity(new Intent(getActivity(), CouCangActivity.class));
                }else {
                    Toast.makeText(getActivity(),"您还没有登陆 无法授权", Toast.LENGTH_LONG).show();
                }
            }
        });
        xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtil.getBoolean(getActivity(),"UserLogin",false)){

                }else {
                    Toast.makeText(getActivity(),"您还没有登陆 无法授权", Toast.LENGTH_LONG).show();
                }
            }
        });
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtil.getBoolean(getActivity(),"UserLogin",false)){

                }else {
                    Toast.makeText(getActivity(),"您还没有登陆 无法授权", Toast.LENGTH_LONG).show();
                }
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (SPUtil.getBoolean(getActivity(),"UserLogin",false)){
//                    startActivity(new Intent(getActivity(), AboutAvtivity.class));
//                }else {
//                    Toast.makeText(getActivity(),"您还没有登陆 无法授权", Toast.LENGTH_LONG).show();
//                }
                startActivity(new Intent(getActivity(),text.class));
            }
        });
        mBt_login = view.findViewById(R.id.bt_login);
        mBt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
//        if (SPUtil.getBoolean("UserLogin",false)){
//            //用户已登陆
//            mBt_login.setVisibility(View.GONE);
//        }else{
//            mBt_login.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
