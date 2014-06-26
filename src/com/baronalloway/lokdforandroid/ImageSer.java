package com.baronalloway.lokdforandroid;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

public class ImageSer implements Serializable{
	
	//TODO: Write class so that it serializes the image that was taken, and another method to unserialize
	int width;
    int height;
    int[] pixels;
    int[] tmp;
	
	ImageSer(Bitmap image){
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		
		image.getPixels(pixels, 0, width, 0, 0, width, height);
	}
	
	public Bitmap toImage()
	{
		Bitmap outImage = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		outImage.setPixels(pixels, 0, width, 0, 0, width, height);
		
		return outImage;
	}
	
	
	

}
