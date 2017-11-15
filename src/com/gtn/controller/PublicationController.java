package com.gtn.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.dto.MarkDto;
import com.gtn.dto.PublicationCartDto;
import com.gtn.dto.PublicationDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Mark;
import com.gtn.model.Product;
import com.gtn.model.Publication;
import com.gtn.model.RestResponse;
import com.gtn.model.User;
import com.gtn.service.PublicationService;
import com.gtn.service.UserService;

@RestController
public class PublicationController {
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private UserService userService;

	@Autowired
	private GtnControllerHelper gtnControllerHelper;
	
	
	
	@RequestMapping(value="/getPublications",method = RequestMethod.GET)
	public List<PublicationDto> getPublications(@RequestParam String email, HttpServletRequest req) throws Exception {
		//System.out.println("username is "+usersDto);
		
		String type = req.getParameter("type");
		
		List<Product> subscribedPublicationProducts=publicationService.getSubscribedPublicationProducts(email);
		ArrayList<PublicationDto> publicationDtos= new ArrayList<PublicationDto>();
		Collection<Publication> publications= publicationService.getPublications(type);
		boolean isUnlimitedAccess=false;
		boolean isAnnotation=false;
		boolean buyNow=false;
		boolean hasPurchasedPublication=false;
		if (subscribedPublicationProducts != null && !subscribedPublicationProducts.isEmpty()) {
			for (Product  p1: subscribedPublicationProducts) {
				
				if(p1.getDisplayName().equals("Unlimited Access")){
					isUnlimitedAccess=true;
					
				}
				if(p1.getDisplayName().equals("Annotate Online")){
					isAnnotation=true;
				}
				
			}}
		
		if (publications != null && !publications.isEmpty()) {
			for (Publication publication: publications) {
				
				PublicationDto publicationDto =gtnControllerHelper.preparePublicationDto(publication);
				publicationDto .setAnnotation(isAnnotation);
				publicationDto.setUnlimitedAccess(isUnlimitedAccess);
				if(!isUnlimitedAccess && !isAnnotation)
				{
					boolean hasBeenIndividuallyPurchased=publicationService.isIndividuallyPurchased(publication.getId(),userService.getUserByEmail(email).getId());
					
					if(hasBeenIndividuallyPurchased)  {
						publicationDto.setHasBeenIndividuallyPurchased(true);
						publicationDto.setHasBeenMarkedForPurchase(false);
						
					}
					else {
						boolean hasBeenMarkedForPurchase=publicationService.isMarkedForPurchase(publication.getId(),userService.getUserByEmail(email).getId());
						if(hasBeenMarkedForPurchase)
						publicationDto.setHasBeenMarkedForPurchase(true);
						else
							publicationDto.setBuyNow(""+true);
					}
				}
				else
					publicationDto.setBuyNow(""+false);
					publicationDtos.add(publicationDto);
				
			}}
		System.out.println("size is "+publicationDtos.size());
		
		return publicationDtos;
			
	}
	@RequestMapping(value="/changeMark", method = RequestMethod.POST)
	public ResponseEntity<RestResponse> changeMark(@RequestBody MarkDto markInputDto) throws Exception {
if(markInputDto.getType().equalsIgnoreCase("note")) {		
Mark mark=publicationService.getMark(markInputDto.getId());
		mark.setNote(markInputDto.getNote());
		publicationService.saveMark(mark,"change");
}
		RestResponse success = new RestResponse(true,"Successful",markInputDto);
		 HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set ("SUCCESS_MESSAGE","Successful");
	   	ResponseEntity<RestResponse> result = null;

		result =new ResponseEntity<>(success,headers,HttpStatus.OK);

//		publicationService.saveMark(mark);
		
		return result;
		
		
	}
	@RequestMapping(value="/createMark",method = RequestMethod.POST)
	public ResponseEntity<RestResponse> createMark(@RequestBody MarkDto markInputDto) throws Exception {
		HashMap<String, String> response = new HashMap<String,String>();
		System.out.println("markInputDto "+markInputDto.getCollapsed());
		Mark mark = new Mark();
		mark.setAuthor(markInputDto.getAuthor());
		if(markInputDto.getCollapsed()!=null && markInputDto.getCollapsed().equals("true"))
		mark.setCollapsed(1);
		else 
			mark.setCollapsed(0);
		
			mark.setColor(markInputDto.getColor());
		mark.setDatechanged(markInputDto.getDatechanged());
		mark.setDatecreated(markInputDto.getDatecreated());
		mark.setDisplayFormat(markInputDto.getDisplayFormat());
		mark.setDocument_filename(markInputDto.getDocument_filename());
		mark.setDocument_relative_path(markInputDto.getDocument_relative_path());
		if(markInputDto.getHas_selection()!=null && markInputDto.getHas_selection().equals("true"))
		mark.setHas_selection( 1);
		else
		mark.setHas_selection(0);	
		if(markInputDto.getHeight()!=null)
		mark.setHeight(Integer.parseInt(markInputDto.getHeight()));
		mark.setId(markInputDto.getId());
		mark.setNote(markInputDto.getNote());
		mark.setPageIndex(Integer.parseInt(markInputDto.getPageIndex()));
		mark.setPoints(markInputDto.getPoints());
		float posx=0.0f;
		if(markInputDto.getPositionX()==null ) {
			posx=0.0f;
		} else
			posx=new Float(markInputDto.getPositionX()).floatValue();
		mark.setPositionX(posx);
		float posy=0.0f;
		if(markInputDto.getPositionY()==null ) {
			posy=0.0f;
		} else
			posy=new Float(markInputDto.getPositionY()).floatValue();
		mark.setPositionY(posy);
		if(markInputDto.getReadonly()!=null && markInputDto.getReadonly().equals("true"))
		mark.setReadonly(0);
		else
			mark.setReadonly(0);
		mark.setSelection_info(markInputDto.getSelection_info());
		mark.setSelection_text(markInputDto.getSelection_text());
		mark.setSelection_text(markInputDto.getSelection_text());
		if(markInputDto.getWidth()!=null)
		mark.setWidth(Integer.parseInt(markInputDto.getWidth()));
		mark.setType(markInputDto.getType());
		publicationService.saveMark(mark,"create");
		RestResponse success = new RestResponse(true,"Successful",markInputDto);
		 HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set ("SUCCESS_MESSAGE","Successful");
		response.put("Status", "Success");
	   	ResponseEntity<RestResponse> result = null;

		result =new ResponseEntity<>(success,headers,HttpStatus.OK);

//		publicationService.saveMark(mark);
		
		response.put("Status", "Success");
		return result;
	}
	@RequestMapping(value="/deleteMark",method = RequestMethod.POST)
	public ResponseEntity<RestResponse> deleteMark(@RequestBody MarkDto markDto) throws Exception {
	int deletedNos =publicationService.deleteMark(markDto.getId());
	HashMap<String, String> response = new HashMap<String,String>();
	 HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
   	ResponseEntity<RestResponse> result = null;
	RestResponse success = new RestResponse(true,"Successful",markDto);
    headers.set ("SUCCESS_MESSAGE","Successful");
	response.put("Status", "Success");
	result =new ResponseEntity<>(success,headers,HttpStatus.OK);

		return result;
			
	}


	@RequestMapping(value="/getAllMarks",method = RequestMethod.POST)
	public List<MarkDto> getAllMarks(@RequestBody MarkDto markInputDto) throws Exception {
	System.out.println("markDto is "+markInputDto);
	ArrayList<MarkDto> markDtos= new ArrayList<MarkDto>();
		Collection<Mark> marks= publicationService.getAllMarks(markInputDto);
		
		
		if (marks != null && !marks.isEmpty()) {
			for (Mark mark: marks) {
				
				MarkDto markDto =gtnControllerHelper.prepareMarkDto(mark);
				markDtos.add(markDto);
				
			}}
		System.out.println("size is "+markDtos.size());
		return markDtos;
			
	}
	
	@RequestMapping(value="/unmapPublication",method = RequestMethod.POST)
	public ResponseEntity<RestResponse> unmapPublication(@RequestBody PublicationDto publicationSearchDto) throws Exception {
		HashMap<String, String> response = new HashMap<String,String>();
System.out.println("publicationSearchDto.getId() "+publicationSearchDto.getId());
		boolean isPublicationUnmapped=publicationService.unmapPublication(publicationSearchDto.getId());
		 HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	   	ResponseEntity<RestResponse> result = null;
		RestResponse success = new RestResponse(true,"Successful",publicationSearchDto);
	    headers.set ("SUCCESS_MESSAGE","Successful");
		response.put("Status", "Success");
		result =new ResponseEntity<>(success,headers,HttpStatus.OK);

			return result;
	
	}
	@RequestMapping(value="/searchPublications",method = RequestMethod.POST)
	public List<PublicationDto> searchPublications(@RequestBody PublicationDto publicationSearchDto) throws Exception {
	ArrayList<PublicationDto> publicationDtos= new ArrayList<PublicationDto>();
		Collection<Publication> publications= publicationService.getPublicationsByDescription(publicationSearchDto.getDescription());
		List<Product> subscribedPublicationProducts=publicationService.getSubscribedPublicationProducts(publicationSearchDto.getAuthor());
		boolean isUnlimitedAccess=false;
		boolean isAnnotation=false;
		boolean buyNow=false;
		
		if (subscribedPublicationProducts != null && !subscribedPublicationProducts.isEmpty()) {
			for (Product  p1: subscribedPublicationProducts) {
				
				if(p1.getDisplayName().equals("Unlimited Access")){
					isUnlimitedAccess=true;
					
				}
				if(p1.getDisplayName().equals("Annotate Online")){
					isAnnotation=true;
				}
				
			}}
		
		
		
		if (publications != null && !publications.isEmpty()) {
			for (Publication publication: publications) {
				
				PublicationDto publicationDto =gtnControllerHelper.preparePublicationDto(publication);
				publicationDto .setAnnotation(isAnnotation);
				publicationDto.setUnlimitedAccess(isUnlimitedAccess);
				if(!isUnlimitedAccess && !isAnnotation) {
					boolean hasBeenIndividuallyPurchased=publicationService.isIndividuallyPurchased(publication.getId(),userService.getUserByEmail(publicationSearchDto.getAuthor()).getId());
					if(hasBeenIndividuallyPurchased) 
						publicationDto.setHasBeenIndividuallyPurchased(true);
					else
				
					publicationDto.setBuyNow(""+true);
				}
				else
					publicationDto.setBuyNow(""+false);
		
				publicationDtos.add(publicationDto);
				
			}}
		System.out.println("size is "+publicationDtos.size());
		return publicationDtos;
			
	}
	@RequestMapping(value="/getPdfViewerUrl",method = RequestMethod.GET)
	public Map<String, String>  getPdfViewerUrl() {
		System.out.println("in UI_flowpaper_desktop_flat.htmlz");
/*	ModelAndView mav = new ModelAndView();
		mav.addObject("pdfviewer", "pdfviewer");
		mav.addObject("url", "/UI_flowpaper_desktop_flat.html");
*/	String viewName="dashboard-index.html#!/pdftoolbar";
System.out.println("viewName "+viewName);
			//mav.setViewName("dashboard-index.html"+BaseEncoding.base64().decode("#")+BaseEncoding.base64().decode("!")+"/pdftoolbar");
//	mav.setViewName(viewName);
Map<String, String> response = new HashMap<String, String>();

response.put("ViewName",viewName);
	   
return response;   
	   }
	
	@RequestMapping(value = "/getPublicationCartInfo", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<PublicationCartDto>> getPublicationCartInfo(){
		ResponseEntity<RestResponse<PublicationCartDto>> response = null;
		
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = userDetail.getUser();
		BigDecimal totalCost = new BigDecimal(0.0);
		ArrayList<PublicationDto> publicationDtos= new ArrayList<PublicationDto>();
		try{			
			Collection<Publication> publications= publicationService.getPublicationsMarkedForPurchaseByUser(new Long(user.getId()).longValue());			
			
			if(publications.size() > 0){
				for(Publication pub:publications) {
					totalCost=totalCost.add(pub.getPrice());
					System.out.println("pub cost totalCost "+totalCost);
					PublicationDto publicationDto =gtnControllerHelper.preparePublicationDto(pub);
					publicationDtos.add(publicationDto);
					
				}
				PublicationCartDto obj = new PublicationCartDto();
				System.out.println("totalCost "+totalCost);
				
				if(totalCost!=null) {
					obj.setTotalCost(totalCost);
					obj.setTotalCount(publications.size());
				}
				else
					obj.setTotalCost(new BigDecimal(0.00));
				obj.setPublications(publicationDtos);
					
			//	obj.setNoOfUsers(itemCount);
				
				RestResponse<PublicationCartDto> result = new RestResponse<PublicationCartDto>(true, "Successful", obj);
				response = new ResponseEntity<RestResponse<PublicationCartDto>>(result, HttpStatus.OK);
				
				
			}else{
				PublicationCartDto publicationEmpty = new PublicationCartDto();
				publicationEmpty.setTotalCost(new BigDecimal(0.0));
				
				RestResponse<PublicationCartDto> result = new RestResponse<PublicationCartDto>(true, "Successfull", publicationEmpty);
				response = new ResponseEntity<RestResponse<PublicationCartDto>>(result, HttpStatus.OK);
				
			}
			
		}catch(ApplicationException e){
			/*RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(false, e.getMessage(), null);
			response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.INTERNAL_SERVER_ERROR);*/
			
			PublicationCartDto publicationEmpty = new PublicationCartDto();
			publicationEmpty.setTotalCost(new BigDecimal(0.0));
			
			RestResponse<PublicationCartDto> result = new RestResponse<PublicationCartDto>(true, "Successfull", publicationEmpty);
			response = new ResponseEntity<RestResponse<PublicationCartDto>>(result, HttpStatus.OK);
			e.printStackTrace();
		}
		
		return response;
	}
}

