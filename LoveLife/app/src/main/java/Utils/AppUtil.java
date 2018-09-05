package Utils;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import Date.DaoMaster;
import Date.DaoSession;
import Date.info;
import Date.infoDao;

/**
 * Created by Wuwei on 2018/5/12.
 */

public class AppUtil extends Application {
    public static AppUtil mContext;
    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static infoDao mInfoDao;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        context = getApplicationContext();
    }

    public static AppUtil getApp(){
        return mContext;
    }

    public static Context getAppC(){
        return context;
    }
    /**
     * 设置greenDao
     */
    public static void setDatabase(Context context) {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        if (mHelper==null){
            mHelper = new DaoMaster.DevOpenHelper(context, "info.db", null);
            db = mHelper.getWritableDatabase();
            // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
            mInfoDao = mDaoSession.getInfoDao();
        }
    }
     public static info getInfo(){
        if (mInfoDao.loadAll().size() == 0){
            mInfoDao.insert(new info());
            return mInfoDao.loadAll().get(0);
        }else {
            return mInfoDao.loadAll().get(0);
        }
     }
    public static infoDao getInfoDao(){
       return mInfoDao;
    }
}
