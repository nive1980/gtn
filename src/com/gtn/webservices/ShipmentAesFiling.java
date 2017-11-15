package com.gtn.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Shipment;
import com.gtn.service.ExportOperationService;
import com.gtn.webservices.dto.ShipmentAesFilingHelper;
import com.gtn.webservices.dto.ShipmentDetailsReq;
import com.gtn.webservices.dto.ShipmentDetailsRes;
import com.gtn.webservices.dto.ShipmentFilingRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class ShipmentAesFiling {

	private final String easeUrl = "http://localhost:8106/webservices/ead/shipments";
	private final String readShipmentUrl = "http://localhost:8106/webservices/ead/getshipments";
	
	public String fileShipmentToEase(Shipment shipment, ExportOperationService expoprtOpService) throws IOException, ApplicationException{
		ShipmentAesFilingHelper helper = new ShipmentAesFilingHelper();
		
		ShipmentFilingRequest request = helper.prepareShipmentFilingReq(shipment, expoprtOpService);
		
		//Client
		ClientResponse clientResponse = null;
		Client client = Client.create();
		WebResource resource = client.resource(easeUrl);
		
		clientResponse = resource.accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).post(ClientResponse.class, request);
		
		//change this
		//ProductLSResponse response = clientResponse.getEntity(ProductLSResponse.class);
		InputStream is = clientResponse.getEntityInputStream();//(WlsScreeningResponse.class);
		
		String op = streamToString(is);
		
		if (200 != clientResponse.getStatus()) {
			//logger;
			return null;
		}
		
		return op;
	}
	
	public ShipmentDetailsRes getShipmentFrmEase(ShipmentDetailsReq request) throws ApplicationException, IOException{
		if(request == null)
			throw new ApplicationException("Invalid request received for getting EASE shipments.", null);
		
		Client client = Client.create();
		WebResource webResource = client.resource(readShipmentUrl);
		
		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).post(ClientResponse.class, request);
		
		ShipmentDetailsRes res = response.getEntity(ShipmentDetailsRes.class);
		
		/*InputStream is = response.getEntityInputStream();//(WlsScreeningResponse.class);
		
		String op = streamToString(is);
		
		if (200 != response.getStatus()) {
			//logger;
			return null;
		}*/
		
		return res;

	}
	
	
	private String streamToString(InputStream in) throws IOException {
		  StringBuilder out = new StringBuilder();
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  for(String line = br.readLine(); line != null; line = br.readLine()) 
		    out.append(line);
		  br.close();
		  return out.toString();
	}
}
