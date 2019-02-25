package com.hoperun.resource.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;


public abstract class AbstractReadFileService implements ReadFileService{

	@Override
	public InputStream readFile(String path) {
		File file = new File(path);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileInputStream;
	}

	@Override
	public FileChannel readFileChannel(String path) {
		File file = new File(path);
		FileChannel fileChannel = null;
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(file);
			fileChannel = fileInputStream.getChannel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileChannel;
	}
	
	@Override
	public void closeInputStream(InputStream inputStream) {
		if (null != inputStream) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
