package com.play4u.mobile.facades;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by ykeyser on 8/4/15.
 */
public class BitMapRescaleFacade {
    final Bitmap originalBitmap;
    final WindowManager winMgr;
    final int SCALE_THRESHOLD=2048;

    public BitMapRescaleFacade(final WindowManager winMgr, final Bitmap originalBitmap){
        this.originalBitmap=originalBitmap;
        this.winMgr=winMgr;
    }

    /**
     * Attempt to rescale image if height or width is > 2048 pixels
     * @return scaled bitmap or original bitmap if scaling is unnecessary
     */
    public Bitmap rescaleImage(){
        if(originalBitmap.getHeight() >= SCALE_THRESHOLD || originalBitmap.getWidth() >= SCALE_THRESHOLD){
            DisplayMetrics metrics = new DisplayMetrics();
            winMgr.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            return Bitmap.createScaledBitmap(originalBitmap, width, height, true);
        }else {
            return originalBitmap;
        }
    }
}
