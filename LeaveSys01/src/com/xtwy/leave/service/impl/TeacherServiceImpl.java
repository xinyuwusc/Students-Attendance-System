package com.xtwy.leave.service.impl;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.dao.impl.LeaveDaoImpl;
import com.xtwy.leave.dao.impl.StudentDaoImpl;
import com.xtwy.leave.dao.impl.TeacherDaoImpl;
import com.xtwy.leave.dao.inter.LeaveDaoInter;
import com.xtwy.leave.dao.inter.StudentDaoInter;
import com.xtwy.leave.dao.inter.TeacherDaoInter;
import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.exception.ApplicationException;
import com.xtwy.leave.model.LeaveDetail;
import com.xtwy.leave.service.inter.TeacherServiceInter;

public class TeacherServiceImpl implements TeacherServiceInter {

	// 数据访问层（spring框架）
	LeaveDaoInter leaveDao = new LeaveDaoImpl();
	StudentDaoInter studentDao = new StudentDaoImpl();
	TeacherDaoInter teacherDao = new TeacherDaoImpl();

	// ===========================业务开始==============================
	/**
	 * 身份校验
	 */
	public Teacher checkTeacher(String taccount, String tpassword) {
		Teacher teacher = teacherDao.getTeacherByTaccount(taccount);
		if (teacher != null) {
			if (teacher.getTpassword().equals(tpassword)) {
				return teacher;
			} else {
				throw new ApplicationException("您输入的密码错误，请重新输入！");
			}
		} else {
			throw new ApplicationException("账号不存在！");
		}
	}

	/**
	 * 新增学生
	 */
	public void addStudent(Student student) {
		studentDao.addStudent(student);

	}

	/**
	 * 获取该教师的所有学生
	 */
	public List<Student> getStudentsByTid(Serializable tid) {
		return studentDao.getStudentsByTid(tid);
	}

	/**
	 * 获取请假审批列表
	 */
	public List<Leave> getLeavesByTid(Serializable tid) {
		return leaveDao.getLeaveByTid(tid);
	}

	/**
	 * 请假审批
	 */
	public void approveLeave(Leave leave) {
		leaveDao.updateLeave(leave); // 请假审批的本质其实就是更新操作

	}

	/**
	 * 根据Lid获取请假基本信息
	 */
	public Leave getLeaveByLid(Serializable lid) {
		return leaveDao.getLeaveById(lid);
	}

	/**
	 * 根据教师ID查询还有几条未处理的请假记录
	 */
	public int getNotDealLeaveByTid(Serializable tid) {
		return leaveDao.getNotDealLeaveByTid(tid);
	}

	/**
	 * 根据sno判断该学号是否已经存在
	 */
	public boolean isExistBySno(String sno) {
		if (studentDao.getStudentBySno(sno) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取某位教师的所有学生（分页）
	 */
	public List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize) {
		return studentDao.getStudentsByTidByPage(tid, pageNum, pageSize);
	}

	/**
	 * 获取某位教师所有学生的总数
	 */
	public int getStudentsCountByTid(Serializable tid) {
		return studentDao.getStudentsCountByTid(tid);
	}

	/**
	 * 根据Lid获取请假详细信息
	 */
	public LeaveDetail getLeaveDetailByLid(long lid) {
		return leaveDao.getLeaveDetailById(lid);
	}

}
