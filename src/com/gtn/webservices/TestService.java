package com.gtn.webservices;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gtn.dto.UserDto;

class aa implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}

interface a{}

class Super{}
class Su extends Super{
	
}

class d{
	public void a(){
		
	}
}

class f extends d{
	@Override
	public void a(){
		
	}
}

@Path("/testservice")
public class TestService {
	
	Super x;
	Su y;
	
	public void main1(String[] args){
		y = (Su)x;
	}
	
	long var;
	
	public TestService(long param){
		var = param;
	}
	
	public boolean equals(Object a){
		return this == a;
	}

	@PermitAll
	@POST
	@Path("/getUser")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public UserDto getUser(@Context HttpServletRequest req){
		UserDto user = new UserDto();
		user.setCompany("ARG Infratach pvt ltd");
		user.setEmail("naveen.ara@gmail.com");
		user.setFirstName("naveen");
		//user.setId(userReq.getId());
		user.setLastName("kumar");
		user.setOtp("24324");
		user.setPassword("moooooooo");
		
		//user.setTitle(userReq.getTitle());
		
		System.out.println("User ---------------- > "+user.toString());
		
		return user;
	}
	
	
	@PermitAll
	@GET
	@Path("/test")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public String test(@Context HttpServletRequest req){
		String param = req.getParameter("param");
		return "Hello "+param;
	}
	
}
