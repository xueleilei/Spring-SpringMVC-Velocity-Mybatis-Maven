package sy.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.UserMapper;
import sy.model.User;


//实现用户的接口类
@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	//根据ID获取一个用户的姓名 [实现方法]
	public User getUserById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	//获取所有用户列表 [实现方法]
    public List<User> getAll() {
        return userMapper.getAll();
    }
    
    //添加一个用户 [实现方法]
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}
	
	//更新一个用户 [实现方法]
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	//删除一个用户 [实现方法]
	public int deleteUser(User user) {
		return userMapper.deleteByPrimaryKey(user);
	}
	
	

	
}
