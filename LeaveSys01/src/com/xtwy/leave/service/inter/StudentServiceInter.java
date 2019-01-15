package com.xtwy.leave.service.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;

public interface StudentServiceInter {
	// 身份验证
	Student checkStudent(String sno, String spassword);

	// 新增请假
	void addLeave(Leave leave);

	// 查看个人请假记录
	List<Leave> getLeaveBySid(Serializable sid);

	// 根据ID获取
	Student getStudentById(Serializable sid);

	// 更新信息
	void updateStudent(Student student);
}
