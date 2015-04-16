package com.cogentsolutions.service;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.cogentsolutions.model.IpFreelyCounter;

public class TransformerUtil {

	public static String counterToXml(IpFreelyCounter counter) {
		
		String xml = null;
		try{
			JAXBContext con = JAXBContext.newInstance(IpFreelyCounter.class);
			Marshaller mar = con.createMarshaller();
			StringWriter writer = new StringWriter();
			mar.marshal(counter, writer);
			xml = writer.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return xml;
		
	}
}
