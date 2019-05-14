# 定时自动拉起钉钉demo
有问题或者机型不适配请提出!
主要思路：使用前台服务防止进程死亡。每隔一分钟获取一下系统时间（手机本地时间）。当和设置的时间相同时，拉起钉钉。
## ！！！仅供学习交流使用！！！
主要代码
```
if (CheckTime == nowTime) {
  //亮屏
  PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
  PowerManager.WakeLock mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "SimpleTimer");
  mWakeLock.acquire(60 * 1000);
  //拉起
  startActivity(getPackageManager().getLaunchIntentForPackage("com.xxx.android.xxx"));
}

```
