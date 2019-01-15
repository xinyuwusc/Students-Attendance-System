package com.xtwy.leave.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xtwy.leave.dao.basic.BasicDaoJDBC;
import com.xtwy.leave.dao.inter.TeacherDaoInter;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.exception.DataAccessException;

public class TeacherDaoImpl implements TeacherDaoInter {

	/**
	 * ����taccount��ѯ��ʦ
	 */
	public Teacher getTeacherByTaccount(String taccount) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select tid,taccount,tpassword,tname,tsex from leave_teacher where taccount=?",
						new Object[] { taccount });

		Teacher teacher = null;
		try {
			if (rset.next()) { // �϶�ֻ��һ����������if,����ж��� ������while
				teacher = new Teacher();
				// ���ݷ�װ
				teacher.setTid(rset.getLong("tid"));
				teacher.setTaccount(rset.getString("taccount"));
				teacher.setTpassword(rset.getString("tpassword"));
				teacher.setTname(rset.getString("tname"));
				teacher.setTsex(rset.getString("tsex"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // ��������Ա����
			throw new DataAccessException(e.getMessage()); // ��ϵͳά����Ա����
		} finally {
			// �ر���Դ
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return teacher;
	}

}
