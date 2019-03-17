package com.example.loopswitch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.slidemove.library.listener.OnItemSelectedListener;
import com.slidemove.library.view.LoopRotarySwitchView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private LoopRotarySwitchView mLoopRotarySwitchView;

    private int width;
    private SeekBar mSeekBarX, mSeekBarZ;
    private CheckBox mCheckbox;
    private Switch mSwitchLeftright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initLoopRotarySwitchView();
        initLinstener();
    }

    private void initLinstener() {
        /**
         * 选中回调
         */
        mLoopRotarySwitchView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void selected(int position, View view) {
            }
        });
        /**
         * 设置子view的x坐标
         */
        mSeekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int i = seekBar.getMax() / 2;
                Log.e(TAG, "i----" + i + "  ,progress----" + progress+ "  ,progress----> " + (progress-i));
                mLoopRotarySwitchView.setLoopRotationX(progress - seekBar.getMax() / 2);
                mLoopRotarySwitchView.initView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        /**
         * 设置子view的z坐标
         */
        mSeekBarZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLoopRotarySwitchView.setLoopRotationZ(progress - seekBar.getMax() / 2);
                mLoopRotarySwitchView.initView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        /**
         * 设置是否自动旋转
         */
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mLoopRotarySwitchView.setAutoRotation(isChecked);//启动LoopViewPager自动切换
            }
        });
        /**
         * 设置向左还是向右自动旋转
         */
        mSwitchLeftright.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mLoopRotarySwitchView.setAutoScrollDirection(isChecked ? LoopRotarySwitchView.AutoScrollDirection.left
                        : LoopRotarySwitchView.AutoScrollDirection.right);
            }
        });
    }

    /**
     * 设置LoopRotarySwitchView
     */
    private void initLoopRotarySwitchView() {
        mLoopRotarySwitchView
                .setR(width / 3)//设置R的大小
                .setAutoRotation(false)//是否自动切换
                .setAutoScrollDirection(LoopRotarySwitchView.AutoScrollDirection.left)
                .setAutoRotationTime(1500);//自动切换的时间  单位毫秒
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mLoopRotarySwitchView = (LoopRotarySwitchView) findViewById(R.id.mLoopRotarySwitchView);
        mSeekBarX = (SeekBar) findViewById(R.id.seekBarX);
        mSeekBarZ = (SeekBar) findViewById(R.id.seekBarZ);
        mCheckbox = (CheckBox) findViewById(R.id.checkbox);
        mSwitchLeftright = (Switch) findViewById(R.id.switchLeftright);
        int i = mSeekBarX.getMax() / 2;
        Log.e(TAG, "i----" + i);
       // mSeekBarX.setProgress(i);
        int progress = mSeekBarX.getProgress();
        mLoopRotarySwitchView.setLoopRotationX(progress-i);
        //    mSeekBarX.setProgress(80);
        mSeekBarZ.setProgress(mSeekBarZ.getMax() / 2);

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
    }

}
