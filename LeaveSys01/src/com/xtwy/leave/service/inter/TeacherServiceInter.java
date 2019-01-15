package com.xtwy.leave.service.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.model.LeaveDetail;

public interface TeacherServiceInter {
	// ���У��
	Teacher checkTeacher(String taccount, String tpassword);

	// ����ѧ��
	void addStudent(Student student);

	// ����sno�жϸ�ѧ���Ƿ��Ѿ�����
	boolean isExistBySno(String sno);

	// ��ȡ�ý�ʦ������ѧ��
	List<Student> getStudentsByTid(Serializable tid);

	// ��ȡ��������б�
	List<Leave> getLeavesByTid(Serializable tid);

	// �������
	void approveLeave(Leave leave);

	// ����Lid��ȡ��ٻ�����Ϣ
	Leave getLeaveByLid(Serializable lid);

	// ��ȡĳλ��ʦ������ѧ������ҳ��
	List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize);

	// ��ȡĳλ��ʦ����ѧ��������
	int getStudentsCountByTid(Serializable tid);

	// ����Lid��ȡ�����ϸ��Ϣ
	LeaveDetail getLeaveDetailByLid(long lid);

	// ���ݽ�ʦID��ѯ���м���δ�������ټ�¼
	int getNotDealLeaveByTid(Serializable tid);
}
