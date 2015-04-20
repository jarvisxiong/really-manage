package com.wuxincheng.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wuxincheng.manage.model.Admins;

/**
 * 登录Session拦截
 * 
 * @author wuxincheng
 *
 */
public class LoginSessionInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(LoginSessionInterceptor.class);
	
	private String mappingURL; // 利用正则映射到需要拦截的路径

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, 
			Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURL().toString();
		
		if (mappingURL == null || url.matches(mappingURL)) {
			Admins admins = (Admins)request.getSession().getAttribute("admins");
			
			if (null == admins) {
				logger.info("后台管理员Admins登录Session失效，跳转到登录页面");
				// request.getRequestDispatcher("/admins/login").forward(request, response); // 转发
				// response.sendRedirect("/index.jsp");  // 重定向
				response.sendRedirect("/really-manage/admins/login");  // 重定向
				return false;   
			}
			
			String operURL = url.substring(url.indexOf("/manage"), url.length());
			
			logger.info("后台管理员[" + admins.getAdminsLogin() + "]执行[" + operURL + "] 操作");
        }
		
		return true;
	}
	
	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}
	
}
