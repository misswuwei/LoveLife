package View

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import butterknife.BindBitmap
import kotlinx.android.synthetic.main.view_morepage.view.*
import lovelife.xiangmu.wuwei.lovelife.R

/**
 * kotlin语法开发
 * Created by Wuwei on 2018/6/25.
 */
class morePage : LinearLayout {
    val RIGHT = 0
    val LEFT = 1
    private var mPosition = 0;
    var mContext : Context? = null
    private var lunbolist : List<Bitmap> = arrayListOf()

    constructor(ctx : Context) : this(ctx,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,-1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int ) : super(context, attrs, defStyleAttr){
        val view = View.inflate(context,R.layout.view_morepage,this)
        mContext = context
    }

    fun initData(list : List<Bitmap>){
//        lunbo.setBackgroundDrawable(mContext?.resources?.getDrawable(lunbolist.get(0)))
        lunbo.setOnClickListener({ setBackGround(RIGHT,mPosition) })
    }


    fun setBackGround(flag : Int, id : Int) = when (flag) {
        LEFT -> leftAnima()
        RIGHT ->rightAnima()
        else -> {
        }
    }

    fun leftAnima(){
//        lunbo.setBackgroundResource(lunbolist!!.get(mPosition-1))
        mPosition = mPosition-1
        val animator = ObjectAnimator.ofFloat(lunbo, "alpha", 0f, 1f)
        animator.setDuration(200);
        animator.start();
    }

    fun rightAnima(){
//        lunbo.setBackgroundDrawable(mContext?.resources?.getDrawable(lunbolist.get(mPosition)))
        if (mPosition == 2)mPosition = 0;
        mPosition = mPosition+1
        val animator = ObjectAnimator.ofFloat(lunbo, "alpha", 0f, 1f)
        animator.setDuration(700);
        animator.start();
    }
}




