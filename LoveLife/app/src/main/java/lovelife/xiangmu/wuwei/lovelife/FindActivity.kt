package lovelife.xiangmu.wuwei.lovelife

import Adapter.FindHomeAdapter
import Date.CourseData
import Utils.SPUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_test.*
import netUtil.BaseObserver
import netUtil.RetrofitFactory
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by Wuwei on 2018/7/31.
 */
class FindActivity : BaseActivity() {
    var TAG = "FindActivity"
    var courseList = arrayListOf<CourseData>()
    val FindHomeAdapter.string : String
        get() = "Asdasd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initData()
    }

    fun initData() {
        find_recyc.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val string = SPUtil.getString(application, "access_token", "")
        setThread(RetrofitFactory.getRetrofitService().getShopCouse(string, "userIsLiked,courseVersion")).subscribe((object : BaseObserver() {
            override fun onSuccess(requeas: JsonObject) {
                var data = JSONArray(requeas.get("data").toString())
                initList(data)
                Log.i("data",""+data.toString())
            }

            override fun onFail(requeas: JsonObject) {
                Log.i("FindActivity", "onFail" + requeas.toString())
            }
        }))
    }

    fun doSome(){
        Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show()
    }

    fun FindHomeAdapter.show(){
        this.context
    }

    //初始化课程数据
    fun initList(data: JSONArray) {
        var courses = CourseData()
        for (i in 0 until data.length()) {
            var course = courses.Clone()
            val jsonObject = JSONObject(data[i].toString())
            course.Title = jsonObject.getString("title")
            course.About = jsonObject.getString("about").toString()
            course.Like = jsonObject.getString("like").toString()
            course.price = jsonObject.getString("price").toString()
            course.cover = "http://up-static.wupup.com/"+jsonObject.getString("cover").toString()
            val courseArray = jsonObject.getJSONArray("courseVersion").toString()
            course.classNumber = courseArray.length.toString()
            for (i in 0..courseArray.length-1){
                course.courseID?.add(courseArray.get(i).toString())
            }
            course.scid =jsonObject.getString("scid").toString()
            course.cid = jsonObject.getString("cid").toString()
            course.setTips(jsonObject.getJSONArray("tips"))
            course.intro = jsonObject.getString("intro").toString()
            course.bgcolor = jsonObject.getString("bgcolor").toString()
            courseList.add(course)
        }
        find_recyc.adapter = FindHomeAdapter(R.layout.recycler_item_find_course, courseList, this)

    }




}







