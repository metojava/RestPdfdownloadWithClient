package com.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

public class Clienti {

	static String loca = "C:\\Users\\User\\Desktop\\files";
	static String url = "http://localhost:8080/res/rest/customer/getfile";
	
	public static void main(String[] args) throws IOException {
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(url).request().get();

		FileOutputStream fos = new FileOutputStream(new File(loca+"\\esa.zip"));
		InputStream ins = (InputStream) response.getEntity();
		
		int len = 0;
		byte[] buf = new byte[4096];
		while ((len = ins.read(buf)) != -1) {

			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		ins.close();
		
		
		
	}
	
	public static File getFileFromRest() throws IOException
	{
		File file = new File(loca+"\\esa.zip");
		Client client = ClientBuilder.newClient();
		Response response = client.target(url).request().get();
		
		FileOutputStream fos = new FileOutputStream(file);
		InputStream ins = (InputStream) response.getEntity();
		
		IOUtils.copy(ins, fos);
		ins.close();
		fos.close();
		
		return file;
		
	}

}
