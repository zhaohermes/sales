package org.yun.sales.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yun.sales.enums.ResultCode;
import org.yun.sales.vo.JsonResult;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "status", method = RequestMethod.POST)
	public JsonResult loginStatus() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String acc = authentication.getName();
		JsonResult result = new JsonResult(ResultCode.SUCCESS, (Object)acc);
		return result;
	}
	
}
