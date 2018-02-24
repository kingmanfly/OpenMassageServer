package com.massage.infosys.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.massage.infosys.dao.AuthUserMapper;
import com.massage.infosys.po.AuthUser;

@Component
public class OperationInterceptor implements HandlerInterceptor {
	
	@Autowired
	AuthUserMapper authUserMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(">>>OperationInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
		HttpSession session = request.getSession(true);
		// 判断用户ID是否存在，不存在就跳转到登录界面
		String username = (String) session.getAttribute("userId");
		if (username == null) {
			System.out.println("------:跳转到login页面！");
			System.out.println(request.getContextPath() + "/login");
			response.sendRedirect("/admin/login");
			return false;
		} else {
			AuthUser user = authUserMapper.findByUserName(username);
			if(user.getRole() == 1) {
				return true;
			}else {
				System.out.println("------:跳转到没有权限页面！");
				response.sendRedirect("/admin/no_permission");
				return false;
			}
			
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(">>>OperationInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(">>>OperationInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");

	}

}
