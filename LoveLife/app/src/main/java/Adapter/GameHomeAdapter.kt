package Adapter

import Date.GameJsonDate
import Date.TypeData
import Utils.grabDateUtils
import View.morePage
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import lovelife.xiangmu.wuwei.lovelife.R

/**
 * 展示游戏主页的Adapter
 * Created by Wuwei on 2018/6/28.
 */
class GameHomeAdapter(data: List<TypeData>?) : BaseMultiItemQuickAdapter<TypeData, BaseViewHolder>(data) {
//初始化方法集成在上类定义中
    val HOME_ITEM = 1
    val HOME_MORE_PAGE = 0;
    val HOME_SPECIAL = 2;   //专题
    val HOME_REVIEW = 3;    //最新评价
    var mList = arrayListOf<GameJsonDate>()
    var upList = arrayListOf<GameJsonDate>()
    val dowmList = arrayListOf<GameJsonDate>()
    init {
        addItemType(HOME_ITEM, R.layout.wangye_hone_movie_recycler)
        addItemType(HOME_MORE_PAGE,R.layout.recycler_game_home_lunbo)
        addItemType(HOME_SPECIAL,R.layout.recycler_game_special)
        addItemType(HOME_REVIEW, R.layout.recycler_game_review)
    }
    override fun convert(helper: BaseViewHolder?, item: TypeData?) = when(item?.getType()) {
        HOME_ITEM ->initItem(helper,item)
        HOME_MORE_PAGE ->initMorePage(helper)
        HOME_SPECIAL ->initSpecial(helper)
        HOME_REVIEW ->initReView(helper)
        else -> {
        }
    }

    //初始化首页最新评价
    fun initReView(helper: BaseViewHolder?){
        val recyc = helper?.getView<RecyclerView>(R.id.review_cycler)
        recyc?.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        recyc?.adapter = GameHomeReViewAdapter(R.layout.recycler_game_review_item,grabDateUtils.reviewList)
    }

    //初始化首页更多控件
    fun initMorePage(helper: BaseViewHolder?){
        val morePage = helper?.getView<morePage>(R.id.game_home_more_page)
    }

    //初始化游戏条目
    fun initItem(helper: BaseViewHolder?,item: TypeData?){
        val recyc = helper?.getView<RecyclerView>(R.id.home_movie_recyc)
        recyc?.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        if (upList.size>3){
            recyc?.adapter = GameHomeItemAdapter(R.layout.item_home_game,upList)
            upList.removeAt(0)
        }else{
            recyc?.adapter = GameHomeItemAdapter(R.layout.item_home_game,dowmList)
        }

    }

    fun initSpecial(helper: BaseViewHolder?){
        val specialPage = helper?.getView<ViewPager>(R.id.game_home_special_page)
        specialPage?.adapter = GameHomeSpecialAdapter(mContext,grabDateUtils.specialList)
    }
    fun setList(list : ArrayList<GameJsonDate>){
        mList = list
        for (i in mList.indices){
            if (i < 5){
                upList.add(mList[i])
            }else{
                dowmList.add(mList[i])
            }

        }
    }
}





