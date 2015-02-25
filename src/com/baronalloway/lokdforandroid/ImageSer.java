package com.baronalloway.lokdforandroid;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

public class ImageSer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width;
    int height;
    int[] pixels;
    int[] tmp;
	
	ImageSer(Bitmap image){
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		Log.i("info", "CREATING NEW IMAGESER IN CLASS");
		image.getPixels(pixels, 0, width, 0, 0, width, height);
	}
	
	public Bitmap toImage()
	{
		Bitmap outImage = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		outImage.setPixels(pixels, 0, width, 0, 0, width, height);
		
		return outImage;
	}
	
	
	

}
