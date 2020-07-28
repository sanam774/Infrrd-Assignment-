package com.example.sanampreet;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataProcessingRequestModel {
	
   public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	 @ApiModelProperty(name = "uuid", value = "uuid", required = true, dataType = "Integer")
	private Integer uuid;
	
    @ApiModelProperty(name = "data", value = "data", required = true, dataType = "String", example = "Infrrd")
	private String data;

}
