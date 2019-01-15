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

	// ���ݷ��ʲ�
	LeaveDaoInter leaveDao = new LeaveDaoImpl();
	StudentDaoInter studentDao = new StudentDaoImpl();

	// ===========================ҵ��ʼ==============================
	/**
	 * �����֤
	 */
	public Student checkStudent(String sno, String spassword) {
		Student student = studentDao.getStudentBySno(sno);
		if (student != null) {
			if (student.getSpassword().equals(spassword)) {
				if (student.getSdeletestatus().equals("0")) {
					throw new ApplicationException("�˺Ŷ��ᣡ");
				} else {
					return student;
				}
			} else {
				throw new ApplicationException("�����������������������룡");
			}
		} else {
			throw new ApplicationException("�˺Ų����ڣ�");
		}
	}

	/**
	 * �������
	 */
	public void addLeave(Leave leave) {
		leaveDao.addLeave(leave);

	}

	/**
	 * �鿴������ټ�¼
	 */
	public List<Leave> getLeaveBySid(Serializable sid) {
		return leaveDao.getLeaveBySid(sid);
	}

	/**
	 * ����ID��ȡ
	 */
	public Student getStudentById(Serializable sid) {
		return studentDao.getStudentBySid(sid);
	}

	/**
	 * ������Ϣ
	 */
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}
}
