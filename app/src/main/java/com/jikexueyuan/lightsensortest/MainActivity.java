package com.jikexueyuan.lightsensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 简易光照传感器
 */
public class MainActivity extends AppCompatActivity {

    //传感器对象管理器
    private SensorManager sensorManager;
    private TextView lightLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //引用view控件
        lightLevel = (TextView) findViewById(R.id.light_level);
        //引入光照传感器
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //注册传感器监听事件
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

    //销毁activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null){
            sensorManager.unregisterListener(listener); //释放资源
        }
    }

    /**
     * 传感器事件监听对象
     */
    private SensorEventListener listener = new SensorEventListener() {
        /**
         * 光照强度变化
         * @param event
         */
        @Override
        public void onSensorChanged(SensorEvent event) {
            //
            float value = event.values[0];//第一个下标就是光照强度
            lightLevel.setText("Current light level is" + value +"lx");


        }

        /**
         * 精度变化
         * @param sensor
         * @param accuracy
         */
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
