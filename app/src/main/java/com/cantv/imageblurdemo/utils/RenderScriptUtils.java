package com.cantv.imageblurdemo.utils;

import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

/**
 * Created by net_c on 2017/7/27.
 */

public class RenderScriptUtils {

    public static Bitmap rsBlur(Context context, Bitmap source, int radius) {
        Log.i("Main", "操作图片尺寸=" + source.getWidth() + "---" + source.getHeight());
        long time = System.currentTimeMillis();
        Bitmap inputBmp = source;
        RenderScript renderScript = RenderScript.create(context);
        final Allocation input = Allocation.createFromBitmap(renderScript, inputBmp);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(inputBmp);
        renderScript.destroy();
        Log.i("Main", "RenderScript处理耗时：" + String.valueOf(System.currentTimeMillis() - time));
        return inputBmp;
    }
}
