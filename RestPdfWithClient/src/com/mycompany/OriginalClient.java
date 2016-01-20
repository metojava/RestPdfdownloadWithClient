package com.mycompany;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class OriginalClient {

	/**
	 * @param args
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		String ur = "http://localhost:8080/res/rest/customer/get";
		URL url = null;
		JAXBContext context = null;
		Customers customers = null;

		try {
			url = new URL(ur);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(123);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/xml");
		try {
			context = JAXBContext.newInstance(Customers.class);
			InputStream xml = connection.getInputStream();
			customers = (Customers) context.createUnmarshaller().unmarshal(xml);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		connection.disconnect();
		System.out.print(customers.getCustomers().get(0).getAddress());


		
	}

}
