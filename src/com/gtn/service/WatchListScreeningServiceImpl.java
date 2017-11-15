package com.gtn.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.gtn.dto.ScreeningEntity;
import com.gtn.dto.WlsMessageHeader;
import com.gtn.dto.WlsScreeningInput;
import com.gtn.dto.WlsScreeningRequest;
import com.gtn.dto.WlsScreeningResponse;
import com.gtn.exception.ApplicationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class WatchListScreeningServiceImpl implements WatchListScreeningService{

	/*@Autowired
	WebServiceTemplate webServiceTemplate;*/
	
	private static final int STATUS_OK = 200;
	
	
	//easeDevUrl
	final String userId = "WZFKArU0b9Xsv4L1ET7G1g==";
	final String password = "tIUtf/vlEoJmKAp964wg6Q==";
	
	//local
	/*final String userId = "OCR";
	final String password = "OCREASE";*/
	
	final String wlsDevUrl = "http://easedev.ocr-inc.com/webservices/dpss/dowatchlistscreening";
	
	final String wlsLocalUrl = "http://localhost:8090/webservices/dpss/dowatchlistscreening";
	
	final String wlsLocalSpringUrl = "http://localhost:8106/webservices/dpss/dowatchlistscreening";
	
	final String wlsNdrUrl = "http://ndr-test.ocr-inc.com:8105/webservices/dpss/dowatchlistscreening";
	final String wlsTestUrl = "http://easetest.ocr-inc.com/webservices/dpss/dowatchlistscreening";
		
	/**
	 * Call Watch list screening
	 * @param entity
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@Override
	public String doWatchListScreening(ScreeningEntity entity) throws JAXBException, IOException, ApplicationException{
		
		
		/*WlsScreeningRequest request = populateScreenRequest(entity);
		
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		
		Object response = webServiceTemplate.marshalSendAndReceive(
				wlsDevUrl, request);
		
		WlsScreeningResponse msg = (WlsScreeningResponse) response;
		
		return null;*/
		
		/*String wlsXmlInput = prepareWLSInput(entity);
		
		URL url = new URL(wlsDevUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept", "application/xml");
		connection.setRequestProperty("Content-Type", "application/xml");
		
		OutputStream os = connection.getOutputStream();
		os.write(wlsXmlInput.getBytes());
        os.flush();
        
        if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            //throw new RuntimeException("Failed : HTTP error code : "
               // + connection.getResponseCode());
        }
		
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		os.close();
		reader.close(); 
		
		connection.disconnect();
		
		System.out.println(sb.toString());*/
		
		ClientResponse clientResponse = null;
		
		Client client = Client.create();
		WebResource webResource = client.resource(wlsDevUrl);
		WlsScreeningRequest request = populateScreenRequest(entity);
		//clientResponse = webResource.accept("application/xml").type("application/xml").post(ClientResponse.class, request);
		
		try{
			clientResponse = webResource.accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).post(ClientResponse.class, request);
		}catch(Exception e){
			throw new ApplicationException(e.getMessage(), e);
			//e.printStackTrace();
		}
		
		//WlsScreeningOutput op1 = clientResponse.getEntity(WlsScreeningOutput.class);
		InputStream is = clientResponse.getEntityInputStream();//(WlsScreeningResponse.class);
		
		String op = streamToString(is);
		
		if (STATUS_OK != clientResponse.getStatus()) {
			//logger;
			return null;
		}
		
		return op;
		//return sb.toString();
	}
	
	private String streamToString(InputStream in) throws IOException {
		  StringBuilder out = new StringBuilder();
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  for(String line = br.readLine(); line != null; line = br.readLine()) 
		    out.append(line);
		  br.close();
		  return out.toString();
	}
	
	/**
	 * convert java object to XML string
	 * @param entity
	 * @return
	 * @throws JAXBException
	 */
	private String prepareWLSInput(ScreeningEntity entity) throws JAXBException{
		
		WlsScreeningRequest request = populateScreenRequest(entity);
		
		JAXBContext context = JAXBContext.newInstance(WlsScreeningRequest.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		StringWriter sw = new StringWriter();
		m.marshal(request, sw);
		
		return sw.toString();
		
	}
		
	
	/**
	 * prepare java object for screening request
	 * @param entity
	 * @return
	 */
	private WlsScreeningRequest populateScreenRequest(ScreeningEntity entity){
		
		WlsScreeningRequest request = new WlsScreeningRequest();
		
		WlsMessageHeader header = new WlsMessageHeader();
		WlsScreeningInput inp = new WlsScreeningInput();
		
		//header.setMessageType("SAPR");
		header.setMessageType("ERPR");
		header.setUserId(userId);
		header.setPassword(password);
		
		inp.setAddr1(entity.getAddressLine1());
		inp.setAddr2(entity.getAddressLine2());
		inp.setCountry(entity.getCountry());
		inp.setName(entity.getName());
		
		request.setMsgHeader(header);
		request.setScreeningInput(inp);
		
		return request;
	}
	
	/**
	 * Convert XML string to java object
	 * @param xmlStr
	 * @return
	 * @throws JAXBException
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public WlsScreeningResponse translateXmlResponse(String xmlStr) throws JAXBException, UnsupportedEncodingException{
		 
		JAXBContext jaxbContext = JAXBContext.newInstance(WlsScreeningResponse.class);
		Unmarshaller um = jaxbContext.createUnmarshaller(); 
		
		InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
		Reader reader = new InputStreamReader(is,"UTF-8");
		
		WlsScreeningResponse response = (WlsScreeningResponse) um.unmarshal(reader);
		return response;
	}
}
