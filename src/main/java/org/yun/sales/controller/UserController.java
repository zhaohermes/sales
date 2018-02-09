package org.yun.sales.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yun.sales.vo.JsonResult;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "status", method = RequestMethod.POST)
	public JsonResult userStatus() {
		return null;
	}
	
}
