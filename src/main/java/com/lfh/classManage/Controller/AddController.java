package com.lfh.classManage.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lfh.classManage.dao.ClassRoomMapper;
import com.lfh.classManage.dao.DeviceMapper;
import com.lfh.classManage.dao.DeviceRecordMapper;
import com.lfh.classManage.dao.DutyMapper;
import com.lfh.classManage.dao.UserMapper;
import com.lfh.classManage.entity.ClassRoom;
import com.lfh.classManage.entity.Device;
import com.lfh.classManage.entity.DeviceExample;
import com.lfh.classManage.entity.DeviceRecord;
import com.lfh.classManage.entity.DeviceRecordExample;
import com.lfh.classManage.entity.Duty;
import com.lfh.classManage.entity.User;
import com.lfh.classManage.utils.DateUtils;
/**
 * 添加相关Controller
 *
 * @author lfh
 * @version 2019年3月12日
 */
@RestController
public class AddController {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ClassRoomMapper classRoomMapper;
	@Autowired
	private DutyMapper dutyMapper;
	@Autowired
	private DeviceRecordMapper deviceRecordMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse resp;
	/**
	 * 添加值班
	 * TODO
	 * @param 
	 * @return void
	 */
	@PostMapping("addDuty")
	public void addDuty() {
		String name = req.getParameter("name");
		String place = req.getParameter("place");
		String date = req.getParameter("date");
		Duty duty=new Duty();
		duty.setName(name);
		duty.setDutyDate(DateUtils.parse(date, "dd/MM/yyyy"));
		duty.setDutyClass(place);
		try {
			dutyMapper.insert(duty);
		}catch(Exception e) {
			}
		}
	@PostMapping("addUser")
	public void addUser() {
		String name = req.getParameter("name");
		String stuNum = req.getParameter("stuNum");
		String major = req.getParameter("major");
		String pwd = req.getParameter("pwd");
		User user=new User();
		user.setStuNum(stuNum);
		user.setName(name);
		user.setMajor(major);
		user.setPassword(pwd);
		try {
			userMapper.insert(user);
		}catch(Exception e) {
			
		}
	}
	@PostMapping("addClassRoom")
	public void addClassRoom() {
		String name = req.getParameter("name");
		String place = req.getParameter("place");
		
		ClassRoom room=new ClassRoom();
		room.setClassRoom(name);
		room.setPlace(place);
		room.setState(0);
		try {
			classRoomMapper.insert(room);
		}catch(Exception e) {
			
		}
	}
	@PostMapping("addDeviceRecord")
	public void addDeviceRecord() {
		String name = req.getParameter("name");
		String state = req.getParameter("state");
		String record = req.getParameter("record");
		try{
			DeviceExample deviceExample=new DeviceExample();
			deviceExample.createCriteria().andDeviceNameLike(name);
			List<Device> device = deviceMapper.selectByExample(deviceExample);
			
			DeviceRecord deviceRecord=new DeviceRecord();
			deviceRecord.setDate(new Date());
			deviceRecord.setDeviceName(name);
			deviceRecord.setDeviceNo(device.get(0).getDeviceNo());
			deviceRecord.setRecord(record);
			deviceRecord.setState(Integer.parseInt(state));
			deviceRecordMapper.insert(deviceRecord);
		}catch(Exception e){
			
		}
		
		
	}
	
}
