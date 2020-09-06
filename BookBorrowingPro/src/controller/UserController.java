package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Admin;
import domain.Student;
@Controller
public class UserController {
	private static List<Student> stuList;
	private static List<Admin> adminList;
	private static long stuCounter=0;
	private static long adminCounter=0; 
	static {
		stuList=new ArrayList<Student>();
		stuList.add(new Student(1,"zhang3","123"));
		stuList.add(new Student(2,"li4","123"));
		stuList.add(new Student(3,"wang5","123"));
		stuCounter+=3;
		adminList=new ArrayList<Admin>();
		adminList.add(new Admin(1,"root","123"));
		adminCounter+=1;
	}
	public static Student findStudent(long stuId) {
		for(Student s:stuList) {
			if(s.getId()==stuId)
				return s;
		}
		return null;
	}
	@RequestMapping(value="/test")
	public String test(Model m) {
		m.addAttribute("name","zhang3");
		return "test";
	}
	@RequestMapping(value="/{name}")
	public String commonHandler(@PathVariable String name) {
		return name;
	}
	@RequestMapping(value="/stu_list")
	public String doListStu(Model m) {
		m.addAttribute("all_stus",stuList);
		return "allStudentsForm";
	}
	@RequestMapping(value="/register")
	public String doRegister(@RequestParam String loginname,
			@RequestParam String password,Model m) {
		stuCounter+=1;
		Student s=new Student(stuCounter,loginname,password);
		stuList.add(s);
		return "redirect:stu_list";
	}
	
	@RequestMapping(value="/login")
	public String doLogin(@RequestParam String loginname,
			@RequestParam String password,
			@RequestParam String user,Model m,
			HttpSession session) {
		if(user.equals("student")) {
			for(Student s:stuList) {
				if(loginname.equals(s.getName()) && password.equals(s.getPassword())) {
					session.setAttribute("user", s);
					m.addAttribute("error",null);
	
					//return "stuInfoForm";
					return "redirect:book_list";
				}
			}
			//session.setAttribute("error","错误的用户名或密码");
			m.addAttribute("error","错误的用户名或密码");
			return "forward:loginForm";
		}else if(user.equals("admin")){
			for(Admin s:adminList) {
				if(loginname.equals(s.getName()) && password.equals(s.getPassword())) {
					session.setAttribute("user", s);
					m.addAttribute("error",null);	
					return "redirect:manage_book";
				}
			}
			//session.setAttribute("error","错误的用户名或密码");
			m.addAttribute("error","错误的用户名或密码");
			return "forward:loginForm";		
		}else {
			m.addAttribute("error","不存在的用户类型");
			return "forward:loginForm";	

		}
	}
}