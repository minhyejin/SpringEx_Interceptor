package kr.co.mhj.interceptorex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//HandlerInterceptorAdapter 클래스 상속하기 
public class InterceptorA extends HandlerInterceptorAdapter {
	
	//클라이언트가 실제 요청한 url(컨트롤러)로 접근전에 실행됨
	@Override	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			System.out.println("doA 인터셉터 작동함. /doA에 접근 전 입니다.");
			
			String param = request.getParameter("msg");
			System.out.println("파라미터가 올바르지 않아 doB로 이동합니다.");
			if(param == null || param.equals("")) {
				response.sendRedirect("/doB");
				return false;
			}else {
				System.out.println("/doA로 이동할 때 전달 된 파라미터 :"+ param);
			}
		//원래 클라이언트가 요청한 곳으로 보내려면 true
		//로직에 따라 다른곳으로 보내려면 false 리턴 
		
		return true;
	}
	//서버가 클라이언트에 응답하고 나서 클라이언트에 도달 전에 실행됨 
	//ModelAndView--->서버가 클라이언트한테 전달하는 Attribute 모델과 이동하고자 하는 View의 정보를 한꺼번에 가지고있음.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
		System.out.println("doA 인터셉터 작동함. /doA가 응답 되었습니다.");
		//Model 을 꺼내기 위해서는 modelAndView객체를 사용함

		ModelMap modelMap = modelAndView.getModelMap();
		String infoValue = (String) modelMap.get("info");
		
		if(infoValue.equals("hello")) {
			System.out.println("/doA 응답중 info 값이 hello라서 doB로 이동합니다.");
			response.sendRedirect("/doB");
			
		}
	
	}
}
