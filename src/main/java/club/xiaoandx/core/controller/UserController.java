  package club.xiaoandx.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.xiaoandx.commons.core.Parameter;
import club.xiaoandx.commons.core.PublicErrorCode;
import club.xiaoandx.commons.exception.CommonException;
import club.xiaoandx.commons.utils.RedisUtil;
import club.xiaoandx.conf.redis.RedisHelperImpl;
import club.xiaoandx.core.entity.Deal;
import club.xiaoandx.core.entity.User;
import club.xiaoandx.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "任务相关Api")
@RequestMapping("/v1/open/task")
public class UserController implements Parameter {

	@Autowired
	private UserService userService;
//	@Autowired 失败
//	private RedisUtil redisUtil;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisHelperImpl redisHelper;
	

	/**
	 *<p>查询XXX用户的总金额</p> 
	 * @Title: findUserById    
	 * @version:V0.1     
	 * @param user_id	用户id
	 * @return:User		返回的对象
	 */
	//	@Cacheable(value="userM")
	@GetMapping(value = "/findUserById/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "查询XXX用户的总金额", notes = "前端传入用户id，返回一个user对象（集合包含总金额+userId）<b>@autho xiaox.周巍</b>")
	public Object findUserById(@ApiParam(value = "用户id*必填",required = true) @PathVariable("user_id") int user_id) {
	//添加缓存
//		if(ENTER_NUMBER != user_id) {
//			User user = userService.findUserById(user_id);
//			Long n = redisHelper.listPush("1",user);
//			if(0 != n) {
//				return user;
//			}
//			throw new CommonException(PublicErrorCode.PARAM_EXCEPTION.getIntValue(), "Input parameter error");
//		}
//		throw new CommonException(PublicErrorCode.PARAM_EXCEPTION.getIntValue(), "Input parameter error");
	
	/**
	 * 获取
	 */
//		List<User> list = redisHelper.listFindAll("xiaox");
//		return list.get(0);
		
		//
		return null;
	}
	

	/**
	 * 
 	 *<p>查询某个用户所有交易记录</p> 
	 * @Title: get    
	 * @version:V1.0     
	 * @param userId		用户Id
	 * @return:List<Deal>	
	 */
	@GetMapping(value = "/findDeal/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET",value = "查询用户交易记录",notes = "<br><br><b>@author xiaox.金彩</b>")
	@ApiResponses({
		@ApiResponse(code = 400,message = "未传入指定参数"),
		@ApiResponse(code = 404,message = "未找到指定页面")
	})
	public List<Deal> findDealByUserId(@ApiParam(value = "用户id*必填",required = true) @PathVariable("user_id") Integer user_id) {
		if(ENTER_NUMBER != user_id && null != user_id) {
			return userService.findDealByUserId(user_id);
		}
		throw new CommonException(PublicErrorCode.PARAM_EXCEPTION.getIntValue(), "Input parameter error");
	}
}
