package com.xtwy.leave.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xtwy.leave.dao.basic.BasicDaoJDBC;
import com.xtwy.leave.dao.inter.StudentDaoInter;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.exception.DataAccessException;

public class StudentDaoImpl implements StudentDaoInter {

	/**
	 * ����sid��ѯѧ��
	 */
	public Student getStudentBySid(Serializable sid) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select sid,sno,spassword,sname,ssex,sbirth,stelephone,sdeletestatus,tid from leave_student where sid=?",
						new Object[] { sid });

		Student student = null;

		try {
			if (rset.next()) {
				student = new Student();
				// ���ݷ�װ
				student.setSid(rset.getLong("sid"));
				student.setSno(rset.getString("sno"));
				student.setSpassword(rset.getString("spassword"));
				student.setSname(rset.getString("sname"));
				student.setSsex(rset.getString("ssex"));
				student.setSbirth(rset.getDate("sbirth"));
				student.setStelephone(rset.getString("stelephone"));
				student.setSdeletestatus(rset.getString("sdeletestatus"));
				student.setTid(rset.getLong("tid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return student;
	}

	/**
	 * ����sno��ѯѧ��
	 */
	public Student getStudentBySno(String sno) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select sid,sno,spassword,sname,ssex,sbirth,stelephone,sdeletestatus,tid from leave_student where sno=?",
						new Object[] { sno });

		Student student = null;

		try {
			if (rset.next()) {
				student = new Student();
				// ���ݷ�װ
				student.setSid(rset.getLong("sid"));
				student.setSno(rset.getString("sno"));
				student.setSpassword(rset.getString("spassword"));
				student.setSname(rset.getString("sname"));
				student.setSsex(rset.getString("ssex"));
				student.setSbirth(rset.getDate("sbirth"));
				student.setStelephone(rset.getString("stelephone"));
				student.setSdeletestatus(rset.getString("sdeletestatus"));
				student.setTid(rset.getLong("tid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return student;
	}

	/**
	 * ����ѧ��
	 */
	public void addStudent(Student student) {
		BasicDaoJDBC
				.executeUpdate(
						"insert into leave_student values(seq_leave.nextval,?,?,?,?,?,?,?,?)",
						new Object[] {
								student.getSno(),
								student.getSpassword(),
								student.getSname(),
								student.getSsex(),
								new java.sql.Timestamp(student.getSbirth()
										.getTime()), student.getStelephone(),
								student.getSdeletestatus(), student.getTid() });
	}

	/**
	 * �޸�ѧ��
	 */
	public void updateStudent(Student student) {
		BasicDaoJDBC
				.executeUpdate(
						"update leave_student set sno=?,spassword=?,sname=?,ssex=?,sbirth=?,stelephone=?,sdeletestatus=?,tid=? where sid=?",
						new Object[] {
								student.getSno(),
								student.getSpassword(),
								student.getSname(),
								student.getSsex(),
								new java.sql.Timestamp(student.getSbirth()
										.getTime()), student.getStelephone(),
								student.getSdeletestatus(), student.getTid(),
								student.getSid() });
	}

	// public void deleteStudent(long sid) {
	// // ��ȡѧ������
	// // deleteStatus = 0
	// // update����
	// }

	/**
	 * ��ȡĳλ��ʦ������ѧ��
	 */
	public List<Student> getStudentsByTid(Serializable tid) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select sid,sno,spassword,sname,ssex,sbirth,stelephone,sdeletestatus,tid from leave_student where tid=?",
						new Object[] { tid });

		List<Student> studentList = null;

		try {
			studentList = new ArrayList<Student>();
			while (rset.next()) {
				Student student = new Student();
				// ���ݷ�װ
				student.setSid(rset.getLong("sid"));
				student.setSno(rset.getString("sno"));
				student.setSpassword(rset.getString("spassword"));
				student.setSname(rset.getString("sname"));
				student.setSsex(rset.getString("ssex"));
				student.setSbirth(rset.getDate("sbirth"));
				student.setStelephone(rset.getString("stelephone"));
				student.setSdeletestatus(rset.getString("sdeletestatus"));
				student.setTid(rset.getLong("tid"));

				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return studentList;
	}

	/**
	 * ��ȡĳλ��ʦ������ѧ������ҳ��
	 */
	public List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize) {
		ResultSet rset = BasicDaoJDBC
				.executeQueryByPage(
						"select sid,sno,spassword,sname,ssex,sbirth,stelephone,sdeletestatus,tid from leave_student where tid=?",
						new Object[] { tid }, pageNum, pageSize);

		List<Student> studentList = null;

		try {
			studentList = new ArrayList<Student>();
			while (rset.next()) {
				Student student = new Student();
				// ���ݷ�װ
				student.setSid(rset.getLong("sid"));
				student.setSno(rset.getString("sno"));
				student.setSpassword(rset.getString("spassword"));
				student.setSname(rset.getString("sname"));
				student.setSsex(rset.getString("ssex"));
				student.setSbirth(rset.getDate("sbirth"));
				student.setStelephone(rset.getString("stelephone"));
				student.setSdeletestatus(rset.getString("sdeletestatus"));
				student.setTid(rset.getLong("tid"));

				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return studentList;
	}

	/**
	 * ��ȡĳλ��ʦ����ѧ��������
	 */
	public int getStudentsCountByTid(Serializable tid) {
		ResultSet rset = BasicDaoJDBC.executeQuery(
				"select count(*) as count from leave_student where tid=?",
				new Object[] { tid });
		int count = 0;
		try {
			if (rset.next()) {
				count = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return count;
	}

}
