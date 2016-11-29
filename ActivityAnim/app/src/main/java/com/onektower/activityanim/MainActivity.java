package com.onektower.activityanim;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 主要演示android中activity切换的动画
 * <p/>
 * 包含两种方式：
 * 1、使用Activity类的overridePendingTransition(int enterAnim, int exitAnim)：这种方法对开发者来说是相当熟悉的了；
 * <p/>
 * 2、使用ActivityOptionsCompat中的以下方法：虽然是向下兼容，但是在低版本上可能没有动画效果
 * 2.1、ActivityOptionsCompat.makeCustomAnimation(Context context, int enterResId, int exitResId),类似于方法overridePendingTransition；
 * 2.2、ActivityOptionsCompat.makeScaleUpAnimation(View source, int startX, int startY, int startWidth, int startHeight)，通过放大view，通过实验发现放大的是要跳转的activity，效果也不是很好
 * 参数中的startX和startY其实设置也没有过多的作用，它们和view的位置关联，下一个activity开始放大的位置和view的位置对应；
 * 2.3、ActivityOptionsCompat.makeThumbnailScaleUpAnimation(View source, Bitmap thumbnail, int startX, int startY)同2.2；
 * 2.4、ActivityOptionsCompat.makeSceneTransitionAnimation(Activity activity,View sharedElement, String sharedElementName)两个activity的view协同完成的动画
 * 2.5、ActivityOptionsCompat.makeSceneTransitionAnimation(Activity activity, Pair<View, String>... sharedElements)两个activity的多个view协同完成的动画
 */

public class MainActivity extends AppCompatActivity {
    private ImageView three;
    private ImageView four;
    private ImageView five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button one = (Button) findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oneIntent = new Intent(MainActivity.this, OneActivity.class);
                startActivity(oneIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        Button two = (Button) findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent twoIntent = new Intent(MainActivity.this, TwoActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(MainActivity.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                ActivityCompat.startActivity(MainActivity.this, twoIntent, compat.toBundle());
            }
        });
        three = (ImageView) findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent threeIntent = new Intent(MainActivity.this, ThreeActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(three, 0, 0, three.getWidth(), three.getHeight());
                ActivityCompat.startActivity(MainActivity.this, threeIntent, compat.toBundle());
            }
        });

        four = (ImageView) findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fourIntent = new Intent(MainActivity.this, FourActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(four, BitmapFactory.decodeResource(getResources(), R.drawable.four), 0, 0);
                ActivityCompat.startActivity(MainActivity.this, fourIntent, compat.toBundle());
            }
        });
        five = (ImageView) findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fiveIntent = new Intent(MainActivity.this, FiveActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, five, getString(R.string.trans));
                ActivityCompat.startActivity(MainActivity.this, fiveIntent, compat.toBundle());
            }
        });
    }
}
