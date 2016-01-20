package com.mycompany;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@Path("/customer")
public class CustomerService {

	String fileName = "D:/jvms7.pdf";

	@GET
	@Path("/get")
	@Produces("application/xml")
	public Customers getCustomer() throws IOException {

		Customer customer = new Customer(1,"Mamuka","IT","Brooklyn",7000.0);
		Customer customer2 = new Customer(2,"Mamuka","IT","Miami",7500.0);
		Customer customer3 = new Customer(3,"Mamuka","IT","Manhattan",7600.0);
		Customers customers = new Customers();
		customers.getCustomers().add(customer);
		customers.getCustomers().add(customer2);
		customers.getCustomers().add(customer3);
		return customers;
	}
	
	@GET
	@Path("/getp")
	@Produces("application/pdf")
	public Response getFile(ServletRequest request) throws IOException {

		ServletContext servletContext = request.getServletContext();
		URL res = servletContext.getResource(fileName);

		File file = new File(fileName);
		FileUtils.copyURLToFile(res, file);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=new-jvms7.pdf");
		return response.build();

	}

	@GET
	@Path("/getpdf")
	@Produces("application/pdf")
	public Response getFileName() throws IOException {

		File file = new File(fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=new-jvms7.pdf");
		return response.build();

	}

	@GET
	@Path("/getfile")
	@Produces("application/x-octet-stream")
	public Response getFila() {

		StreamingOutput stout = new StreamingOutput() {

			@Override
			public void write(OutputStream arg0) throws IOException,
					WebApplicationException {

				BufferedOutputStream bout = new BufferedOutputStream(arg0);
				try {
					//URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
					File file = new File("D:\\RESTfulProject.zip");
					FileInputStream fis = new FileInputStream(file);
					bout.write(IOUtils.toByteArray(fis));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		return Response.ok(stout).header("Content-Disposition",
				"attachment; filename=RESTfulProject.zip").build();

	}

}
