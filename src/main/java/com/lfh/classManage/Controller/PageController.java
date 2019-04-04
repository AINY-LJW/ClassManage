package com.lfh.classManage.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lfh.classManage.dao.DeviceMapper;
import com.lfh.classManage.dao.DeviceRecordMapper;
import com.lfh.classManage.dao.UserMapper;
import com.lfh.classManage.entity.Device;
import com.lfh.classManage.entity.DeviceExample;
import com.lfh.classManage.entity.DeviceRecord;
import com.lfh.classManage.entity.DeviceRecordExample;
import com.lfh.classManage.entity.User;
import com.lfh.classManage.entity.UserExample;

/**
 * 页面跳转Controller
 * 
 * @author lfh
 * @version 2019年3月11日
 */
@Controller
public class PageController {
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private HttpServletRequest req;
	@GetMapping("login")
	public String login() {
		return "login";
	}
	/*8
	 * 退出
	 */
	@GetMapping("loginout")
	public String loginout() {
		req.getSession().removeAttribute("user");
		req.getSession().removeAttribute("role");
		
		return "login";
		
		
	}
	@GetMapping("tohomePageIn")
	public String homePageIn() {
		return "homePageIn";
	}
	@GetMapping("/getAllTeacher")
	public String getAllTeacher(){
		return "allTeacher";
	}
	/**
	 * 所有上课地点 TODO
	 * 
	 * @param
	 * @return String
	 */
	@GetMapping("/allClassMap")
	public String toMappage() {
		return "allClassMap";
	}

	/**
	 * 首页 TODO
	 * 
	 * @param
	 * @return String
	 */
	@GetMapping({ "/index", "/" })
	public String toHomePage() {
		return "home";
	}

	/**
	 * 所有学生 TODO
	 * 
	 * @param
	 * @return String
	 */
	@GetMapping("getAllUsers")
	public String getAllUsers() {
		return "allUser";
	}

	/**
	 * 所有教室页面
	 */
	@GetMapping("/getAllClassRoom")
	public String allClassRoom() {
		return "allClassRoom";

	}
	/**
	 * 所有值班
	 * TODO
	 * @param 
	 * @return String
	 */
	@GetMapping("allDuty")
	public String allDuty() {
		return "allDuty";
		
	}
	@GetMapping("allDeviceRecord")
	public  String allDeviceRecord(Model model) {
		List<Device> selectByExample = deviceMapper.selectByExample(new DeviceExample());
		model.addAttribute("devices",selectByExample);
		return "deviceRecord";
	}
}
