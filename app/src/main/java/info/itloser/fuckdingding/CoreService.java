package info.itloser.fuckdingding;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

public class CoreService extends Service {

    Handler handler;
    long CheckTime;//单位分钟

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("s", "服务开启");

        //发送通知，设定为前台服务。
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.icon);
        builder.setContentTitle("正在为您保驾护航");
        builder.setTicker("服务已启动");
        builder.setContentText("请勿关闭应用程序,github-tc7326");
        Notification notification = builder.build();
        startForeground(1, notification);

        //开启一个定时任务
        handler = new Handler();
        runnable.run();

        CheckTime = new SPUtil(this).getCheckTime();

    }

    /*
     * 核心
     * */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //要做的事情
            Log.i("设定的闹钟时间", CheckTime + "");
            Calendar calendar = Calendar.getInstance();
            long hours = calendar.get(Calendar.HOUR_OF_DAY) * 60;
            long minute = calendar.get(Calendar.MINUTE);
            long nowTime = hours + minute;
            Log.i("当前时间", nowTime + "");
            handler.postDelayed(this, 55000);

            //逻辑
            if (CheckTime == nowTime) {
                //亮屏
                PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
                @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "SimpleTimer");
                mWakeLock.acquire(60 * 1000);
                //拉起钉钉
                startActivity(getPackageManager().getLaunchIntentForPackage("com.alibaba.android.rimet"));
            }


        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.i("s", "服务停止");
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
