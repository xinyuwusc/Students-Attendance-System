package com.xtwy.leave.test;

import com.xtwy.leave.dao.impl.TeacherDaoImpl;
import com.xtwy.leave.dao.inter.TeacherDaoInter;
import com.xtwy.leave.domain.Teacher;

public class TeacherDaoTester {

	public static void main(String[] args) {
		// getTeacherByTaccount 方法测试
		TeacherDaoInter tdi = new TeacherDaoImpl();
		// Teacher teacher = tdi.getTeacherByTaccount("ztm"); // 数据存在
		Teacher teacher = tdi.getTeacherByTaccount("ztm1"); // 数据不存在
		System.out.println(teacher);
	}

}
