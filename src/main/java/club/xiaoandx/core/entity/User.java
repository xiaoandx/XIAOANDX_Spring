package club.xiaoandx.core.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@ToString
@NoArgsConstructor
@ApiModel(value = "参与记录实体")
@Data
public class User implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(   )   
	 */ 
	private static final long serialVersionUID = -1191516383200426138L;

	@ApiModelProperty(value="用户Id")
	private Long user_id;
	
	@ApiModelProperty(value="open_id")
	private String  open_id;
	
	@ApiModelProperty(value="余额")
	private Double overage;
	
	@ApiModelProperty(value="昵称")
	private String nickname;
	
	@ApiModelProperty(value="头像")
	private String head_portrait;
}
