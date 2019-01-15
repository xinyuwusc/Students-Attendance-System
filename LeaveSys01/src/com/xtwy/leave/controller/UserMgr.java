package com.xtwy.leave.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xtwy.leave.domain.Student;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.exception.ApplicationException;
import com.xtwy.leave.exception.DataAccessException;
import com.xtwy.leave.service.impl.StudentServiceImpl;
import com.xtwy.leave.service.impl.TeacherServiceImpl;
import com.xtwy.leave.service.inter.StudentServiceInter;
import com.xtwy.leave.service.inter.TeacherServiceInter;

public class UserMgr extends HttpServlet {

	// ҵ���߼�
	StudentServiceInter studentService = new StudentServiceImpl();
	TeacherServiceInter teacherService = new TeacherServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		if ("login".equals(act)) { // ��¼����
			String username = request.getParameter("username");
			String userpass = request.getParameter("userpass");
			String type = request.getParameter("type");
			if (type.equals("1")) { // ��ʦ��¼
				Teacher teacher = null;
				try {
					teacher = teacherService.checkTeacher(username, userpass);
					// ��¼�ɹ�
					request.getSession().setAttribute("user", teacher);
					// request.getRequestDispatcher(
					// "jsps/teacher/show_student.jsp").forward(request,
					// response);
					response.sendRedirect("TeacherMgr?act=showStudent");
				} catch (DataAccessException de) {
					// ���ݷ���ʧ��
					request.setAttribute("err", de.getMessage());
					// log��־�洢

					request.getRequestDispatcher("jsps/error.jsp").forward(
							request, response);
				} catch (ApplicationException ae) {
					// ��¼ʧ��
					request.setAttribute("msg", ae.getMessage());
					request.getRequestDispatcher("jsps/login.jsp").forward(
							request, response);
				}

			} else if (type.equals("2")) { // ѧ����¼
				Student student = null;
				try {
					student = studentService.checkStudent(username, userpass);
					// ��¼�ɹ�
					request.getSession().setAttribute("user", student);
					// request.getRequestDispatcher("jsps/student/show_leaves.jsp")
					// .forward(request, response);
					response.sendRedirect("StudentMgr?act=loadLeaves");
				} catch (DataAccessException de) {
					// ���ݷ���ʧ��
					request.setAttribute("err", de.getMessage());
					request.getRequestDispatcher("jsps/error.jsp").forward(
							request, response);
				} catch (ApplicationException ae) {
					// ��¼ʧ��
					request.setAttribute("msg", ae.getMessage());
					request.getRequestDispatcher("jsps/login.jsp").forward(
							request, response);
				}
			}
		} else if ("logout".equals(act)) { // ע��
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("jsps/login.jsp").forward(request,
					response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
