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

	// 业务逻辑
	StudentServiceInter studentService = new StudentServiceImpl();
	TeacherServiceInter teacherService = new TeacherServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		if ("login".equals(act)) { // 登录操作
			String username = request.getParameter("username");
			String userpass = request.getParameter("userpass");
			String type = request.getParameter("type");
			if (type.equals("1")) { // 教师登录
				Teacher teacher = null;
				try {
					teacher = teacherService.checkTeacher(username, userpass);
					// 登录成功
					request.getSession().setAttribute("user", teacher);
					// request.getRequestDispatcher(
					// "jsps/teacher/show_student.jsp").forward(request,
					// response);
					response.sendRedirect("TeacherMgr?act=showStudent");
				} catch (DataAccessException de) {
					// 数据访问失败
					request.setAttribute("err", de.getMessage());
					// log日志存储

					request.getRequestDispatcher("jsps/error.jsp").forward(
							request, response);
				} catch (ApplicationException ae) {
					// 登录失败
					request.setAttribute("msg", ae.getMessage());
					request.getRequestDispatcher("jsps/login.jsp").forward(
							request, response);
				}

			} else if (type.equals("2")) { // 学生登录
				Student student = null;
				try {
					student = studentService.checkStudent(username, userpass);
					// 登录成功
					request.getSession().setAttribute("user", student);
					// request.getRequestDispatcher("jsps/student/show_leaves.jsp")
					// .forward(request, response);
					response.sendRedirect("StudentMgr?act=loadLeaves");
				} catch (DataAccessException de) {
					// 数据访问失败
					request.setAttribute("err", de.getMessage());
					request.getRequestDispatcher("jsps/error.jsp").forward(
							request, response);
				} catch (ApplicationException ae) {
					// 登录失败
					request.setAttribute("msg", ae.getMessage());
					request.getRequestDispatcher("jsps/login.jsp").forward(
							request, response);
				}
			}
		} else if ("logout".equals(act)) { // 注销
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
