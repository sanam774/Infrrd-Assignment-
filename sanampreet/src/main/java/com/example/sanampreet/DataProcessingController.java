package com.example.sanampreet;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

//@Api
@Slf4j
@RestController
@RequestMapping("/sanam")
public class DataProcessingController {
	
    
    @Autowired
    DataProcessingService  dataprocessingService;
    
    
   //This is Api for starting data Processing which takes requestModel (RequestUniqueId , data)  as the request Object
    @ApiOperation(value = "This method will start processing of data if its not already processed otherwise it sends a custom message sayin that Data  is already in process ")
    @ApiResponses(value = {
       @io.swagger.annotations.ApiResponse(code = 200, message = "Operation to start processing is successful "),
       @io.swagger.annotations.ApiResponse(code = 400, message = "Already in process by another thread")})
    @PostMapping(value = "/startDataprocessing" )
    public  DataProcessingResponseDTO  startApi(@RequestBody DataProcessingRequestModel requestModel)
    {
    	
    	//Logger.info("Entering start Api mehod of data processing controller");
    	DataProcessingResponseDTO response = new DataProcessingResponseDTO ();
    	 
    	try
    	{
    	dataprocessingService.startApiService(requestModel);
		//Logger.info("Data processing started successfully"  + "for"+ " "+ requestModel.getUuid());
		response.setData("Data processing" +  requestModel.getUuid() + "started");
		

    	}
    	catch(DataProcessingExceptionHandler ex)
    	{
    		
    		//Logger.info("exception occured while starting data processing"+" "+requestModel.getUuid()+ "  "+ "Data already in process by other thread");
    		response.setError(ex.getMessage());
    		response.setData(ex.getMessage());
    		
    	} 
    	
    	//Logger.info("existing start Api method of data processing controller");

    	return response;
    	
    }
    
    
    //This is Api for Ending data Processing which takes RequestUniqueId  as the request paramter
    @ApiOperation(value = "This method will end processing of data if its not already ended otherwise it sends a custom message sayig that request has already been terminated ")
    @ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operation to end processing is successful "),
        @io.swagger.annotations.ApiResponse(code = 400, message = "Already terminated  by another thread")})
    @GetMapping(value = "/stopDataprocessing")
    public  DataProcessingResponseDTO  EndApi(@RequestParam Integer UUID)
    {
    	//log.debug("entering End Api method of data processing controller");

    	
    	DataProcessingResponseDTO response = new DataProcessingResponseDTO ();
    	 
    	try
    	{
   String message = 	dataprocessingService.stopApiService(UUID);
	//Logger.info(" Successfully stopped  data processing for the "+" "+UUID);

		response.setData(message);
		

    	}
    	catch( DataProcessingExceptionHandler ex)
    	{
    		
    		//log.info("exception occured while stopping data processing"+" "+UUID+ "  "+ "processing already terminated by other thread");
    		response.setError(ex.getMessage());
    		response.setData(ex.getMessage());
    		
    	} 	
    	
    	//log.debug("exiting end  Api method of data processing controller");

    	return response;
    	
    }
    
    
    
    
    @ApiOperation(value = "This method will return set of all  request Ids  completely processed ")
    @ApiResponses(value = 
       @io.swagger.annotations.ApiResponse(code = 200, message = "Operation to get all   requestids processed  is successful "))
    @GetMapping(value ="/getProceesedids")
    public DataProcessingResponseDTO  getAllProcessedIds()
    {
   //log.debug("entering getAllProcessedIds method of data processing controller");

   DataProcessingResponseDTO  response = new  DataProcessingResponseDTO();

    	response.setDataSet(dataprocessingService.getAllProcessedMessageIds());
    	
    //log.debug("exiting getAllProcessedIds method of data processing controller");

    	return response;
    	
    }
    
	

}
