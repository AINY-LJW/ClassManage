package com.lfh.classManage.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lfh.classManage.dao.AdminMapper;
import com.lfh.classManage.dao.ClassRoomMapper;
import com.lfh.classManage.dao.DeviceRecordMapper;
import com.lfh.classManage.dao.DutyMapper;
import com.lfh.classManage.dao.UserMapper;
import com.lfh.classManage.entity.Admin;
import com.lfh.classManage.entity.AdminExample;
import com.lfh.classManage.entity.ClassRoom;
import com.lfh.classManage.entity.ClassRoomExample;
import com.lfh.classManage.entity.DeviceRecord;
import com.lfh.classManage.entity.DeviceRecordExample;
import com.lfh.classManage.entity.Duty;
import com.lfh.classManage.entity.DutyExample;
import com.lfh.classManage.entity.DutyExample.Criteria;
import com.lfh.classManage.entity.User;
import com.lfh.classManage.entity.UserExample;
import com.lfh.classManage.utils.EasyUIDataGridResult;

@RestController
public class ManageController {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ClassRoomMapper classRoomMapper;
	@Autowired
	private DutyMapper dutyMapper;
	@Autowired
	private DeviceRecordMapper deviceRecordMapper;
	
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse resp;
	
	@Autowired
	private AdminMapper adminMapper;
	/**
	 * 登录
	 * TODO
	 * @param 
	 * @return String
	 */
	@PostMapping("login")
	public String login() {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		AdminExample example=new AdminExample();
		com.lfh.classManage.entity.AdminExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCountEqualTo(name);
		createCriteria.andPasswordEqualTo(pwd);
		List<Admin> selectByExample = adminMapper.selectByExample(example);
		
		if(selectByExample.get(0).getRole()==1) {
			req.getSession().setAttribute("role", selectByExample.get(0).getName());
		}else {
			
		}
		
		if(selectByExample!=null) {
			req.getSession().setAttribute("user", selectByExample.get(0).getName());
			return "ok";
		}else {
			return "no";
		}
		
		
		
	}
	@PostMapping("getAllTeacher")
	public EasyUIDataGridResult getAllTeacher(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		AdminExample example=new AdminExample();
		example.createCriteria().andRoleEqualTo(0);
		List<Admin> selectByExample = adminMapper.selectByExample(example);
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<Admin> pageInfo = new PageInfo<>(selectByExample);
		dataGridResult.setRows(selectByExample);
		dataGridResult.setTotal(pageInfo.getTotal());
		
		return dataGridResult;
	}

	/**
	 * 获取所有学生 TODO
	 * 
	 * @param
	 * @return EasyUIDataGridResult
	 */
	@PostMapping("getAllUsers")
	public EasyUIDataGridResult getAllUsers(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "stuNum", required = false) String stuNum,
			@RequestParam(value = "major", required = false) String major) {
		PageHelper.startPage(pageNum, pageSize);
		UserExample example = new UserExample();

		com.lfh.classManage.entity.UserExample.Criteria createCriteria = example.createCriteria();
		if (name != null && !"".equals(name)) {
			createCriteria.andNameLike("%" + name + "%");
		}
		if (stuNum != null && !"".equals(stuNum)) {
			createCriteria.andStuNumLike("%" + stuNum + "%");
		}
		if (major != null && !"".equals(major)) {
			createCriteria.andMajorLike("%" + major + "%");
		}

		List<User> allUsers = userMapper.selectByExample(example);
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<User> pageInfo = new PageInfo<>(allUsers);
		dataGridResult.setRows(allUsers);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	/**
	 * 获取所有教室
	 */
	@PostMapping("getAllClassRoom")
	public EasyUIDataGridResult getAllClassRoom(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "place", required = false) String place) {
		PageHelper.startPage(pageNum, pageSize);
		ClassRoomExample example = new ClassRoomExample();
		com.lfh.classManage.entity.ClassRoomExample.Criteria createCriteria = example.createCriteria();
		if (name != null && !"".equals(name)) {
			createCriteria.andClassRoomLike("%" + name + "%");
		}
		if (state != null && !"".equals(state)) {
			if ("1".equals(state)) {
				createCriteria.andStateEqualTo(1);
			} else if ("0".equals(state)) {
				createCriteria.andStateEqualTo(0);
			}
		}
		if (place != null && !"".equals(place)) {
			createCriteria.andPlaceLike("%" + name + "%");
		}
		List<ClassRoom> allClassRoom = classRoomMapper.selectByExample(example);
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<ClassRoom> pageInfo = new PageInfo<>(allClassRoom);
		dataGridResult.setRows(allClassRoom);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;

	}

	/**
	 * 获取所有值班表
	 */
	@PostMapping("/getAllDuty")
	public EasyUIDataGridResult getAllDuty(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "place", required = false) String place) {
		PageHelper.startPage(pageNum, pageSize);
		DutyExample example = new DutyExample();

		Criteria createCriteria = example.createCriteria();
		if (id != null && !"".equals(id)) {
			createCriteria.andIdEqualTo(Integer.parseInt(id));
		}
		if (name != null && !"".equals(name)) {
			createCriteria.andNameLike("%" + name + "%");
		}
		if (place != null && !"".equals(place)) {
			createCriteria.andDutyClassLike("%" + place + "%");
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Duty> allDutys = dutyMapper.selectByExample(example);
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<Duty> pageInfo = new PageInfo<>(allDutys);
		dataGridResult.setRows(allDutys);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;

	}
/**
 * 获取所有设备维修使用记录
 * TODO
 * @param 
 * @return EasyUIDataGridResult
 */
	@PostMapping("/getAllDeviceRecords")
	public EasyUIDataGridResult getAllDeviceRecords(@RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize, 
			@RequestParam(value = "name", required = false) String name) {

		PageHelper.startPage(pageNum, pageSize);
		DeviceRecordExample example = new DeviceRecordExample();
		com.lfh.classManage.entity.DeviceRecordExample.Criteria createCriteria = example.createCriteria();
		
		if (name != null && !"".equals(name)) {
			createCriteria.andDeviceNameLike(name);
		}
		
		List<DeviceRecord> selectByExample = deviceRecordMapper.selectByExample(example);
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<DeviceRecord> pageInfo = new PageInfo<>(selectByExample);
		dataGridResult.setRows(selectByExample);
		dataGridResult.setTotal(pageInfo.getTotal());

		return dataGridResult;

	}
}
