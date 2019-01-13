package ua.testov.test;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File workDirectory = new File("C:\\Users\\Вадим\\Documents\\Aspyr\\The Shie");
		File[] arrWorkDirectory = workDirectory.listFiles(new MyFileFilter());

		File newFolder = new File("New Folder");
		newFolder.mkdirs();
		for (int i = 0; i < arrWorkDirectory.length; i++) {
			File out = new File(newFolder, "(copy) " + arrWorkDirectory[i].getName());
			try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File[] arrNewFolder = newFolder.listFiles();

		int x = arrWorkDirectory.length;
		
		if(arrWorkDirectory.length>=10) {
			x=10;
			Thread[] arrThr = arrayThread(arrWorkDirectory, arrNewFolder, x);
			startThread(arrThr);
			joinThread(arrThr);
		}else {
			Thread[] arrThr = new Thread[x];
		for (int i = 0; i < arrThr.length; i++) {
			File[] arrIn=new File[] {arrWorkDirectory[i]};
			File[] arrOut=new File[] {arrNewFolder[i]};
			arrThr[i] = new Thread(new ThreadCopyFile(arrIn,arrOut,0,1));
		}	
		startThread(arrThr);
		joinThread(arrThr);
		}
		
		System.out.println("File copy finished");
	}

	public static Thread[] arrayThread(File[] arrIn, File[] arrOut, int threadNumber) {
		Thread[] arrThr = new Thread[threadNumber];
		for (int i = 0; i < arrThr.length; i++) {
			int size = arrIn.length / threadNumber;
			int begin = size * i;
			int end = (size * (i + 1));
			if ((arrIn.length - end) < size) {
				end = arrIn.length;
			}
			arrThr[i] = new Thread(new ThreadCopyFile(arrIn, arrOut, begin, end));
		}
		return arrThr;
	}
	public static void startThread(Thread[]th) {
		for (int i = 0; i < th.length; i++) {
			th[i].start();
		}
	}
	public static void joinThread(Thread[]th) {
		for (int i = 0; i < th.length; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
