package com.decexp.utils;

import java.io.File;

import autoitx4java.AutoItX;

import com.jacob.com.LibraryLoader;



public class AutoItUtills {

	public static void LoginUsingAutoIt(String UserName, String Pwd) throws InterruptedException {
		
		File FileObj = new File("Library_AutoIt\\jacob-1.18-x64.dll");
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, FileObj.getAbsolutePath());
		System.out.println("I am with JACOB");
		AutoItX x = new AutoItX();
		//Thread.sleep(2000);
		
		//x.winActivate("Authentication Required");
		x.winWaitActive("Authentication Required");
		x.send(UserName);
		x.sleep(1000);
		x.send("{TAB}", false );
		x.send(Pwd);
		x.send("{ENTER}", false);
		
		
	}
	
}
