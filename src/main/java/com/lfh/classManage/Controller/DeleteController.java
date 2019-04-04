package com.lfh.classManage.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lfh.classManage.dao.ClassRoomMapper;
import com.lfh.classManage.dao.DeviceRecordMapper;
import com.lfh.classManage.dao.DutyMapper;
import com.lfh.classManage.dao.UserMapper;
/**
 * 删除
 *
 * @author lfh
 * @version 2019年3月12日
 */
@RestController
public class DeleteController {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ClassRoomMapper classRoomMapper;
	@Autowired
	private DutyMapper dutyMapper;
	@Autowired DeviceRecordMapper deviceRecordMapper;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse resp;
	/**
	 * 删除值班
	 * TODO
	 * @param 
	 * @return void
	 */
	@PostMapping("deleteDutyById")
	public void deleteDutyById() {
		String id = req.getParameter("id");
		try{
			dutyMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}catch(Exception e) {
			
		}
		
	}
	/**
	 * 删除用户
	 * TODO
	 * @param 
	 * @return void
	 */
	@PostMapping("deleteUserById")
	public void deleteUserById() {
		String id = req.getParameter("id");
		try {
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}catch(Exception e) {
			
		}
	}
	/**
	 * 删除教室信息
	 * TODO
	 * @param 
	 * @return void
	 */
	@PostMapping("deleteClassRoomById")
	public void deleteClassRoomById() {
		String id = req.getParameter("id");
		try {
			classRoomMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}catch(Exception e) {
			
		}
	}
	/**
	 * 删除设备使用和维修记录
	 * TODO
	 * @param 
	 * @return void
	 */
	@PostMapping("deleteDeviceRecordById")
	public void deleteDeviceRecordById() {
		String id = req.getParameter("id");
		try {
			deviceRecordMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}catch(Exception e) {
			
		}
	}
}
