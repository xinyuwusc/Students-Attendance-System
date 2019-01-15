package com.xtwy.leave.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.exception.DataAccessException;
import com.xtwy.leave.service.impl.StudentServiceImpl;
import com.xtwy.leave.service.impl.TeacherServiceImpl;
import com.xtwy.leave.service.inter.StudentServiceInter;
import com.xtwy.leave.service.inter.TeacherServiceInter;
import com.xtwy.leave.utils.DateUtils;

public class StudentMgr extends HttpServlet {
	// 业务逻辑
	TeacherServiceInter tsi = new TeacherServiceImpl();
	StudentServiceInter ssi = new StudentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("act");
		if ("loadLeaves".equals(act)) { // 学生主界面
			Student student = (Student) request.getSession().getAttribute(
					"user");
			List<Leave> list = ssi.getLeaveBySid(student.getSid());
			// 放入request
			request.setAttribute("leaveList", list);
			// 页面跳转
			request.getRequestDispatcher("jsps/student/show_leaves.jsp")
					.forward(request, response);
		} else if ("forAddLeave".equals(act)) { // 跳转到新增请假页面
			request.getRequestDispatcher("jsps/student/add_leave.jsp").forward(
					request, response);
		} else if ("addLeave".equals(act)) { // 新增请假信息
			// 接收参数
			Date lbegintime = DateUtils.StrToDate(request
					.getParameter("lbegintime"));
			Date lendtime = DateUtils.StrToDate(request
					.getParameter("lendtime"));
			String lreason = request.getParameter("lreason");
			// 数据封装
			Leave leave = new Leave();
			Student student = (Student) request.getSession().getAttribute(
					"user");
			leave.setSid(student.getSid());
			leave.setTid(student.getTid());
			leave.setLapplytime(new Date());
			leave.setLbegintime(lbegintime);
			leave.setLendtime(lendtime);
			leave.setLapprovestatus("0");// 默认未审批
			leave.setLreason(lreason);
			leave.setLfeedback("");

			// System.out.println(leave);

			// 数据保存
			try {
				ssi.addLeave(leave);
				// 页面跳转
				response.sendRedirect("StudentMgr?act=loadLeaves");
			} catch (DataAccessException de) { // 数据访问异常，系统级错误
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("forUpdateInfo".equals(act)) { // 跳转到更新界面
			// 根据session中的id获取该用户信息

			// 把信息放入request

			// 页面跳转
			request.getRequestDispatcher("jsps/student/update_info.jsp")
					.forward(request, response);
		} else if ("updateInfo".equals(act)) { // 更新数据
			// 接收参数

			// 更新数据

			// 页面跳转
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
