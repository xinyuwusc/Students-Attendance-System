package com.xtwy.leave.service.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;

public interface StudentServiceInter {
	// �����֤
	Student checkStudent(String sno, String spassword);

	// �������
	void addLeave(Leave leave);

	// �鿴������ټ�¼
	List<Leave> getLeaveBySid(Serializable sid);

	// ����ID��ȡ
	Student getStudentById(Serializable sid);

	// ������Ϣ
	void updateStudent(Student student);
}
