#ImageBlur

###功能介绍：一款对于大图片进行高斯模糊处理避免发生内存溢出的解决方案

###代码说明：本方案使用Bitmap压缩从而减少像素点，减少资源开销。

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        int width = Math.round(bitmap.getWidth() * SCALE);
        int height = Math.round(bitmap.getHeight() * SCALE);
        // 优化点：压缩Bitmap
        Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, width, height, false);
        // 推荐模糊方式
        Bitmap blurBitmap = RenderScriptUtils.rsBlur(this, bitmapScale, RADIUS);
        //Bitmap blurBitmap = FastBlurUtils.doBlur(bitmapScale, RADIUS, false);
        imageView.setImageBitmap(blurBitmap);

###效果图

