package com.parse.email.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class APIServiceImpl implements APIService {

	@Override
	public void runDocker(String type) {
		// TODO Auto-generated method stub
		Process p;
		try {
			p=Runtime.getRuntime().exec("sudo docker-compose up");
			p.waitFor();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public int createCSVConfig(String name) throws IOException {
		// TODO Auto-generated method stub
		String homeDir = System.getProperty("user.home");
		String dest=homeDir+"/Utilities/CSV/csv_conf.conf";
		File file = new File(dest);
		if (file.createNewFile())
		{
		    System.out.println("File is created!");
		} else {
		    System.out.println("File already exists.");
		}
		FileWriter writer = new FileWriter(file);
		String str = "input {\n" + 
				"	file {\n" + 
				"		path => \"/usr/share/input/*.csv\"\n" + 
				"		sincedb_path => \"/dev/null\"\n" + 
				"		start_position => \"beginning\"\n" + 
				"	}\n" + 
				"}\n" + 
				"\n" + 
				"filter {\n" + 
				"    csv {\n" + 
				"        autodetect_column_names => true\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"output {\n" + 
				"  elasticsearch {\n" + 
				"    hosts => \"elasticsearch:9200\"\n" + 
				"    index => \"sample\"\n" + 
				"    document_type => \"temp\"\n" + 
				"  }  \n" + 
				"}";
		writer.write(str);
		
		return 0;
	}

}
