package com.xtwy.leave.service.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.model.LeaveDetail;

public interface TeacherServiceInter {
	// 身份校验
	Teacher checkTeacher(String taccount, String tpassword);

	// 新增学生
	void addStudent(Student student);

	// 根据sno判断该学号是否已经存在
	boolean isExistBySno(String sno);

	// 获取该教师的所有学生
	List<Student> getStudentsByTid(Serializable tid);

	// 获取请假审批列表
	List<Leave> getLeavesByTid(Serializable tid);

	// 请假审批
	void approveLeave(Leave leave);

	// 根据Lid获取请假基本信息
	Leave getLeaveByLid(Serializable lid);

	// 获取某位教师的所有学生（分页）
	List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize);

	// 获取某位教师所有学生的总数
	int getStudentsCountByTid(Serializable tid);

	// 根据Lid获取请假详细信息
	LeaveDetail getLeaveDetailByLid(long lid);

	// 根据教师ID查询还有几条未处理的请假记录
	int getNotDealLeaveByTid(Serializable tid);
}
