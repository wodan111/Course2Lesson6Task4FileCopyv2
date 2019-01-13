package ua.testov.test;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter{	
	boolean check=true;
	public MyFileFilter() {
		super();
	}

	@Override
	public boolean accept(File pathname) {
		
		if(pathname.isDirectory()) {
			check=false;
		}
		return check;
	}
}
