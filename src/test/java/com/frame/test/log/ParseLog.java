package com.frame.test.log;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ParseLog {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ParseLog.class);

	private static String VIEWSTRING = "{\"EVS\"";

	/**
	 * 用戶名，用戶行為，以key-value方式存储
	 * 用户行为 以json方式存储
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			LOGGER.info("以行为单位读取文件内容，一次读一整行：");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			Map<String, Object> map = new HashMap<String, Object>();
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains(VIEWSTRING)) {
					int userindex = tempString.indexOf(VIEWSTRING);
					String evsData = tempString.substring(userindex,tempString.length());
					int done = tempString.indexOf(VIEWSTRING);


					String ewId = tempString.substring(done + VIEWSTRING.length());
					new ArrayList<>().add(evsData);
					LOGGER.info(evsData);

					if (evsData != null && evsData != "") {
						try {
							Object json= JSONObject.parse(evsData);


						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
			reader.close();
			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				System.out.println(entry.getKey() + "-----------" + entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void main(String[] args) {
		ParseLog.readFileByLines("D:\\1.log");
	}

}