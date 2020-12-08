package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindAnim(R.anim.top_animation) Animation topAnimation;
    @BindAnim(R.anim.middle_animation) Animation middleAnimation;
    @BindView(R.id.androidHead) ImageView androidHead;
    @BindView(R.id.androidBody) View androidBody;
    @BindView(R.id.splashTextView) TextView splashTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    void init(){
        topAnimation.setDuration(5000);
        middleAnimation.setDuration(5000);
        androidHead.startAnimation(topAnimation);
        androidBody.startAnimation(middleAnimation);
        splashTextView.startAnimation(middleAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);

    }
}