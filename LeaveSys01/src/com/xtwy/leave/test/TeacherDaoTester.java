package com.xtwy.leave.test;

import com.xtwy.leave.dao.impl.TeacherDaoImpl;
import com.xtwy.leave.dao.inter.TeacherDaoInter;
import com.xtwy.leave.domain.Teacher;

public class TeacherDaoTester {

	public static void main(String[] args) {
		// getTeacherByTaccount ��������
		TeacherDaoInter tdi = new TeacherDaoImpl();
		// Teacher teacher = tdi.getTeacherByTaccount("ztm"); // ���ݴ���
		Teacher teacher = tdi.getTeacherByTaccount("ztm1"); // ���ݲ�����
		System.out.println(teacher);
	}

}
