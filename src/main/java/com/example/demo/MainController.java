package com.example.demo;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/test1")
public class MainController {
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("index")
	public String index(Model model) {
		List<Users> list = usersRepository.getAll();
		model.addAttribute("UsersList", list);
		return "test1/index";
	}
	
	@GetMapping("input")
	public String input1() {
		return "test1/input";
	}
	
	@GetMapping("inputconfirm")
	public String output1(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "department") String department,
			Model model) {
			model.addAttribute("name", name);
			model.addAttribute("department", department);
			return "test1/input_confirm";
	}
	
	@GetMapping("inputcomplete")
	public String output2(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "department") String department,
			Model model) {
		    Users users = new Users();
		    users.setName(name);
		    users.setDepartment(department);
		    usersRepository.insertUsers(users);
		    
			model.addAttribute("name", name);
			model.addAttribute("department", department);
			return "test1/input_complete";
	}
	
	@GetMapping("edit/{userId}")
	public String editForm(@PathVariable("userId") long userId, Model model) {
		Users user = usersRepository.selectOne(userId);
		model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("department", user.getDepartment());
        return "test1/edit";
	}
	
	@GetMapping("editconfirm")
	public String editconfirm(
			@RequestParam(name = "id") Integer id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "department") String department,
			Model model) {
			model.addAttribute("id", id);
			model.addAttribute("name", name);
			model.addAttribute("department", department);
			return "test1/edit_confirm";
	}
	
	@GetMapping("editcomplete")
	public String editcomplete(
			@RequestParam(name = "id") Integer id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "department") String department,
			Model model) {
		    Users users = new Users();
		    users.setId(id);
		    users.setName(name);
		    users.setDepartment(department);
		    usersRepository.update(users);
		    
			model.addAttribute("name", name);
			model.addAttribute("department", department);
			return "test1/edit_complete";
	}
	
	@GetMapping("delete/{userId}")
	public String deleteUser(@PathVariable("userId") long userId, Model model) {
		Users user = usersRepository.delete(userId);
        model.addAttribute("name", user.getName());
        model.addAttribute("department", user.getDepartment());
        return "test1/delete_complete";
	}
	
}