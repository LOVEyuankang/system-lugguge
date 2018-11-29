package com.net.metadata.utils;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

public class RuleDRL {

	@SuppressWarnings("resource")
	public static void CreateDRL()
	{
		try {
			URL url=RuleDRL.class.getClassLoader().getResource("resources/Sample.drl");
			System.out.print(url.toString());
			File file = new File(url.toString());
			  if (!file.exists()) {
				    file.createNewFile();
				   }
			  @SuppressWarnings("unused")
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
