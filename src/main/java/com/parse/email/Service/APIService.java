package com.parse.email.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface APIService {
	public void runDocker(String type);
	public int createCSVConfig(String name) throws IOException;
}
