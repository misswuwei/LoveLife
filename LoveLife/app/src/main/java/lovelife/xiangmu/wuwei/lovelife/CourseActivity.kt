package lovelife.xiangmu.wuwei.lovelife

import Adapter.FindHomeAdapter
import Date.CourseData
import android.graphics.Color
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_course.*
import netUtil.RetrofitFactory


/**
 * Created by Wuwei on 2018/9/5.
 */
class CourseActivity : BaseActivity(){
    var courseData : CourseData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        initIntent()
        initUI()
        initCourseDara()
    }

    fun initIntent(){
        courseData = intent.getSerializableExtra("data") as CourseData?
    }

    fun initUI(){
        course_tv_title.setText(courseData?.Title)
        course_tv_about.setText(courseData?.About)
        course_tv_commend_people_count.setText(courseData?.Like+"人")
        course_tv_buy.setText(courseData?.price+"元购买")
        if (courseData?.bgcolor.equals("null")){
            course_rl_cover_background.setBackgroundColor(Color.WHITE)
        }else{
            course_rl_cover_background.setBackgroundColor(Color.parseColor(courseData?.bgcolor))
        }
        Glide.with(this).load(courseData?.cover).into(course_iv_cover)
        course_tv_second_title.setText(courseData?.intro)
        course_ll_solve_problem.addData(courseData?.tips!!)
    }

    fun initCourseDara(){
//        setThread(RetrofitFactory.sRetrofitService.getSection(SPUtil.getString(application, "access_token", ""),))
    }
}