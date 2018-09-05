package Fragment

import Adapter.GameHomeAdapter
import Date.GameJsonDate
import Date.TypeData
import Utils.grabDateUtils
import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import lovelife.xiangmu.wuwei.lovelife.HomeActivity
import lovelife.xiangmu.wuwei.lovelife.R


/**
 * 加载游戏首页的Fragment
 * Created by Wuwei on 2018/6/28.
 */
class GameHomeFragment : Fragment() {
    val list = arrayListOf<TypeData>()
    //存放首页游戏条目数据的集合
    var ShouyeDateList = arrayListOf<GameJsonDate>()

    var srl: SwipeRefreshLayout? = null
    var game_home_recycler: RecyclerView? = null
    var mview: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment完全加载
        mview = inflater!!.inflate(R.layout.fragment_game_home, null)

        initData(mview!!)
        return mview as View
    }

    //初始化数据
    fun initData(view: View) {
        //添加type
        for (i in 0 .. 4) {
            val type = TypeData()
            when(i){
                3 -> type.type = 1
                4 -> type.type = 3
                else -> type.type = i
            }
            list.add(type)
        }


        game_home_recycler = view.findViewById<RecyclerView>(R.id.game_home_recycler)
        srl = view.findViewById<SwipeRefreshLayout>(R.id.game_home_srl)
        view.findViewById<TextView>(R.id.toolbartitle).setOnClickListener(View.OnClickListener {
            (activity as HomeActivity).showFragment(GameFragment(), "game_order")
        })
        startLoadData()
        srl?.viewTreeObserver?.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            //判断刷新
            srl?.isEnabled = (srl!!.scaleY == 0f)
            Log.i("initData", "" + srl!!.scaleY)
        })
    }

    fun startLoadData() {
        if (ShouyeDateList.size == 0) {
            srl?.isRefreshing = grabDateUtils.GameHomeDatefinish
            Thread(Runnable {
                grabDateUtils.initHomeGameDate(ShouyeDateList)
                while (grabDateUtils.GameHomeDatefinish) {

                }
                activity.runOnUiThread(Runnable {
                    addData()
                })

            }).start()
        } else {
            addData()
        }

    }

    fun addData() {
        srl?.isRefreshing = grabDateUtils.GameHomeDatefinish
        game_home_recycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val adapter = GameHomeAdapter(list)
        adapter.setList(ShouyeDateList)
        game_home_recycler?.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        grabDateUtils.GameHomeDatefinish = true
        super.onDestroy()
    }

    interface show {
        fun showFragment(fragment: Fragment, tag: String)
    }
}
