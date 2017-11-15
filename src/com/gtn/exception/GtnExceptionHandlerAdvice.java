package com.gtn.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gtn.model.RestResponse;


@ControllerAdvice
public class GtnExceptionHandlerAdvice {
	 @ExceptionHandler(Exception.class)
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 @ResponseBody
	    public ResponseEntity<RestResponse>  handleException(HttpServletRequest req,Exception e) {
		 System.out.println("in exceprion handledsxxxxr");

	//sxz	 List<ObjectError> errors = exception.getBindingResult().getAllErrors();

		e.printStackTrace();
		 ResponseEntity<RestResponse> resp =null;
		 RestResponse error = new RestResponse(false,e.getMessage(),e);
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set ("ERROR_MESSAGE",e.getMessage());
	    	HashMap<String, String> response = new HashMap<String,String>();
			response.put("Status", e.getMessage());
			resp=new ResponseEntity<RestResponse>(error,headers,HttpStatus.INTERNAL_SERVER_ERROR);
			return resp;
			//	 return new ResponseEntity<>("Error while charging card"+e.getMessage(), new HttpHeaders(),HttpStatus.BAD_REQUEST);
		/*    return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Error Message"+e.getMessage());
	    */}

	/* @ExceptionHandler({InvalidFormatException.class, HttpMessageNotReadableException.class})
	public ResponseEntity<RestResponse> bindingResultHandler(HttpServletRequest req,MethodArgumentNotValidException e){
		ResponseEntity<RestResponse> response = null;

		System.out.println("hello");

		 List<ObjectError> errors = e.getBindingResult().getAllErrors();

		return response;
	}*/

}
