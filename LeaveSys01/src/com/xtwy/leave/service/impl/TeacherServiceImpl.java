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

	// ���ݷ��ʲ㣨spring��ܣ�
	LeaveDaoInter leaveDao = new LeaveDaoImpl();
	StudentDaoInter studentDao = new StudentDaoImpl();
	TeacherDaoInter teacherDao = new TeacherDaoImpl();

	// ===========================ҵ��ʼ==============================
	/**
	 * ���У��
	 */
	public Teacher checkTeacher(String taccount, String tpassword) {
		Teacher teacher = teacherDao.getTeacherByTaccount(taccount);
		if (teacher != null) {
			if (teacher.getTpassword().equals(tpassword)) {
				return teacher;
			} else {
				throw new ApplicationException("�����������������������룡");
			}
		} else {
			throw new ApplicationException("�˺Ų����ڣ�");
		}
	}

	/**
	 * ����ѧ��
	 */
	public void addStudent(Student student) {
		studentDao.addStudent(student);

	}

	/**
	 * ��ȡ�ý�ʦ������ѧ��
	 */
	public List<Student> getStudentsByTid(Serializable tid) {
		return studentDao.getStudentsByTid(tid);
	}

	/**
	 * ��ȡ��������б�
	 */
	public List<Leave> getLeavesByTid(Serializable tid) {
		return leaveDao.getLeaveByTid(tid);
	}

	/**
	 * �������
	 */
	public void approveLeave(Leave leave) {
		leaveDao.updateLeave(leave); // ��������ı�����ʵ���Ǹ��²���

	}

	/**
	 * ����Lid��ȡ��ٻ�����Ϣ
	 */
	public Leave getLeaveByLid(Serializable lid) {
		return leaveDao.getLeaveById(lid);
	}

	/**
	 * ���ݽ�ʦID��ѯ���м���δ�������ټ�¼
	 */
	public int getNotDealLeaveByTid(Serializable tid) {
		return leaveDao.getNotDealLeaveByTid(tid);
	}

	/**
	 * ����sno�жϸ�ѧ���Ƿ��Ѿ�����
	 */
	public boolean isExistBySno(String sno) {
		if (studentDao.getStudentBySno(sno) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ȡĳλ��ʦ������ѧ������ҳ��
	 */
	public List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize) {
		return studentDao.getStudentsByTidByPage(tid, pageNum, pageSize);
	}

	/**
	 * ��ȡĳλ��ʦ����ѧ��������
	 */
	public int getStudentsCountByTid(Serializable tid) {
		return studentDao.getStudentsCountByTid(tid);
	}

	/**
	 * ����Lid��ȡ�����ϸ��Ϣ
	 */
	public LeaveDetail getLeaveDetailByLid(long lid) {
		return leaveDao.getLeaveDetailById(lid);
	}

}
