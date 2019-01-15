package com.xtwy.leave.service.impl;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.dao.impl.LeaveDaoImpl;
import com.xtwy.leave.dao.impl.StudentDaoImpl;
import com.xtwy.leave.dao.inter.LeaveDaoInter;
import com.xtwy.leave.dao.inter.StudentDaoInter;
import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.exception.ApplicationException;
import com.xtwy.leave.service.inter.StudentServiceInter;

public class StudentServiceImpl implements StudentServiceInter {

	// 数据访问层
	LeaveDaoInter leaveDao = new LeaveDaoImpl();
	StudentDaoInter studentDao = new StudentDaoImpl();

	// ===========================业务开始==============================
	/**
	 * 身份验证
	 */
	public Student checkStudent(String sno, String spassword) {
		Student student = studentDao.getStudentBySno(sno);
		if (student != null) {
			if (student.getSpassword().equals(spassword)) {
				if (student.getSdeletestatus().equals("0")) {
					throw new ApplicationException("账号冻结！");
				} else {
					return student;
				}
			} else {
				throw new ApplicationException("您输入的密码错误，请重新输入！");
			}
		} else {
			throw new ApplicationException("账号不存在！");
		}
	}

	/**
	 * 新增请假
	 */
	public void addLeave(Leave leave) {
		leaveDao.addLeave(leave);

	}

	/**
	 * 查看个人请假记录
	 */
	public List<Leave> getLeaveBySid(Serializable sid) {
		return leaveDao.getLeaveBySid(sid);
	}

	/**
	 * 根据ID获取
	 */
	public Student getStudentById(Serializable sid) {
		return studentDao.getStudentBySid(sid);
	}

	/**
	 * 更新信息
	 */
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}
}
