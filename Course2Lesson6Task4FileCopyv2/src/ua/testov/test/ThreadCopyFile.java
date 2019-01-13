package ua.testov.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadCopyFile implements Runnable {
	private File[] arrIn;
	private File[] arrOut;
	private int begin;
	private int end;

	public ThreadCopyFile() {
		super();
	}

	public ThreadCopyFile(File[] arrIn, File[] arrOut, int begin, int end) {
		super();
		this.arrIn = arrIn;
		this.arrOut = arrOut;
		this.begin = begin;
		this.end = end;
	}

	public void fileCopy(File in, File out) throws IOException {

		byte[] buffer = new byte[1024 * 1024];
		int readByte = 0;

		try (FileInputStream fis = new FileInputStream(in); FileOutputStream fos = new FileOutputStream(out)) {
			for (; (readByte = fis.read(buffer)) > 0;) {
				fos.write(buffer, 0, readByte);
			}

		} catch (IOException e) {
			throw e;
		}
	}

	private void copyFileFromArr() {
		for (int i = begin; i < end; i++) {
			try {
				fileCopy(arrIn[i], arrOut[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		copyFileFromArr();
	}
}
