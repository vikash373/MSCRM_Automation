package com.decexp.utils;

import java.util.Iterator;
import java.util.Set;

public class Helper extends BaseTest {

	private static Object firstHandle;
	private static Object lastHandle;

	public static void switchToWindowsPopup() {
	    Set<String> winhandles = driver.getWindowHandles();
	    Iterator<String> itr = winhandles.iterator();
	    firstHandle = itr.next();
	    lastHandle = firstHandle;
	    while (itr.hasNext()) {
	        lastHandle = itr.next();
	    }
	    driver.switchTo().window(lastHandle.toString());
	    System.out.println("Swithching window id is-->"+driver.getWindowHandle());
	}

	public static void switchToMainWindow() {
	    driver.switchTo().window(firstHandle.toString());
	    //System.out.println("Default Window id is-->"+driver.switch);
	}
	
	public static String currentWindow(){
		return driver.getWindowHandle();
	}
}
