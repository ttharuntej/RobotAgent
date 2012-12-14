package com.tharun;

public class WarpZone  extends Actor
{
	char value;
	int x,y;
	public WarpZone(char value) 
	{
		this.type = value;
//		this.value = Integer.parseInt(Character.toString(value));
	}
	
	public WarpZone() 
	{
	 }
	public WarpZone(char value, int x, int y)
	{
		this.value = value;//Integer.parseInt(Character.toString(value));
		this.x = x;
		this.y = y;
		
	}
	
	
}
