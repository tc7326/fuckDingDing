package info.itloser.fuckdingding;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn, btnSave;
    Context context;

    long setTime;//单位分钟

    /*
     * author    itloser.info
     * csdn      https://me.csdn.net/qq_38376757
     * blog      itloser.info
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.button);
        btnSave = findViewById(R.id.btn_save);
        context = this;

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv:
                        Calendar calendar = Calendar.getInstance();
                        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                Log.i("DateSet", i + ":" + i1);
                                StringBuilder builder = new StringBuilder();
                                builder.append("已选择：");
                                builder.append(i);
                                builder.append("点");
                                builder.append(i1);
                                builder.append("分");
                                tv.setText(builder);
                                //转时间戳
                                setTime = i * 60 + i1;
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                        timePickerDialog.show();
                        break;
                    case R.id.btn_save:
                        new SPUtil(context).setCheckTime(setTime);
                        Intent intentService = new Intent(MainActivity.this, CoreService.class);
                        startService(intentService);
                        break;
                }
            }
        };

        tv.setOnClickListener(click);
        btnSave.setOnClickListener(click);

    }
}
