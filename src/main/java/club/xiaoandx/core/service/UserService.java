package club.xiaoandx.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import club.xiaoandx.commons.core.DaoCode;
import club.xiaoandx.commons.core.Parameter;
import club.xiaoandx.core.dao.UserDao;
import club.xiaoandx.core.entity.Deal;
import club.xiaoandx.core.entity.User;

@Component
@Transactional
public class UserService implements DaoCode, Parameter {

	@Autowired
	private UserDao userDao;
	
	/**
	 * <p>
	 * 查询XXX用户的总金额
	 * </p>
	 * 
	 * @Title: findUserById
	 * @version:V0.1
	 * @param user_id 用户id
	 * @return:User 返回的对象
	 */
	@Transactional(readOnly = true)
	public User findUserById(int user_id) {
		return userDao.findUserById(user_id);
	}
	
	/**
	 * 
	 * <p>查询某个用户距离当前现在6个月交易记录 </p>
	 * @param userId 用户ID
	 * @return List<Deal> 交易记录实体
	 */
	@Transactional(readOnly = true)
	public List<Deal> findDealByUserId(Integer userId) {
		return userDao.findDealByUserId(userId);
	}
	
	
}
