package com.me.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logoutMethod( HttpServletRequest request,Model model) {
		
		HttpSession sess = request.getSession();
		sess.removeAttribute("patient");
		sess.removeAttribute("doctor");
		sess.removeAttribute("isLoggedIn");
		sess.invalidate();
		return "home";
	}

}
