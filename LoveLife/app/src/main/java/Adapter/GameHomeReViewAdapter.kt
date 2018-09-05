package Adapter

import Date.ReviewData
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import lovelife.xiangmu.wuwei.lovelife.R
import lovelife.xiangmu.wuwei.lovelife.WebViewActivity

/**
 * Created by Wuwei on 2018/7/4.
 */
class GameHomeReViewAdapter(resId : Int,list : List<ReviewData>) : BaseQuickAdapter<ReviewData,BaseViewHolder>(resId,list){
    override fun convert(helper: BaseViewHolder?, item: ReviewData?) {
        helper?.setText(R.id.revire_app_name,item?.appName)
        helper?.setText(R.id.review_app_content,item?.reviewContent)
        helper?.setText(R.id.review_item_user_name,item?.userName)
        val img = helper?.getView<RoundedImageView>(R.id.review_app_img)
        img?.setImageBitmap(item?.bitmap)
        img?.setOnClickListener(View.OnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("Uri", item?.appUrl)
            intent.putExtra("Type", "3")
            mContext.startActivity(intent)
        })
        helper?.setOnClickListener(R.id.review_rl_item,View.OnClickListener{
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("Uri", item?.itemUrl)
            intent.putExtra("Type", "3")
            mContext.startActivity(intent)
        })

    }

}


