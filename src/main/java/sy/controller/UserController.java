package sy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.model.User;
import sy.service.UserServiceI;

@Controller
@RequestMapping("/userController")
public class UserController {

	private UserServiceI userService;

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	//测试显示
	@RequestMapping(value="/index")
	public String index(Model model) {
		String user = "我是用来测试显示的";
		model.addAttribute("user", user);
		
		return "showUser";
	}
		
		
	// 直接通过URL访问用户
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable String id, HttpServletRequest request) {
		User u = userService.getUserById(id);
		try {
			// System.out.println(u.getName());
			request.setAttribute("user", u.getName());
		} catch (Exception e) {
			request.setAttribute("user", "未能找到该用户");
		}
		return "showUser";
	}
	
	
	// 根据ID查询用户的名称
	@RequestMapping("/getId")
	@ResponseBody
	public String getId(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");

		User u = userService.getUserById(id);
		
		try {
			System.out.println(u.getName());
			
			return "你查找的用户名是："+u.getName()+"，密码是："+u.getPassword();
		} catch (Exception e) {
			return "你查找的用户不存在！";
		}
		
	}
	
	// 根据ID查询用户的名称
	@RequestMapping("/getName")
	@ResponseBody
	public String getName(HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("name");

		User u = userService.getUserByName(name);
		
		try {
			System.out.println(u.getName());
			
			return "你查找的用户名是："+u.getName()+"，密码是："+u.getPassword();
		} catch (Exception e) {
			return "你查找的用户不存在！";
		}
		
	}
	
	// 根据ID查询用户的名称
	@RequestMapping("/getUserName")
	public String getUserName(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");

		User u = userService.getUserById(id);
		try {
			System.out.println(u.getName());
			model.addAttribute("id", u.getId());
			model.addAttribute("name", u.getName());
			model.addAttribute("password", u.getPassword());
		} catch (Exception e) {
			
		}
		return "updateUser";
	}
	
	
	//显示所有用户
	@RequestMapping("/listUser")
	public String listUser(Model model) {
		List<User> list = userService.getAll();
		System.out.println(list); 
		try {
			// System.out.println(u.getName());
			model.addAttribute("list", list);
		} catch (Exception e) {
			model.addAttribute("list", "未能找到该用户");
		}
		return "listUser";
	}



	// 添加一个用户
	@RequestMapping("/insertUser")
	@ResponseBody
	public String insertUser(HttpServletRequest request, Model model, User user) {


		try {
			userService.insertUser(user);
			//model.addAttribute("user", "您添加的用户是：" + name + "，密码是：" + password);
			return "{\"success\":true,\"msg\":\"添加成功！\",\"id\":\""+user.getId()+"\",\"name\":\""+user.getName()+"\",\"password\":\""+user.getPassword()+"\"}";
		} catch (Exception e) {
			//model.addAttribute("user", "添加失败");
			return "{\"success\":false,\"msg\":\"添加失败！\"}";
		}

	}
	
	// 更新一个用户
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request, Model model, User user) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		System.out.println("userId is:"+id);
		System.out.println("userName is:"+name);  
        System.out.println("password is:"+password);  
		
		try {
			userService.updateUser(user);
			
			model.addAttribute("user", "更新成功，您更改的用户是：" + name + "，密码是：" + password);
		} catch (Exception e) {
			model.addAttribute("user", "更新失败");
		}

		return "showUser";
	}
	
	

	// 删除一个用户
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(User user) {
		//String id = request.getParameter("id");

		try {
			userService.deleteUser(user);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}

	}

}
