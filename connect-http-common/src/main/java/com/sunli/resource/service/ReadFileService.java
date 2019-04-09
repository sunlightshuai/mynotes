package com.sunli.resource.service;

import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * 读取配置文件
 * @author Administrator
 *
 */
public interface ReadFileService {

	public InputStream readFile(String path);
	
	public FileChannel readFileChannel(String path);
	
	public void closeInputStream(InputStream inputStream);
}
