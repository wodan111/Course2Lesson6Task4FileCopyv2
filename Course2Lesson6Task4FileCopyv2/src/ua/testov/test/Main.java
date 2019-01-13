package ua.testov.test;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File workDirectory = new File(".");
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

		int x = 10;
		Thread[] arrThr = arrayThread(arrWorkDirectory, arrNewFolder, x);

		for (int i = 0; i < arrThr.length; i++) {
			arrThr[i].start();
		}

		for (int i = 0; i < arrThr.length; i++) {
			try {
				arrThr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
}
