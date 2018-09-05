package Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Wuwei on 2018/5/12.
 */

public class SPUtil {
    private static SharedPreferences sp;

    //写入
    /**
     * @param key	key键值（索引）
     * @param value		想要存入的值
     */
    public static void putBoolean(Context ctx,String key,Boolean value) {
        //若sp在内存中已经存在（第二次访问就不必在创建了）
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = ctx.getSharedPreferences("comfig", ctx.MODE_PRIVATE);
        }
        //将传入的键值和键值关联的值存入
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * @param key	key键值（索引）
     * @param defValue	想要存入的值
     */
    public static boolean getBoolean(Context ctx,String key, boolean defValue) {//二者不同在于，这里的参数三是当文件没有存值要设置的默认值
        //若sp在内存中已经存在（第二次访问就不必在创建了）
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = ctx.getSharedPreferences("comfig", ctx.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }


    public static void putString(Context ctx,String key,String value) {
        //若sp在内存中已经存在（第二次访问就不必在创建了）
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = ctx.getSharedPreferences("comfig", ctx.MODE_PRIVATE);
        }
        //将传入的键值和键值关联的值存入
        sp.edit().putString(key, value).commit();
    }

    /**
     * @param key	key键值（索引）
     * @param defValue	想要存入的值
     */
    public static String getString(Context ctx,String key,String defValue) {//二者不同在于，这里的参数三是当文件没有存值要设置的默认值
        //若sp在内存中已经存在（第二次访问就不必在创建了）
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = ctx.getSharedPreferences("comfig", ctx.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);}


    public static void remove(String key) {
        // TODO Auto-generated method stub
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = AppUtil.getApp().getSharedPreferences("comfig", AppUtil.getApp().MODE_PRIVATE);
        }
        //删除方法同样是通过编译器deit调用
        sp.edit().remove(key).commit();
    }


    public static int getInt(String key,int defValue) {//二者不同在于，这里的参数三是当文件没有存值要设置的默认值
        //若sp在内存中已经存在（第二次访问就不必在创建了）
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = AppUtil.getApp().getSharedPreferences("comfig", AppUtil.getApp().MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);}

    public static void putInt(String key,int value) {
        //若sp在内存中已经存在（第二次访问就不必在创建了）
        if (sp == null) {
            //参数一：想要存放的文件的名称。参数二：存储模式Context.MODE_PRIVATE为禁止其他人访问
            sp = AppUtil.getApp().getSharedPreferences("comfig", AppUtil.getApp().MODE_PRIVATE);
        }
        //将传入的键值和键值关联的值存入
        sp.edit().putInt(key, value).commit();
    }

}
