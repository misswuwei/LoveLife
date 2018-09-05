package Date

import android.util.Log
import org.json.JSONArray
import java.io.Serializable

/**
 * Created by Wuwei on 2018/8/1.
 */
class CourseData : Cloneable,Serializable{
    var Title:String? = null
    var About:String? = null
    var Like:String? = null
    var classNumber:String? = null
    var cover:String? = null
    var price:String? = null
    var bgcolor:String? = null
    var intro:String? = null
    var tips :ArrayList<String>? = ArrayList()

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