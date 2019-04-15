package com.eussi.schedulejob.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.output.FileWriterWithEncoding;


public class FileUtils {

		/**
		 * 加载配置文件,传入参数为类路径
		 * 
		 * @param path
		 * @return
		 */
		public static Properties LoadConfig(String path) {
			String filePath = FileUtils.class.getClassLoader().getResource("").getPath() + path;
			Properties prop = new Properties();
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(new File(filePath));
				prop.load(fin);
				return prop;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fin != null)
					try {
						fin.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				fin = null;
			}
			return prop;
		}

		/**
		 * 读取文件内容
		 * 
		 * @param file
		 * @return
		 */
		public static List<String> getFileContent(File file) {
			List<String> strs = new ArrayList<String>();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = br.readLine()) != null) {
					if(!line.startsWith("#")&&!"".equals(line.trim())) {
						strs.add(line);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null)
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return strs;
		}


		/**
		 * 在路径下生成文件并将内容写入文件
         *
		 */
		public static boolean write2File(File file, String content, String encoding) {
			BufferedWriter bw = null;
			try {
				File dir = new File(file.getParent());
				if (!dir.exists()) {
					dir.mkdirs();
				}
				bw = new BufferedWriter(new FileWriterWithEncoding(dir + "/" + file.getName(), encoding));
				bw.write(content == null ? "" : content);
				bw.flush();
				return true;
			} catch (IOException e) {
				return false;
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

		}


}
