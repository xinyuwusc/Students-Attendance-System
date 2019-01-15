package com.xtwy.leave.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xtwy.leave.dao.basic.BasicDaoJDBC;
import com.xtwy.leave.dao.inter.TeacherDaoInter;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.exception.DataAccessException;

public class TeacherDaoImpl implements TeacherDaoInter {

	/**
	 * 根据taccount查询教师
	 */
	public Teacher getTeacherByTaccount(String taccount) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select tid,taccount,tpassword,tname,tsex from leave_teacher where taccount=?",
						new Object[] { taccount });

		Teacher teacher = null;
		try {
			if (rset.next()) { // 肯定只有一条，我们用if,如果有多条 我们用while
				teacher = new Teacher();
				// 数据封装
				teacher.setTid(rset.getLong("tid"));
				teacher.setTaccount(rset.getString("taccount"));
				teacher.setTpassword(rset.getString("tpassword"));
				teacher.setTname(rset.getString("tname"));
				teacher.setTsex(rset.getString("tsex"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 给开发人员看的
			throw new DataAccessException(e.getMessage()); // 给系统维护人员看的
		} finally {
			// 关闭资源
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return teacher;
	}

}
