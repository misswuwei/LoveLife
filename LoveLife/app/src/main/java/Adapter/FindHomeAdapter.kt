package Adapter

import Date.CourseData
import View.HintLayout
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import lovelife.xiangmu.wuwei.lovelife.CourseActivity
import lovelife.xiangmu.wuwei.lovelife.R

/**
 * Created by Wuwei on 2018/7/30.
 */
class  FindHomeAdapter : BaseQuickAdapter< CourseData , BaseViewHolder> {
    var context : Context? = null
    constructor(id :Int,data:List<CourseData>,contexts : Context) : super(id,data){
        context = contexts
    }

    override fun convert(helper: BaseViewHolder?, item: CourseData?) {

        helper?.setText(R.id.item_find_course_tv_title,item?.Title)
        helper?.setText(R.id.item_find_course_tv_about,item?.About)
        helper?.setText(R.id.item_find_course_tv_count,item?.classNumber+"节课")
        helper?.setText(R.id.item_find_course_tv_recommend,item?.Like+"认为有用")
        helper?.setText(R.id.item_find_course_tv_price,item?.price+"元")
        val imageView = helper?.itemView?.findViewById<ImageView>(R.id.item_find_course_iv_cover)
        Glide.with(context).load(item?.cover).into(imageView)
        helper?.itemView?.setOnClickListener(View.OnClickListener {
            var intent = Intent(context,CourseActivity().javaClass)
            intent.putExtra("data",item)
            context?.startActivity(intent)
        })
        val layout: HintLayout = helper?.itemView?.findViewById<HintLayout>(R.id.item_find_course_hint_view)!!
        layout.addData(item?.tips!!)
    }


}