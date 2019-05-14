package info.itloser.fuckdingding;

import android.content.Context;
import android.content.SharedPreferences;


public class SPUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SPUtil(Context context) {
        sp = context.getSharedPreferences("fuck_ding_ding.db", context.MODE_PRIVATE);
        editor = sp.edit();
    }

    //    首次启动，新方案
    public long getCheckTime() {
        return sp.getLong("time", -10086);
    }

    public void setCheckTime(long time) {
        editor.putLong("time", time);
        editor.commit();
    }


}
