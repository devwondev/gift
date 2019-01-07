package com.gift.futurestrading.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.futurestrading.login.service.LoginService;
import com.gift.futurestrading.login.vo.LoginVo;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	/** 로그인폼
	 * 로그인폼으로 랜더링해주는 메서드
	 * @param HttpSession session
	 * @return loginReturn
	 * @since JDK1.8
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin(HttpSession session) {
		System.out.println("LoginController.getLogin() 호출 GET");
		String loginReturn = null;
		if(session.getAttribute("sessionLoginMember")!=null) {
			loginReturn = "redirect:/";
		}else {
			loginReturn = "login/login";
		}
		return loginReturn;
	}
	/** 로그인 액션
	 * 로그인처리를 해주는 메서드
	 * @param LoginVo loginVo,HttpSession session
	 * @return loginReturn
	 * @since JDK1.8
	 */
	@RequestMapping(value="/login/re", method=RequestMethod.POST)
	public String getLogin(LoginVo loginVo,HttpSession session) {
		System.out.println("LoginController.getLogin() 호출 POST");
		String loginReturn = null;
		if(loginService.getLogin(loginVo)!=null) {
			System.out.println("login success!!");
			LoginVo sessionLoginMember = loginService.getLogin(loginVo);
			System.out.println(sessionLoginMember+"<--sessionLoginMember");
			session.setAttribute("sessionLoginMember", sessionLoginMember);
			session.setAttribute("sessionLoginId", sessionLoginMember.getSessionId());
			session.setAttribute("sessionLoginRight", sessionLoginMember.getSessionRight());
			loginReturn = "redirect:/";
		}else {
			System.out.println("login fail!!");
			loginReturn = "login/login";
		}
		return loginReturn;
	}
	/** 로그아웃
	 * 로그아웃처리해주는 메서드
	 * @param HttpSession session
	 * @return redirect:/
	 * @since JDK1.8
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		System.out.println("LoginController.logout() 호출 GET");
		session.invalidate();
		return "redirect:/";
	}
	/** 아이디, 비밀번호 일치하는지 체크
	 * 로그인 시 아이디와 비밀번호가 일치하는지 체크하기 위한 메서드(ajax)
	 * @param 
	 * @return 
	 * @since JDK1.8
	 */
	@RequestMapping(value="/get/id/password/check", method=RequestMethod.POST)	
	@ResponseBody
	public int getIdPasswordCheck(@RequestBody HashMap<String, Object> idAndPassword) {
		System.out.println("LoginController.getIdPasswordCheck() 호출");
		System.out.println(idAndPassword.get("sessionId") + "<-- sessionId(ajax)");
		System.out.println(idAndPassword.get("sessionPassword") + "<-- sessionPassword(ajax)");
		int count = loginService.getIdPasswordCheck(idAndPassword);
		System.out.println(count+"<--count");
		return count;
	}
}
