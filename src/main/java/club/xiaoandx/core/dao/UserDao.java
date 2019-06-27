package club.xiaoandx.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import club.xiaoandx.commons.core.PublicErrorCode;
import club.xiaoandx.commons.exception.CommonException;
import club.xiaoandx.core.entity.Deal;
import club.xiaoandx.core.entity.User;

@Component
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	/**
	 *<p>查询XXX用户的总金额</p> 
	 * @Title: findUserById    
	 * @version:V0.1     
	 * @param user_id	用户id
	 * @return:User		返回的对象
	 */
	public User findUserById(int user_id) {
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		String sql = "SELECT u.`user_id`,u.`overage` "
				+ "FROM `user` AS u "
				+ "WHERE u.`user_id` = ?";
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, user_id);
		} catch (DataAccessException e) {
			throw new CommonException(PublicErrorCode.QUERY_EXCEPTION.getIntValue(), "user_id Non-existent - select userTable overage error");
		}
	}

	/**  
	 * 
	 *<p>根据用户userId查询某个用户所有交易记录</p> 
	 * @Title: findDealByUserID    
	 * @version:V0.1     
	 * @param userId
	 * @return:List<Deal>
	 */
	public List<Deal> findDealByUserId(Integer userId) {
		RowMapper<Deal> rowMapper = new BeanPropertyRowMapper<Deal>(Deal.class);
		String optionSql = "select `deal_id`,`user_id`,`content`,`sum`,`time` from deal WHERE user_id = ? ORDER BY time DESC";
		return jdbcTemplate.query(optionSql, rowMapper,userId);
	}
}
