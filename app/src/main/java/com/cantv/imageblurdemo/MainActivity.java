package com.cantv.imageblurdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cantv.imageblurdemo.utils.FastBlurUtils;
import com.cantv.imageblurdemo.utils.RenderScriptUtils;

public class MainActivity extends AppCompatActivity {

    private static final int RADIUS = 20;
    private static final float SCALE = 0.125f;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv_image);
        initData();
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        int width = Math.round(bitmap.getWidth() * SCALE);
        int height = Math.round(bitmap.getHeight() * SCALE);
        // 优化点：压缩Bitmap
        Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, width, height, false);
        // 推荐模糊方式
        Bitmap blurBitmap = RenderScriptUtils.rsBlur(this, bitmapScale, RADIUS);
        //Bitmap blurBitmap = FastBlurUtils.doBlur(bitmapScale, RADIUS, false);
        imageView.setImageBitmap(blurBitmap);
    }
}
