package com.mycompany;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class asdf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "/com/mycompany/jvms7.pdf";
		File fi = new File(fileName);
		InputStream ins = CustomerService.class.getResourceAsStream(fileName);
		
		try {
			System.out.println(ins.available());
			System.out.println(fi.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
