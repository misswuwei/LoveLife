package Date

import android.util.Log
import org.json.JSONArray
import java.io.Serializable

/**
 * Created by Wuwei on 2018/8/1.
 */
class CourseData : Cloneable,Serializable{
    var Title:String? = null    //课程标题
    var About:String? = null    //课程关于
    var Like:String? = null      //课程点赞数
    var classNumber:String? = null      //课程数
    var cover:String? = null    //课程图片
    var price:String? = null    //课程价格
    var bgcolor:String? = null      //课程背景
    var intro:String? = null    //课程介绍
    var tips :ArrayList<String>? = ArrayList()      //课程帮助
    var scid :String? = null    //商店id
    var cid :String? = null    //章节id
    var courseID :ArrayList<String>? = ArrayList()      //存放所有章节ID

    fun Clone() : CourseData{
        val clone = super.clone() as CourseData
        this.tips = tips?.clone() as ArrayList<String>
        return clone
    }

    fun setTips(tips:JSONArray){
       for (i in 0..tips.length()-1){
           this.tips?.add(tips.get(i).toString())
       }
    }

}