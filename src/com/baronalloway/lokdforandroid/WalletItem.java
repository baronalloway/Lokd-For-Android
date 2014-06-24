package com.baronalloway.lokdforandroid;

import java.io.Serializable;

public class WalletItem implements Serializable{
	ImageSer image;
	String name;
	
	WalletItem(ImageSer inimage, String inname)
	{
		image = inimage;
		name = inname;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ImageSer getImage()
	{
		return image;
	}
	public void setName(String inname)
	{
		name = inname;
	}
	public void setImageSer(ImageSer inimage)
	{
		image = inimage;
	}
	
	
	
	
}
