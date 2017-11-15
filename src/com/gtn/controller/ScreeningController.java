package com.gtn.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtn.dto.LicenseScreeningInput;
import com.gtn.dto.LicenseScreeningResponse;
import com.gtn.dto.ProductLSResponse;
import com.gtn.dto.ProductLSResponseDto;
import com.gtn.dto.ScreeningEntity;
import com.gtn.dto.WlsResponse;
import com.gtn.dto.WlsScreeningOutput;
import com.gtn.dto.WlsScreeningResponse;
import com.gtn.exception.ApplicationException;
import com.gtn.model.DplScreeningValue;
import com.gtn.model.RestResponse;
import com.gtn.service.ExportOperationService;
import com.gtn.service.GenericService;
import com.gtn.service.LicenceScreeningService;
import com.gtn.service.WatchListScreeningService;

/**
 * REST layer for managing people.
 * 
 * @author Adapted from http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
 */
@RestController
public class ScreeningController {

	
	@Autowired
	private WatchListScreeningService watchListScreeningService;
	
	@Autowired
	private LicenceScreeningService licenceScreeningService;
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private ExportOperationService exportService;
		
	/**
	 * 
	 * 
	 * HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		List<ScreeningEntity> screenings = mapper.readValue(entity, new TypeReference<List<WlsScreeningListInp>>() { });*/
		
		//List<ScreeningEntity> screenings = requestParam;
		
		/*JSONArray entities = new JSONArray(entity);
		List<ScreeningEntity> screenings = new ArrayList<ScreeningEntity>();
		
		for(int i=0; i<entities.length(); i++){
			JSONObject obj = entities.getJSONObject(i);
			screenings.add(populateEntity(obj));
		}
	 * 
	 * 
	 * 
	 * File f = new File("C:\\Users\\lenovo\\Desktop\\GTN\\wlsResponse.txt");
			FileWriter fw = new FileWriter(f);
			fw.write(response);
			fw.close();
			
			
			
	 * File f = new File("C:\\Users\\lenovo\\Desktop\\GTN\\wlsResponse.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String sCurrentLine;
			String response = "";
			
			while ((sCurrentLine = br.readLine()) != null) {
				response += sCurrentLine;
			}
	 */

	
	/**
	 * watch list screening
	 * @param entity
	 * @return
	 * @throws JSONException
	 * @throws JAXBException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/doWatchListScreening", method = RequestMethod.POST)
	public ResponseEntity<WlsResponse> doWatchListScreening(@RequestBody List<ScreeningEntity> screenings, HttpServletRequest request) throws JAXBException, IOException{
	
		HttpSession session = request.getSession();
		
		List<WlsScreeningResponse> results = new ArrayList<WlsScreeningResponse>();
		
		String responseStr = null;

		WlsResponse response = new WlsResponse();
		
		Map graphData = new HashMap<>();
		
		List<Map> nodes = new ArrayList<Map>();
		List<Map> links = new ArrayList<Map>();
		
		int group = 0;
		int srcIndex = 0;
		int targetIdx = 0;
		
		for(ScreeningEntity screeningEntity : screenings){
			try{
				responseStr = watchListScreeningService.doWatchListScreening(screeningEntity);
				
			}catch(ApplicationException e){
				response.setSuccess(false);
				response.setMsg(e.getMessage());
				return new ResponseEntity<WlsResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				//break outer;
			}
			WlsScreeningResponse responseObj = null;
			
			try{
				responseObj = watchListScreeningService.translateXmlResponse(responseStr);
			}catch(Exception e){
				response.setSuccess(false);
				response.setMsg("Error parsing screening respose.");
				return new ResponseEntity<WlsResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
			try{
				//save screening results
				if(responseObj.getHttsReturned()!=null)
					saveScreeningResult(screeningEntity, responseObj.getHttsReturned().getHits());
				else
					saveScreeningResult(screeningEntity, 0);
				
			}catch(ApplicationException e){
				response.setSuccess(false);
				response.setMsg(e.getMessage());
				return new ResponseEntity<WlsResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			/**
			 * graph data preperation
			 */
			Map nodeVal = new HashMap();
			int source = srcIndex;
			nodeVal.put("name", screeningEntity.getName());
			nodeVal.put("fullName", screeningEntity.getName());
			nodeVal.put("group", group++);
			nodeVal.put("dplScore", 0);
			
			nodeVal.put("country", screeningEntity.getCountryName());
			nodeVal.put("agency", "-");
			nodeVal.put("category", "-");
			nodeVal.put("ntype", "root");
			
			targetIdx++;
			
			nodes.add(nodeVal);
			
			List<WlsScreeningOutput> hitList = responseObj.getHttsReturned().getWlsList().getWlsScreenings();
			
			if(hitList!=null){
				int maxLimit = 0;
				Map<String, List<Map>> agencyLinks = new HashMap<String, List<Map>>();
				
				for(WlsScreeningOutput op: hitList){
					
					if(maxLimit++ > 50)
						break;
					
					Map tempMap = new HashMap();
					
					tempMap.put("fullName", op.getDpl_name());
					tempMap.put("name", formName(op.getDpl_name()));
					
					tempMap.put("group", group);
					if(!isEmpty(op.getDpl_score())){
						tempMap.put("dplScore", Integer.parseInt(op.getDpl_score()));
					}else{
						tempMap.put("dplScore", 0);
					}
					
					tempMap.put("country", op.getCountry());
					tempMap.put("agency", op.getDpl_agency());
					tempMap.put("category", op.getDpl_category());
					tempMap.put("ntype", "leaf");
					
					nodes.add(tempMap);
					
					Map tempLink = new HashMap();
					
					tempLink.put("source", source);
					tempLink.put("target", targetIdx);
					
					if(!isEmpty(op.getDpl_score())){
						tempLink.put("value", Integer.parseInt(op.getDpl_score()));
					}else{
						tempLink.put("value", 0);
					}
					//tempLink.put("type", "twofive");
					links.add(tempLink);
					
					/**
					 * add same agency links
					 */
					/*if(!isEmpty(op.getDpl_agency())){
						Map map = new HashMap();
						
						map.put("source", srcIndex);
						map.put("target", targetIdx);
						map.put("value", 1);
						
						if(agencyLinks.containsKey(op.getDpl_agency())){
							List<Map> oldEntry = agencyLinks.get(op.getDpl_agency());
							oldEntry.add(map);
							//agencyLinks.put(op.getDpl_agency(), oldEntry);
						}else{
							List<Map> nEntry = new ArrayList<Map>();
							nEntry.add(map);
							agencyLinks.put(op.getDpl_agency(), nEntry);
						}
					}*/
										
					srcIndex++;
					targetIdx++;
				}//for loop end
				
			}
			
			group++;
			srcIndex++;
			
			results.add(responseObj);
		}
		
		
		response.setScreeningInp(screenings);
		response.setScreeningResponse(results);
		response.setMsg("Success");
		response.setSuccess(true);
		
		/**
		 * prepare intra connections based on same agency
		 */
		connectByAgency(nodes, links);
		
		graphData.put("nodes", nodes);
		graphData.put("links", links);
		
		response.setGraphData(graphData);
		
		//serializeAddressJDK7(response);
		
		/*WlsResponse response = deserialzeAddressJDK7("C:\\Users\\lenovo\\Desktop\\GTN\\wlsResponse.txt");*/
		
		for(int i=0; i<response.getScreeningResponse().size(); i++){
			if(response.getScreeningResponse().get(i)!=null)
				response.getScreeningInp().get(i).setHits(response.getScreeningResponse().get(i).getHttsReturned().getHits());
		}
		
		return new ResponseEntity<WlsResponse>(response, HttpStatus.OK);	
	}
	
	private void connectByAgency(List<Map> nodes, List<Map> links){
		
		int commonGroup = 0;
		int groupNum = 1;
		
		Map groupMap = new HashMap();
		Map<String, List<Integer>> groupedAgencies = new HashMap<String, List<Integer>>();
		
		for(int i=0; i<nodes.size(); i++){
			Map nodeMap = nodes.get(i);
			
			String agency = (String) nodeMap.get("agency");
			Integer group = (Integer) nodeMap.get("group");
			
			addToAgencyMap(agency, groupedAgencies, i);
			
			if(isEmpty(agency)){
				nodeMap.put("group", commonGroup);
				
			}else{
				if(groupMap.containsKey(agency)){
					nodeMap.put("group", groupMap.get(agency));
				}else{
					nodeMap.put("group", groupNum);
					groupMap.put(agency, groupNum++);
				}
			}
			
			
			if(isEmpty(agency))
				continue;
			
			for(int j=0; j<nodes.size(); j++){
				if(i==j)
					continue;
				
				Map nodeMapInner = nodes.get(j);
				//if(agency.equals((String) nodeMapInner.get("agency")) && group == (Integer) nodeMapInner.get("group")){
				if(agency.equals((String) nodeMapInner.get("agency"))){
					Map linkMapVal = new HashMap();
					linkMapVal.put("source", i);
					linkMapVal.put("target", j);
					linkMapVal.put("value", getRandomNo(1, 10));
					
					//links.add(linkMapVal);
				}
			}
		}
		
		
		Set<String> keySet = groupedAgencies.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String agency = it.next();
			List<Integer> list = groupedAgencies.get(agency);
			for(int i=0; i<list.size(); i++){
				if(i == list.size()-1){
					Map linkMapVal = new HashMap();
					linkMapVal.put("source", list.get(i));
					linkMapVal.put("target", list.get(0));
					linkMapVal.put("value", getRandomNo(1, 10));
					//links.add(linkMapVal);
				}else{
					Map linkMapVal = new HashMap();
					linkMapVal.put("source", list.get(i));
					linkMapVal.put("target", list.get(i+1));
					linkMapVal.put("value", getRandomNo(1, 10));
					//links.add(linkMapVal);
				}
			}
		}
		
	}
	
	private void addToAgencyMap(String agency, Map<String, List<Integer>> groupedAgencies, int pos){
		
			if(groupedAgencies.containsKey(agency)){
				List<Integer> list = groupedAgencies.get(agency);
				list.add(pos);
			}else{
				List<Integer> list = new ArrayList<Integer>();
				list.add(pos);
				groupedAgencies.put(agency, list);
			}
	}
	
	private String formName(String name){
		
		if(isEmpty(name))
			return name;
		
		String[] words = name.split(" ");
		
		if(words.length > 2){
			String finalOp = words[0]+" "+words[1]+"...";
			return finalOp;
		}else{
			return name;
		}
		
	}
	
	private int getRandomNo(int minimum, int maxValue){
		Random rn = new Random();
		int num  = minimum + rn.nextInt(maxValue - minimum + 1);
		return num;
	}
	
	private DplScreeningValue saveScreeningResult(ScreeningEntity screeningEntity, int hits) throws ApplicationException{
		DplScreeningValue dplValue = new DplScreeningValue();
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		BeanUtils.copyProperties(screeningEntity, dplValue);
		
		String uniqueID = UUID.randomUUID().toString();
		
		while(true){
			DplScreeningValue oldVal = (DplScreeningValue) genericService.read(DplScreeningValue.class, uniqueID);
			if(oldVal == null){
				break;
			}else{
				uniqueID = UUID.randomUUID().toString();
			}
		}
		
		dplValue.setEntityId(uniqueID);
		dplValue.setScreenedBy(userDetail.getUsername());
		dplValue.setScreenedOn(new Date());
		dplValue.setHits(hits);
		
		genericService.saveEntity(dplValue);
		
		return dplValue;
	}
	
	private void serializeAddressJDK7(WlsResponse results) {

		try (ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\GTN\\wlsResponse.txt"))) {

			oos.writeObject(results);
			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@SuppressWarnings("unchecked")
	private WlsResponse deserialzeAddressJDK7(String filename) {

		WlsResponse address = null;

		try (ObjectInputStream ois
			= new ObjectInputStream(new FileInputStream(filename))) {

			address = (WlsResponse) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return address;
	}
	
	@RequestMapping(value = "/resultWatchListScreening", method = RequestMethod.GET)
	public ModelAndView resultWatchListScreening(HttpServletRequest request, ModelMap map){
		
		ModelAndView mv = new ModelAndView("resultWatchListScreening.jsp");
		WlsScreeningResponse responseObj = (WlsScreeningResponse) request.getSession().getAttribute("wlsResult");
		ScreeningEntity screeningEntity = (ScreeningEntity) request.getSession().getAttribute("screeningEntity");
		
		mv.addObject("wlsResults", responseObj);
		screeningEntity.setEntityType(getEntityTypeText(screeningEntity.getEntityType()));
		
		mv.addObject("screeningEntity", screeningEntity);
		
		return mv;
	}
	
	/*public static String prettyFormat(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}*/
	
	/*private ScreeningEntity populateEntity(JSONObject obj) throws JSONException{
		ScreeningEntity entity = new ScreeningEntity();
		
		entity.setAddressLine1(obj.getString("addres_en"));
		entity.setAddressLine2(obj.getString("addres_en2"));
		entity.setAddressLine3(obj.getString("addres_en3"));
		entity.setCity(obj.getString("city_en"));
		entity.setCountry(obj.getString("country_en"));
		entity.setEntityId(obj.getString("id_en"));
		entity.setEntityType(obj.getString("entity_sl"));
		entity.setName(obj.getString("name_en"));
		entity.setOrgType(obj.getString("C_group"));
		entity.setState(obj.getString("state_en"));
		
		entity.setZip(obj.getString("zip_en"));
		return entity;
	}*/
	
	
	private String getEntityTypeText(String code){
		Map<String, String> map = new HashMap<String, String>();
		map.put("CON", "Consignee");
		map.put("FF", "Freight Forwarder");
		map.put("C", "Carrier");
		map.put("E", "Exporter");
		
		if(!isEmpty(code)){
			return map.get(code);
		}
		return null;
	}
	
	private boolean isEmpty(String str){
		if(str != null && !str.isEmpty())
		{
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * License Screening start
	 */
	
	@RequestMapping(value = "/doLicenseScreeningServer", method = RequestMethod.POST)
	public ResponseEntity<LicenseScreeningResponse> doLicenseScreeningServer(@RequestBody LicenseScreeningInput input) throws IOException{
		String op = licenceScreeningService.doLicenceScreening(input);
		//String op = licenceScreeningService.authenticateUser();
		//System.out.println(op);
		
		LicenseScreeningResponse response = null;
		
		if(!isEmpty(op)){
			ObjectMapper mapper = new ObjectMapper();
			
			response = mapper.readValue(op, LicenseScreeningResponse.class);
			
			//response.getResultList().get(0).setLicReq("No");
		}
		response.setInp(input);
		
		//remove this
		response.getEldsHostedServiceView().get(0).setExpCtry(input.getExpCountry());
		
		if(response.getEldsHostedServiceView().get(0)!=null){
			if(response.getEldsHostedServiceView().get(0).getReportReq().isEmpty())
			{
				response.getEldsHostedServiceView().get(0).setReportReq("No");
			}
			if(response.getEldsHostedServiceView().get(0).getLicReq().isEmpty())
			{
				response.getEldsHostedServiceView().get(0).setLicReq("No");
			}
		}
		
		return new ResponseEntity<LicenseScreeningResponse>(response, HttpStatus.OK);
		//System.out.println(input.toString());
		
	}
	
	@RequestMapping(value = "/doLicenseScreening", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ProductLSResponseDto>> doLicenseScreening(@RequestBody LicenseScreeningInput input) throws IOException{
		ResponseEntity<RestResponse<ProductLSResponseDto>> response = null;
		
		ProductLSResponseDto dto = new ProductLSResponseDto();
		
		if("product_no".equals(input.getType())){
			try{
				String eccnNo = exportService.getEccnForProduct(input.getInput());
				
				if(eccnNo == null){
					RestResponse<ProductLSResponseDto> restResponse = new RestResponse<ProductLSResponseDto>(false, "ECCN not found for product : "+input.getInput(), dto);
					return new ResponseEntity<RestResponse<ProductLSResponseDto>>(restResponse, HttpStatus.BAD_REQUEST);
				}else{
					input.setInput(eccnNo);
				}
				
			}catch(ApplicationException e){
				RestResponse<ProductLSResponseDto> restResponse = new RestResponse<ProductLSResponseDto>(false, "Error", dto);
				return new ResponseEntity<RestResponse<ProductLSResponseDto>>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		ProductLSResponse plsResponse = licenceScreeningService.doLicenceScreeningNew(input);
		plsResponse.setMessageHeader(null);
		
		dto.setResponse(plsResponse);
		dto.setInput(input);
		
		RestResponse<ProductLSResponseDto> restResponse = new RestResponse<ProductLSResponseDto>(true, "Successful", dto);
		
		return new ResponseEntity<RestResponse<ProductLSResponseDto>>(restResponse, HttpStatus.OK);
	}
	
}
