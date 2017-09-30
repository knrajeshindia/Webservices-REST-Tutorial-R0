package com.ws.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
/*ExceptionMapper intercepts exception message for the registered provider type (here DataNotFoundException) before it 
	reaches servlet container and returns custom error messages.*/
	
	@Override
	public Response toResponse(DataNotFoundException ex) {
		System.out.println("Exception message: "+ex);
		ErrorCode errorMessage=new ErrorCode(ex.getMessage());
		return Response.status(Status.NOT_FOUND).entity(errorMessage.getErrorMessage()).build();
	}

}
