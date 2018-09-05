package lovelife.xiangmu.wuwei.lovelife;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import Adapter.MySouCangAdapter;
import Utils.AppUtil;
import Utils.grabDateUtils;

/**
 * Created by Wuwei on 2018/5/13.
 */

public class CouCangActivity extends Activity {
    private MySouCangAdapter mRecycAdaper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coucang);
        initUI();
    }

    private void initUI() {
        ImageView coucang_back = findViewById(R.id.coucang_back);
        coucang_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView coucang_recyc = findViewById(R.id.coucang_recyc);
        coucang_recyc.setLayoutManager(new LinearLayoutManager(CouCangActivity.this,LinearLayoutManager.VERTICAL,false));
        mRecycAdaper = new MySouCangAdapter(this,R.layout.item_coucang, AppUtil.getInfoDao().loadAll());
        mRecycAdaper.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        coucang_recyc.setAdapter(mRecycAdaper);
    }
}
