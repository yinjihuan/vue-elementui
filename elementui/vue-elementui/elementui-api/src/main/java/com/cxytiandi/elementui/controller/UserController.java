package com.cxytiandi.elementui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxytiandi.elementui.base.ResponseCode;
import com.cxytiandi.elementui.base.ResponseData;
import com.cxytiandi.elementui.utils.JWTUtils;
import com.cxytiandi.elementui.vo.MenuVo;
import com.cxytiandi.elementui.vo.UserVo;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/login")
	public ResponseData login(String username, String password) {
		if (!StringUtils.hasText(username)) {
			return ResponseData.fail("用户名不能为空", ResponseCode.PARAM_ERROR_CODE.getCode());
		}
		if (!StringUtils.hasText(password)) {
			return ResponseData.fail("密码不能为空", ResponseCode.PARAM_ERROR_CODE.getCode());
		}
		if (username.equals("admin") && password.equals("admin")) {
			List<MenuVo> meuns = new ArrayList<>();
			MenuVo m = new MenuVo();
			m.setIcon("el-icon-setting");
			m.setIndex("/home");
			m.setTitle("首页");
			meuns.add(m);
			
			MenuVo m3 = new MenuVo();
			m3.setIndex("/users");
			m3.setTitle("用户列表");
			
			MenuVo m2 = new MenuVo();
			m2.setIcon("el-icon-menu");
			m2.setIndex("2");
			m2.setTitle("用户管理");
			m2.setSubs(Arrays.asList(m3));
			meuns.add(m2);
			
			Map<String, Object> result = new HashMap<>();
			result.put("token", JWTUtils.getToken("1001"));
			result.put("meuns", meuns);
			result.put("routers", "/home_/users");
			return ResponseData.ok(result);
		}
		
		if (username.equals("root") && password.equals("root")) {
			List<MenuVo> meuns = new ArrayList<>();
			MenuVo m = new MenuVo();
			m.setIcon("el-icon-setting");
			m.setIndex("/home");
			m.setTitle("首页");
			meuns.add(m);
			
			MenuVo m3 = new MenuVo();
			m3.setIndex("/users");
			m3.setTitle("用户列表");
			
			MenuVo m4 = new MenuVo();
			m4.setIndex("/user/info");
			m4.setTitle("用户信息");
			
			MenuVo m2 = new MenuVo();
			m2.setIcon("el-icon-menu");
			m2.setIndex("2");
			m2.setTitle("用户管理");
			m2.setSubs(Arrays.asList(m3, m4));
			meuns.add(m2);
			
			Map<String, Object> result = new HashMap<>();
			result.put("token", JWTUtils.getToken("1002"));
			result.put("meuns", meuns);
			result.put("routers", "/home_/users_/user/info");
			return ResponseData.ok(result);
		}
		return ResponseData.fail("账号或者密码错误");
	}
	
	@GetMapping("/list")
	public ResponseData queryUsers(String name, String address, Integer page, Integer pageSize){
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		List<UserVo> users = new ArrayList<UserVo>();
		if (page == 1) {
			for (int i = 1; i < 11; i++) {
				users.add(new UserVo(i, "王小虎" + i, "上海市普陀区金沙江路 1518 弄", "2016-05-02"));
			}	
		}
		if (page == 2) {
			for (int i = 11; i < 20; i++) {
				users.add(new UserVo(i, "王小虎" + i, "上海市普陀区金沙江路 1518 弄", "2016-05-02"));
			}	
		}
		if (!address.equals("")) {
			users = new ArrayList<UserVo>();
			users.add(new UserVo(111, "王小虎111", "上海市普陀区金沙江路 1518 弄", "2016-05-02"));
		}
		Map<String, Object> result = new HashMap<>();
		result.put("list", users);
		result.put("currentPage", page);
		result.put("total", 19);
		result.put("pageSize", pageSize);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseData.ok(result);
	}
	
}
