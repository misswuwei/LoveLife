package Adapter

import Date.GameJsonDate
import android.content.Intent
import android.view.View
import butterknife.OnClick
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import lovelife.xiangmu.wuwei.lovelife.R
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity

/**
 * 加载游戏页面的数据
 * Created by Wuwei on 2018/6/28.
 */
class GameHomeItemAdapter(id : Int, list :List<GameJsonDate>) :BaseQuickAdapter<GameJsonDate,BaseViewHolder>(id,list){
    override fun convert(helper: BaseViewHolder?, item: GameJsonDate?) {
        helper?.setText(R.id.tv_gameitem_ming,item?.gameitem_name)
        helper?.setImageBitmap(R.id.iv_gameitem_src,item?.gameitem_img)
        helper?.setText(R.id.tv_gameitem_other,item?.gameitem_content)
        helper?.setOnClickListener(R.id.ll_home_game_item, View.OnClickListener(){
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("Uri", item?.Uri)
            intent.putExtra("Type", "3")
            mContext.startActivity(intent)
        })
    }

}