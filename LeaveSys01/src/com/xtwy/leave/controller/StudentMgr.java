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
	// ҵ���߼�
	TeacherServiceInter tsi = new TeacherServiceImpl();
	StudentServiceInter ssi = new StudentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("act");
		if ("loadLeaves".equals(act)) { // ѧ��������
			Student student = (Student) request.getSession().getAttribute(
					"user");
			List<Leave> list = ssi.getLeaveBySid(student.getSid());
			// ����request
			request.setAttribute("leaveList", list);
			// ҳ����ת
			request.getRequestDispatcher("jsps/student/show_leaves.jsp")
					.forward(request, response);
		} else if ("forAddLeave".equals(act)) { // ��ת���������ҳ��
			request.getRequestDispatcher("jsps/student/add_leave.jsp").forward(
					request, response);
		} else if ("addLeave".equals(act)) { // ���������Ϣ
			// ���ղ���
			Date lbegintime = DateUtils.StrToDate(request
					.getParameter("lbegintime"));
			Date lendtime = DateUtils.StrToDate(request
					.getParameter("lendtime"));
			String lreason = request.getParameter("lreason");
			// ���ݷ�װ
			Leave leave = new Leave();
			Student student = (Student) request.getSession().getAttribute(
					"user");
			leave.setSid(student.getSid());
			leave.setTid(student.getTid());
			leave.setLapplytime(new Date());
			leave.setLbegintime(lbegintime);
			leave.setLendtime(lendtime);
			leave.setLapprovestatus("0");// Ĭ��δ����
			leave.setLreason(lreason);
			leave.setLfeedback("");

			// System.out.println(leave);

			// ���ݱ���
			try {
				ssi.addLeave(leave);
				// ҳ����ת
				response.sendRedirect("StudentMgr?act=loadLeaves");
			} catch (DataAccessException de) { // ���ݷ����쳣��ϵͳ������
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("forUpdateInfo".equals(act)) { // ��ת�����½���
			// ����session�е�id��ȡ���û���Ϣ

			// ����Ϣ����request

			// ҳ����ת
			request.getRequestDispatcher("jsps/student/update_info.jsp")
					.forward(request, response);
		} else if ("updateInfo".equals(act)) { // ��������
			// ���ղ���

			// ��������

			// ҳ����ת
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
