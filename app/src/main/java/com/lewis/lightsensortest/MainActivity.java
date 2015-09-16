package com.lewis.lightsensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private SensorManager sensorManager;

    private TextView lightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);
        //传感器管理
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //获取传感器类型
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //注册监听事件
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将资源释放掉
        if (sensorManager != null){
            sensorManager.unregisterListener(listener);
        }
    }

    /**
     * 对传感器输出的信号进行监听
     */
    private SensorEventListener listener = new SensorEventListener() {
        //当传感器监测的数值发生变化的时候就会调用
        @Override
        public void onSensorChanged(SensorEvent event) {
            //values数组中的第一个下标的值就是当前的光照强度
            float value = event.values[0];
            lightLevel.setText("Current light level is "+value+" lx");
        }
        //当传感器的精度发生变化的时候就会调用
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
