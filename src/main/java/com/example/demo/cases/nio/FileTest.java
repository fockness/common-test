package com.example.demo.cases.nio;

import java.io.File;

/*
 * 递归读取文件夹中的文件
 */
public class FileTest {

	public static void main(String[] args) {
		visitFile("F:\\visitFile");
	}

	private static void visitFile(String path) {
		if (path == null) {
			return;// 因为下面的new File如果path为空，回报异常
		}
		//listFiles()方法是返回某个目录下所有文件和目录的绝对路径，返回的是File数组
		File[] files = new File(path).listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isFile()) {
				System.out.println(file.getName());
			} else if (file.isDirectory()) {
				System.out.println("Directory");
				visitFile(file.getPath());
			} else {
				System.out.println("Error");
			}
		}
	}

}
