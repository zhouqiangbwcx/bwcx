package com.see.utl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author vma
 */
public class ManageClassLoader {
	DynamicClassLoader dc = null;

	Long lastModified = 0l;
	Class c = null;

	// 加载类， 如果类文件修改过加载，如果没有修改，返回当前的
	public Class loadClass(String name) throws Exception {
		if (isClassModified(name)) {
			dc = new DynamicClassLoader();
			return c = dc.findClass(getBytes(name));
		}
		return c;
	}

	// 判断是否被修改过
	private boolean isClassModified(String filename) {
		boolean returnValue = false;
		File file = new File(filename);
		if (file.lastModified() > lastModified) {
			returnValue = true;
		}
		return returnValue;
	}

	// 从本地读取文件
	private byte[] getBytes(String filename) throws Exception {
		String key = "umF1itDagHw=";
		byte[] inputStrFile = DESCoder.decryptBASE64(new String (IOUtil.getBytes(filename)));

		byte[] outputDataFile = DESCoder.decrypt(inputStrFile, key);
		return outputDataFile;
	}

	// 从本地读取文件
	// private byte[] getBytes(String filename) throws Exception {
	// File file = new File("C:\\User.class");
	// long len = file.length();
	// lastModified = file.lastModified();
	// byte raw[] = new byte[(int) len];
	// FileInputStream fin = new FileInputStream(file);
	// int r = fin.read(raw);
	// if (r != len) {
	// throw new IOException("Can't read all, " + r + " != " + len);
	// }
	// fin.close();
	//
	// // String key = "umF1itDagHw=";
	// // byte[] inputStrFile = DESCoder.decryptBASE64(IOUtil.read(filename));
	// //
	// // byte[] outputDataFile = DESCoder.decrypt(inputStrFile, key);
	// //
	// // String outputStrFile = new String(outputDataFile);
	// //
	// // System.err.println("解密后:\t" + outputStrFile);
	// // return outputDataFile;
	// }
}