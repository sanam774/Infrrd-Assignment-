package com.example.sanampreet;
import java.util.Set;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataProcessingResponseDTO {
	
   @ApiModelProperty(name = "error", value = "error", required = false, dataType = "String", example = "xxxx")
	private String error;
    
   @ApiModelProperty(name = "data", value = "data", required = false, dataType = "String", example = "xxxx")
	private String data;
    
   @ApiModelProperty(name = "dataSet", value = "dataSet", required = false, dataType = "Set", example ="123,456,789")
	private Set<Integer> dataSet;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Set<Integer> getDataSet() {
		return dataSet;
	}

	public void setDataSet(Set<Integer> dataSet) {
		this.dataSet = dataSet;
	}
	
	
	
	
	

}

