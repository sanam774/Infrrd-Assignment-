package com.example.sanampreet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataProcessingService {
	
	//map to store the  unique requestIds 
    private  Map<Integer , Boolean >  dataPresentCheckerMap  = new HashMap<>();
    // Set to store the request ids fully processed (started and stopped)
    private  Set<Integer>  processedRequestids = new HashSet<>();
    

	//This service class start api  method does 2 things
    //1- Checks if the request is not under processing already by other thread  and then process the request/data
    // 2- else it throws an exception with custom message and response code
    public void  startApiService( DataProcessingRequestModel requestModel) 
    {
    	//Logger.debug("entering  startApiService method of data processing Service");
    	if(checkIfRequestNotUnderprocessing(requestModel.getUuid()))
    	{
    		///Logger.debug("Validation passed  for data not already being proceesed andData processing starts");
    		 processRequest(requestModel);   		  		
    	}
    	else
    	{
    		//Logger.debug(" invalid request as  data already being proceesed by other thread" + requestModel.getUuid() );

    		String message = "request Invalid for" + requestModel.getUuid() + "already underprocess";

    		 throw new DataProcessingExceptionHandler(   message);
  		
    	}
 	
    	//Log.debug("exiting startApiService method of data processing Service");
	
    }
    
    
    
    
  //This service class stop api  method does 2 things
    //1- Checks if the request is not terminated already by other thread  and then terminates the request
    // 2- else it throws an exception with custom message and response code
    public String  stopApiService(Integer requestId)
    {
    	//log.debug("entering  stopApiService method of data processing Service");
	
    	if(checkIfRequestNotAlreadyStopped(requestId))
    	{	
   		String message = "processing of request with "+ requestId + "stopped";
   		
   		//log.debug(message);  
   		
   	return  message;
   	
    	}
    	else
    	{
    		String message = "processing of request with "+ requestId + "alreadyDisruptedByOtherThread";
    		
        	//log.info(message);
        	
        	 throw new  DataProcessingExceptionHandler(message);
    	}	
    	
    }
    
    
 
    
    
    // This private method is used by start service Method to
//Checks if the request is not under processing already by other thread using the HashMap and double locking
    private Boolean checkIfRequestNotUnderprocessing(  Integer 
    		UIID )
    {
    
    	if(dataPresentCheckerMap.get(UIID) == null)
    	{
    		synchronized(this)
    		{
    			if(dataPresentCheckerMap.get(UIID) == null)
    			{
    				dataPresentCheckerMap.put(UIID , true);
    				return true;
    			}
    			
    			
    		}
    		
    		
    	}
    	
    	return false;
    	
    	
    	
    }
    
    
    
    
    // This private method is used by stop service Method to
//Checks if the request is not  already terminated by other thread using the HashMap and double locking
    private Boolean checkIfRequestNotAlreadyStopped(  Integer 
    		UIID )
    {
    
    	if(dataPresentCheckerMap.get(UIID) != null)
    	{
    		synchronized(this)
    		{
    			if(dataPresentCheckerMap.get(UIID) != null)
    			{
    				dataPresentCheckerMap.remove(UIID);
    				return true;
    			}
    			
    			
    		}
    		
    		
    	}
    	
    	return false;
      	
    }
    
    

    
    // This private method is used by start service Method to
// process the given data/request	
	private void  processRequest(DataProcessingRequestModel requestModel)
	{
		Integer UIID = requestModel.getUuid();
		while(dataPresentCheckerMap.get(UIID) !=null)
		{	
			
		//log.info("Thread processing data with"  + UIID);
        // log.info(requestModel.getData());		
		}
		
		processedRequestids.add(UIID);
		//log.info("Thread processing data with"  + UIID + "completes");
		
	}
	
	
	
	
	
	//This service class getAllProcessedMessageIds returns requestUUIDS fully processed
    public Set<Integer> getAllProcessedMessageIds()
    {
    	return  processedRequestids;
    }
    
    
    
    
	
	
	
	
	
	
	
}
