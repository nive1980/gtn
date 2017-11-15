package com.gtn.service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gtn.dto.LicenseScreeningAuthDto;
import com.gtn.dto.LicenseScreeningInput;
import com.gtn.dto.MessageHeader;
import com.gtn.dto.ProductLSCriteria;
import com.gtn.dto.ProductLSRequest;
import com.gtn.dto.ProductLSResponse;
import com.gtn.helper.ExportOperationHelper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class LicenceScreeningServiceImpl implements LicenceScreeningService{
	
	private static final int STATUS_OK = 200;
	
	final String partUrl = "http://global.ocr-inc.com:9105/easewebservices/licence/screenings/bypart";
	final String eccnUrl = "http://global.ocr-inc.com:9105/easewebservices/licence/screenings/byeccn";
	final String nrcUrl = "http://global.ocr-inc.com:9105/easewebservices/licence/screenings/bynrc";
	final String usmlUrl = "http://global.ocr-inc.com:9105/easewebservices/licence/screenings/byusml";
	
	/*final String partUrl = "http://localhost:8090/easewebservices/licence/screenings/bypart";
	final String eccnUrl = "http://localhost:8090/easewebservices/licence/screenings/byeccn";
	final String nrcUrl = "http://localhost:8090/easewebservices/licence/screenings/bynrc";
	final String usmlUrl = "http://localhost:8090/easewebservices/licence/screenings/byusml";*/
	
	final String authUrl = "http://global.ocr-inc.com:9105/easewebservices/user/authuser";
	final String subscribeUrl = "http://global.ocr-inc.com:9105/easewebservices/user/subscribeaccount";
	final String logoutUrl = "http://global.ocr-inc.com:9105/easewebservices/user/logout";
	
	/*final String authUrl = "http://localhost:8090/easewebservices/user/authuser";
	final String subscribeUrl = "http://localhost:8090/easewebservices/user/subscribeaccount";
	final String logoutUrl = "http://localhost:8090/easewebservices/user/logout";*/
	
	////// Latest Sunil changes //////
	
	final String localSpringUrl = "http://localhost:8106/webservices/elds/licensescreening";
	final String easeDevUrl = "http://easedev.ocr-inc.com/webservices/elds/licensescreening";
	
	@Override
	public ProductLSResponse doLicenceScreeningNew(LicenseScreeningInput input) throws IOException{
		
		ProductLSRequest request = prepareRequest(input);
		
		ClientResponse clientResponse = null;
		ObjectMapper mapper = new ObjectMapper();
		
		Client client = Client.create();
		WebResource resource = client.resource(easeDevUrl);
		
		clientResponse = resource.accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).post(ClientResponse.class, request);
		
		ProductLSResponse response = clientResponse.getEntity(ProductLSResponse.class);
		/*InputStream is = clientResponse.getEntityInputStream();
		
		String op = streamToString(is);*/
		
		return response;
	}
	
	private ProductLSRequest prepareRequest(LicenseScreeningInput input){
		ProductLSRequest request = new ProductLSRequest();
		
		MessageHeader msgHeader = prepareMsgHeader();
		request.setMessageHeader(msgHeader);
		
		ProductLSCriteria criteria = prepareLSCriteria(input);
		request.setProductLSCriteria(criteria);
		
		return request;
	}
	
	private MessageHeader prepareMsgHeader(){
		MessageHeader msgHeader = new MessageHeader();
		
		msgHeader.setUserID("cCQqDRuSoxZ8YyhGAx3Gng==");
		msgHeader.setPassword("PRZI4F4l6MRvPyEg/tp02g==");
		msgHeader.setMessageType("ERPR");
		
		return msgHeader;
	}
	
	private ProductLSCriteria prepareLSCriteria(LicenseScreeningInput input){
		ProductLSCriteria criteria = new ProductLSCriteria();
		
		criteria.setAccountNumber("S100");
		criteria.setActiveSbu("DEMO");
		criteria.setBomNumber("");
		criteria.setDestinationCountryCode(input.getImpCountry());
		criteria.setDirectUser("N");
		criteria.setDirectUserSbu("");
		criteria.setEccnNumber(input.getInput());
		criteria.setExportCountryCode(input.getExpCountry());
		criteria.setPartNumber("");
		criteria.setPersistAudit(false);
		criteria.setReferenceNo("");
		criteria.setScreenSubCompFlag(false);
		criteria.setUserName("OCR");
		
		if(!ExportOperationHelper.isEmpty(input.getPartNo())){
			criteria.setPartNumber(input.getPartNo());
		}
		
		return criteria;
	}
	
	
	
	/////////////////// OLD screening ///////////////
	
	@Override
	public String doLicenceScreening(LicenseScreeningInput input) throws IOException{
		
		String auth = authenticateUser();			
		//System.out.println(auth);
		
		/*LicenseScreeningRequest request = new LicenseScreeningRequest();
					
		request.setCtrlCtry("US");
		request.setExpCtry("AU");
		request.setProdNo("M-PRO007");
		request.setUserName("suniltest20");
		request.setDeviceType("android");
		request.setDeviceToken("111111111111111");*/
		
		ObjectNode request = prepareInput(input);
		
		ClientResponse clientResponse = null;
		ObjectMapper mapper = new ObjectMapper();
		
		Client client = Client.create();
		
		String url = null;
		
		if(input.getType().equals("product_no")){
			url = partUrl;
		}else if(input.getType().equals("eccn")){
			url = eccnUrl;
		}else if(input.getType().equals("nrc")){
			url = nrcUrl;
		}else if(input.getType().equals("usml")){
			url = usmlUrl;
		}
		
		WebResource webResource = client.resource(url);
		//WebResource webResource = client.resource(localSpringUrl);
		
		clientResponse = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, new ProductLSRequest());
		
		//WlsScreeningOutput op1 = clientResponse.getEntity(WlsScreeningOutput.class);
		InputStream is = clientResponse.getEntityInputStream();//(WlsScreeningResponse.class);
		
		//ProductLSResponse obj = clientResponse.getEntity(ProductLSResponse.class);
		
		String op = streamToString(is);
		
		//serializeAddressJDK7(op);
		
		//String op = "{ \"status\": \"SUCCESS\", \"regDate\": \"12\\/12\\/2014\", \"message\": null, \"resultList\": [ { \"category\": \"VII(H)\", \"lvsLimit\": \"\", \"resultSize\": 1, \"licExcep\": \"GBS,APP\", \"reportReq\": \"Yes\", \"salesOrderNo\": \"\", \"prodNo\": \"\", \"subProd\": \"\", \"itarCtrl\": \"Y\", \"nrcCtrl\": \"Y\", \"expCtry\": \"AUSTRALIA\", \"collect\": [ { \"description\": \"MLVII.h classified technical data\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.12\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B6, 125.4B7, 125.4B8, 125.4B9, 125.4B11, 125.4B12, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.18\", \"exceptions\": \"125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B6, 125.4B7, 125.4B8, 125.4B9, 125.4B11, 125.4B12, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h unclassified for MLVII.a plough or flail for mine clearance\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.13\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h unclassified for MLVII.a other than plough or flail for mine clearance\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.14\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for MLVII.b MT\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.15\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 1 25.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 1 25.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for MLVII.b, other than MT\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.16\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for MLVII.c MT\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.17\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 1 25.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 1 25.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for MLVII.c, other than MT\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.18\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.2C, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 1 25.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.2C, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 1 25.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for MLVII.e\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.19\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 125.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for VII.g plough or flail, cryogenic, or superconductive\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.20\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"124.2A, 124.2B, 124.2C, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 1 25.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.2C, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 1 25.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null }, { \"description\": \"MLVII.h for VII.g, other than plough or flail, other than cryogenic, other than cuperconductive\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"VII\", \"subEccnNo\": \"VII.21\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"No\", \"addtlReq\": \"\", \"reportResult\": null, \"cclLicExcep\": \"124.2A, 124.2B, 124.2C, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 1 25.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"exceptions\": \"124.2A, 124.2B, 124.2C, 124.3, 124.3A, 124.3B, 124.16, 125.4B1, 125.4B2, 125.4B3, 125.4B4, 125.4B5, 125.4B7, 125.4B8, 125.4B9, 125.4B10, 125.4B11, 125.4B13, 125.4C, 125.5, 1 25.5A, 125.5B, 125.5C, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4, 126.18\", \"licExcepUsed\": \"\", \"productNo\": \"\", \"basicLic\": null } ], \"licReq\": \"Yes\", \"ctrlCtry\": \"US\" } ], \"resultSize\": 1 }";
		//op = "{ \"status\": \"SUCCESS\", \"regDate\": \"12\\/12\\/2014\", \"message\": null, \"resultList\": [ { \"category\": \"\", \"lvsLimit\": \"\", \"resultSize\": 1, \"licExcep\": \"GBS,APP\", \"reportReq\": \"Yes\", \"salesOrderNo\": \"\", \"prodNo\": \"M-PRO007\", \"subProd\": \"\", \"itarCtrl\": \"Y\", \"nrcCtrl\": \"Y\", \"expCtry\": \"AUSTRALIA\", \"collect\": [ { \"description\": \"MLV.a.1,a.2,a.3,a.5,a.6,a.7,a.8,a.9,a.10,a.11,a.12,a.13.i,a.13.ii,a.14,a.15.ii,a.15.iii,a.16,a.17,a.18,a.19,a.20,a.21,a.22,a.23.ii,a.24,a.26,a.27,a.28,a.30,a.31,a.32,a.33,a.34,a.35,a.36,a.37\", \"reasonForControl\": \"ITAR\", \"eccnNo\": \"V\", \"subEccnNo\": \"V.01\", \"licAgency\": \"DDTC\", \"report\": null, \"licRquire\": \"Yes\", \"reportReq\": \"Yes\", \"addtlReq\": \"\", \"reportResult\": [ { \"reportNo\": \"R20\", \"reportType\": \"Registration for manufacturing, export, or brokering of defense articles\", \"reportDue\": \"Prior to manufacturing, export, or brokering\", \"requiredInfo\": \"1. Documentation that intended manufacturing or export registrant is authorized to do business in the United States.: 2. Whether the intended registrant or a senior officer has been indicted for or convicted of violation specified U.S. criminal statutes.: 3. Whether the intended registrant is owned or controlled by a foreign person.\", \"earSection\": \"22 CFR 122.2(b), 22 CFR 129.3, 22 CFR 129.4\", \"status\": \"Y\" }, { \"reportNo\": \"R21\", \"reportType\": \"Notification of intent to export major defense equipment\", \"reportDue\": \"Prior to export\", \"requiredInfo\": \"Signed contract and DSP-83 signed by applicant, foreign consignee, and end-user\", \"earSection\": \"22CFR 123.15(c), 22 CFR 124.11(c)\", \"status\": \"Y\" } ], \"cclLicExcep\": \"123.4, 123.4A1, 123.4A2, 123.4A3, 123.4A4, 123.4A5, 123.4B, 123.6, 123.13, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4\", \"exceptions\": \"123.4, 123.4A1, 123.4A2, 123.4A3, 123.4A4, 123.4A5, 123.4B, 123.6, 123.13, 126.4, 126.4A, 126.4C, 126.6, 126.6A, 126.6B, 126.6C, 126.16E1, 126.16E2, 126.16E3, 126.16E4\", \"licExcepUsed\": \"\", \"productNo\": \"M-PRO007\", \"basicLic\": null } ], \"licReq\": \"Yes\", \"ctrlCtry\": \"US\" } ], \"resultSize\": 1 } ";
		return op;
	}
	
	private void serializeAddressJDK7(String results) {

		try (ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\GTN\\lsResponse.txt"))) {

			oos.writeObject(results);
			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private ObjectNode prepareInput(LicenseScreeningInput input){
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		
		node.put("ctrlCtry", input.getImpCountry());
		node.put("expCtry", input.getExpCountry());
		node.put("userName", "suniltest20");
		node.put("deviceType", "android");
		node.put("deviceToken", "111111111111111");
				
		if(input.getType().equals("product_no")){
			node.put("prodNo", input.getInput());
		}else if(input.getType().equals("eccn")){
			node.put("eccnNo", input.getInput());
		}else if(input.getType().equals("nrc")){
			node.put("nrcNo", input.getInput());
		}else if(input.getType().equals("usml")){
			node.put("usmlNo", input.getInput());
		}		
		
		return node;
	}
	
	
	@Override
	public String authenticateUser() throws IOException{
		
		ClientResponse clientResponse = null;
		ObjectMapper mapper = new ObjectMapper();
		
		Client client = Client.create();
		WebResource webResource = client.resource(authUrl);
		LicenseScreeningAuthDto request = getUser();
		
		clientResponse = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, mapper.writeValueAsString(request));
		
		//WlsScreeningOutput op1 = clientResponse.getEntity(WlsScreeningOutput.class);
		InputStream is = clientResponse.getEntityInputStream();//(WlsScreeningResponse.class);
		
		String op = streamToString(is);
		
		if (STATUS_OK != clientResponse.getStatus()) {
			//logger;
			return null;
		}
		
		return op;
		
		/*JSONObject user = getUser();
		
		URL url = new URL(authUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		
		OutputStream os = connection.getOutputStream();
		os.write(user.toString().getBytes());
        os.flush();
        
        if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
          
        }
		
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		os.close();
		reader.close(); 
		
		//connection.disconnect();
		
		return sb.toString();*/
	}
		
	private String streamToString(InputStream in) throws IOException {
		  StringBuilder out = new StringBuilder();
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  for(String line = br.readLine(); line != null; line = br.readLine()) 
		    out.append(line);
		  br.close();
		  return out.toString();
	}
		
	private LicenseScreeningAuthDto getUser(){
		LicenseScreeningAuthDto userObj = new LicenseScreeningAuthDto();
		
		userObj.setUserId("suniltest20");
		userObj.setPassword("ocr123");
		userObj.setDeviceType("android");
		userObj.setDeviceToken("111111111111111");
		
		return userObj;
	}
}