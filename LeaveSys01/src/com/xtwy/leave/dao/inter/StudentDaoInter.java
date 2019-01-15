package com.xtwy.leave.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Student;

public interface StudentDaoInter {
	// ����sid��ѯѧ��
	Student getStudentBySid(Serializable sid);

	// ����sno��ѯѧ��
	Student getStudentBySno(String sno);

	// ����ѧ��
	void addStudent(Student student);

	// �޸�ѧ��
	void updateStudent(Student student);

	// ��ȡĳλ��ʦ������ѧ��
	List<Student> getStudentsByTid(Serializable tid);

	// ��ȡĳλ��ʦ������ѧ������ҳ��
	List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize);

	// ��ȡĳλ��ʦ����ѧ��������
	int getStudentsCountByTid(Serializable tid);

	// һ����ԣ�����ӿڶ����ɾ߱�һ����Ŀ������������ġ�
	// ��ͨ�ĳ���Աֻ��Ҫʵ�ֽӿڼ��ɡ�

	// һ����ԣ�һ�ű�Ὠһ��Dao�ࡣ������ṩ������õķ���������ף���
	// ��ӣ�add��
	// ���£�update��
	// ɾ����delete��
	// ����id��ѯ��getById��
	// ��ѯ������Ϣ����ҳ��ѯ������Ϣ����getAll/getAllByPage��
}
