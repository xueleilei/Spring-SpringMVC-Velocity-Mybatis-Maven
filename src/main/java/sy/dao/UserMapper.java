package sy.dao;



import java.util.List;


import sy.model.User;

public interface UserMapper {
	

	User selectByPrimaryKey(String id);
	
	User selectByName(String name);
	
	List<User> getAll();
	
	int insertUser(User user);
	
	int updateUser(User user);
	
	int deleteByPrimaryKey(User user);
	
}