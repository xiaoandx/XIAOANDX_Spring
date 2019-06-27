package club.xiaoandx.core.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@ToString
@NoArgsConstructor
@ApiModel(value = "参与记录实体")
@Data
public class Deal {

	@ApiModelProperty(value="交易Id")
	private Long deal_id;
	
	@ApiModelProperty(value="用户Id")
	private Long user_id;
	
	@ApiModelProperty(value="交易内容")
	private String content;
	
	@ApiModelProperty(value="交易金额")
	private Double sum;
	
	@ApiModelProperty(value="交易Id")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date time;

	public Deal(Long deal_id, Long user_id, String content, Double sum, Date time) {
		this.deal_id = deal_id;
		this.user_id = user_id;
		this.content = content;
		this.sum = sum;
		this.time = time;
	}
	
}
