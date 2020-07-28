package com.example.sanampreet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataProcessingExceptionHandler  extends  RuntimeException{
	
    static final long serialVersionUID = -7034897190745766939L;

     
     public DataProcessingExceptionHandler(String message)
     {
    	 super(message);
     }


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    

}
