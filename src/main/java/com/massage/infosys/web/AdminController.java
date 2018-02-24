package com.massage.infosys.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.massage.infosys.dto.SkillerDetailPojo;
import com.massage.infosys.dto.SkillerPojo;
import com.massage.infosys.po.AuthUser;
import com.massage.infosys.po.User;
import com.massage.infosys.service.AuthUserService;
import com.massage.infosys.service.ManageSkillerService;
import com.massage.infosys.service.SkillerService;
import com.massage.infosys.service.UserService;
import com.massage.infosys.util.MD5Util;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private String nick;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SkillerService skillerService;
	
	@Autowired
	private ManageSkillerService manageSkillerService;
	
	@Autowired
	private AuthUserService authUserService;
	
	@GetMapping("/home")
	public ModelAndView	home(HttpServletResponse response,
			HttpServletRequest request,
			Map<String,Object> map){
		System.out.println("home");
		HttpSession session = request.getSession(true);
		String userName = (String) session.getAttribute("userId");
		map.put("nickname", nick);
		return new ModelAndView("home");
	}
	
	@GetMapping("/userlist")
	public ModelAndView	userList(@RequestParam(value = "pageNo", defaultValue = "1")Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
			Map<String,Object> map){
		System.out.println("userlist");
		List<User> users = userService.findAll(pageNo, pageSize);
		map.put("users", users);
		int usersCount = userService.findAllTotalPagesCount(pageSize);
		map.put("currentPage", pageNo);
		map.put("pageSize", pageSize);
		map.put("usersCount", usersCount);
		map.put("nickname", nick);
		return new ModelAndView("userlist", map);
	}
	
	/**
	 * @param page 第几页，从第一页开始
	 * @param size
	 * @return
	 */
	@GetMapping("/skillerlist")
	public ModelAndView	skillerList(@RequestParam(value = "pageNo", defaultValue = "1")Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
							 Map<String,Object> map){
		System.out.println("list dsdf");
		map.put("keyy", "ddsdf");
		List<SkillerPojo> SkillerPojos = null;
		SkillerPojos = skillerService.findAll(pageNo, pageSize);
		int skillersCount = skillerService.findAllTotalPagesCount(pageSize);
		map.put("skillers", SkillerPojos);
		map.put("currentPage", pageNo);
		map.put("pageSize", pageSize);
		map.put("skillersCount", skillersCount);
		map.put("nickname", nick);
		return new ModelAndView("skillers", map);
	}
	
	@GetMapping("/skillerdetail")
	public ModelAndView	skillerDetail(Long id,
							 Map<String, Object> map){
		System.out.println("skillerdetail");
		SkillerDetailPojo skillerdetail = null;
		skillerdetail = this.skillerService.findSkillerDetailById(id);
		map.put("detail", skillerdetail);
		map.put("nickname", nick);
		return new ModelAndView("skillerdetail", map);
	}
	
	@GetMapping("/auditing-pass")
	public ModelAndView auditingPass(String id,
			Map<String, Object> map){
		System.out.println("auditingPass");
		int result = manageSkillerService.auditingPass(id);
		if(result == 1){
			map.put("msg", "正确");
			map.put("url", "/admin/skillerlist");
			return new ModelAndView("common/success", map);
		}else{
			map.put("msg", "错误了");
			map.put("url", "/admin/skillerlist");
			return new ModelAndView("common/error", map);
		}
	}
	
	@GetMapping("/auditing-reject")
	public ModelAndView auditingReject(String id,
			Map<String, Object> map){
		System.out.println("auditingReject");
		int result = manageSkillerService.auditingReject(id);
		if(result == 1){
			map.put("msg", "正确");
			map.put("url", "/admin/skillerlist");
			return new ModelAndView("common/success", map);
		}else{
			map.put("msg", "错误了");
			map.put("url", "/admin/skillerlist");
			return new ModelAndView("common/error", map);
		}
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/check_login")
	@ResponseBody
	public boolean checkLogin(String userName,
			String password,
			HttpServletResponse response,
			HttpServletRequest request) {
		System.out.println("username = " + userName + "," + password);
		List<AuthUser> authUsers = authUserService.findAll();
		boolean isAuthUser = false;
		for(AuthUser user : authUsers) {
			if(userName.equals(user.getUsername()) &&
					MD5Util.encode(password, "").equals(user.getPassword())){
				isAuthUser = true;
			}
		}
		
		if(isAuthUser) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userName);
			nick = userName;
			try {
				response.sendRedirect("/admin/home");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else {
			try {
				response.sendRedirect("/admin/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public ModelAndView logout(HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", null);
		try {
			response.sendRedirect("/admin/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("login");
	}
	
	@GetMapping("/no_permission")
	public ModelAndView HaveNoPermission() {
		return new ModelAndView("common/no_permission");
	}
}
