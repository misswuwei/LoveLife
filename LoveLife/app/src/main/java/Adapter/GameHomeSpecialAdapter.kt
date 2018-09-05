package Adapter

import Date.SprcialData
import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.makeramen.roundedimageview.RoundedImageView
import lovelife.xiangmu.wuwei.lovelife.R
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity

/**
 * 标题页适配器
 * Created by Wuwei on 2018/7/3.
 */
class GameHomeSpecialAdapter(context : Context,list :List<SprcialData>) : PagerAdapter(){
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    val mList = list//可以这样使用
    val mContext = context
    val viewList = arrayListOf<View>()

    init {
        for (i in mList.indices){
            val view = View.inflate(mContext, R.layout.recycler_game_special_item,null)
            val image = view.findViewById<RoundedImageView>(R.id.special_page_item)
            image.setImageBitmap(mList[i].bitmap)
            image.setOnClickListener(View.OnClickListener {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("Uri", mList[i].url)
                intent.putExtra("Type","3")
                mContext.startActivity(intent);
            })
            viewList.add(view)
        }
    }

    override fun getCount(): Int {
        return mList.size
    }

    //初始化条目数据
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container?.addView(viewList[position])
        return viewList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container?.removeView(viewList[position])
    }

    override fun getPageWidth(position: Int): Float {
        return 0.9f
    }
}