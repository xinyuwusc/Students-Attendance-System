package com.xtwy.leave.test;

import com.xtwy.leave.dao.impl.StudentDaoImpl;
import com.xtwy.leave.dao.inter.StudentDaoInter;

public class StudentDaoTester {
	public static void main(String[] args) {
		// getStudentBySid ͨ��
		// StudentDaoInter sdi = new StudentDaoImpl();
		// // Student student = sdi.getStudentBySid(32); //�����ݵ�
		// Student student = sdi.getStudentBySid(3211); // û���ݵ�
		// System.out.println(student);

		// ===================================================
		// getStudentBySno ͨ��
		// StudentDaoInter sdi = new StudentDaoImpl();
		// // Student student = sdi.getStudentBySno("dahua8"); //û�����ݵ�
		// Student student = sdi.getStudentBySno("12301130"); // �����ݵ�
		// System.out.println(student);

		// =====================================================
		// addStudent ͨ��
		// Student student = new Student();
		// student.setSno("12301132");
		// student.setSpassword("123456");
		// student.setSname("test108");
		// student.setSsex("1");
		// student.setSbirth(new Date());
		// student.setStelephone("18359100031");
		// student.setSdeletestatus("0");
		// student.setTid(2);
		//
		// StudentDaoInter sdi = new StudentDaoImpl();
		//
		// sdi.addStudent(student);

		// =================================================
		// updateStudent ͨ��
		// StudentDaoInter sdi = new StudentDaoImpl();
		// // ��ȡ����
		// Student student = sdi.getStudentBySid(67);
		// System.out.println(student);
		// student.setSdeletestatus("0");
		// // ��������
		// sdi.updateStudent(student);
		// System.out.println(student);

		// ========================================================
		// getStudentsByTid �ɹ�
		// StudentDaoInter sdi = new StudentDaoImpl();
		// List<Student> studentList = sdi.getStudentsByTid(2);
		//
		// for (Student s : studentList) {
		// System.out.println(s);
		// }

		// =========================================================
		// StudentDaoInter sdi = new StudentDaoImpl();
		// List<Student> studentList = sdi.getStudentsByTidByPage(2, 2, 5);
		// for (Student s : studentList) {
		// System.out.println(s);
		// }

		// ===========================================================
		StudentDaoInter sdi = new StudentDaoImpl();
		int count = sdi.getStudentsCountByTid(2);
		System.out.println(count);
	}
}
