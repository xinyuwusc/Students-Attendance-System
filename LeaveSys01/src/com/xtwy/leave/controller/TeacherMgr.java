package com.xtwy.leave.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.domain.Student;
import com.xtwy.leave.domain.Teacher;
import com.xtwy.leave.exception.DataAccessException;
import com.xtwy.leave.model.LeaveDetail;
import com.xtwy.leave.service.impl.StudentServiceImpl;
import com.xtwy.leave.service.impl.TeacherServiceImpl;
import com.xtwy.leave.service.inter.StudentServiceInter;
import com.xtwy.leave.service.inter.TeacherServiceInter;
import com.xtwy.leave.utils.DateUtils;

public class TeacherMgr extends HttpServlet {

	// ҵ���߼�
	StudentServiceInter studentService = new StudentServiceImpl();
	TeacherServiceInter teacherService = new TeacherServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		if ("forAddStudent".equals(act)) { // ��ת������ѧ������
			request.getRequestDispatcher("jsps/teacher/add_student.jsp")
					.forward(request, response);
		} else if ("addStudent".equals(act)) { // ����ѧ��
			// ���ղ���
			String sno = request.getParameter("sno");
			String spassword = request.getParameter("spassword");
			String sname = request.getParameter("sname");
			String ssex = request.getParameter("ssex");
			Date sbirth = DateUtils.StrToDate(request.getParameter("sbirth"));
			String stelephone = request.getParameter("stelephone");

			// ���ݷ�װ
			Student student = new Student();
			student.setSno(sno);
			student.setSname(sname);
			student.setSpassword(spassword);
			student.setSbirth(sbirth);
			student.setSsex(ssex);
			student.setStelephone(stelephone);
			student.setSdeletestatus("1"); // Ĭ����Ч
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"user");
			student.setTid(teacher.getTid());
			try {
				teacherService.addStudent(student);
				request.getRequestDispatcher("TeacherMgr?act=showStudent")
						.forward(request, response);
			} catch (DataAccessException de) {
				// ���ݷ���ʧ��
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("isExistBySno".equals(act)) { // �ж�ѧ��ѧ���Ƿ����
			String sno = request.getParameter("sno");
			boolean isExist = teacherService.isExistBySno(sno);

			// ���ؽ�����ͻ���
			PrintWriter out = response.getWriter();
			out.print(isExist);
			out.flush();
			out.close();
		} else if ("showStudent".equals(act)) { // ��ʾ�ý�ʦ��ѧ���б�(��ҳ����һҳ)
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"user");
			List<Student> list = null;
			try {
				list = teacherService.getStudentsByTidByPage(teacher.getTid(),
						1, 5);
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsps/teacher/show_student.jsp")
						.forward(request, response);
			} catch (DataAccessException da) {
				request.setAttribute("err", da.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("getNextPageStudent".equals(act)) { // ��ȡָ��ҳ����ѧ���б���Ϣ
			String nextPage = request.getParameter("nextPage");
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"user");
			List<Student> list = null;
			PrintWriter out = response.getWriter();
			try {
				list = teacherService.getStudentsByTidByPage(teacher.getTid(),
						Integer.parseInt(nextPage), 5);
				if (list.size() > 0) {
					Gson gson = new Gson();
					String resp = gson.toJson(list);
					out.print(resp);
				} else {
					out.print("empty");
				}
			} catch (DataAccessException da) {
				request.setAttribute("err", da.getMessage());
				out.print("error");
			} finally {
				out.flush();
				out.close();
			}
		} else if ("showLeaves".equals(act)) { // ��ʾ�ý�ʦѧ����������������¼
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"user");
			List<Leave> list = null;
			try {
				list = teacherService.getLeavesByTid(teacher.getTid());
				request.setAttribute("leaveList", list);
				// ҳ����ת
				request.getRequestDispatcher("jsps/teacher/show_leaves.jsp")
						.forward(request, response);
			} catch (DataAccessException de) { // ���ݷ����쳣��ϵͳ������
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("forDealLeave".equals(act)) { // ��ת�������������
			long lid = Long.parseLong(request.getParameter("lid"));
			LeaveDetail leaveDetail = null;
			try {
				leaveDetail = teacherService.getLeaveDetailByLid(lid);
				System.out.println(leaveDetail);
				request.setAttribute("leave", leaveDetail);
				String callback = request.getParameter("callback");
				if ("true".equals(callback)) {
					request.setAttribute("msg", "���ݱ���ɹ���");
				}
				// ҳ����ת
				request.getRequestDispatcher("jsps/teacher/deal_leave.jsp")
						.forward(request, response);
			} catch (DataAccessException de) { // ���ݷ����쳣��ϵͳ������
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("dealLeave".equals(act)) { // ��������
			String lid = request.getParameter("lid");
			String lapprovestatus = request.getParameter("lapprovestatus");
			String lfeedback = request.getParameter("lfeedback");

			Leave leave = null;
			try {
				// ����lid�Ȱ����ݲ�ѯһ��
				leave = teacherService.getLeaveByLid(Integer.parseInt(lid));
				if (leave != null) {
					// ���ݸ���
					leave.setLapprovestatus(lapprovestatus);
					leave.setLfeedback(lfeedback);
					teacherService.approveLeave(leave);

					// ҳ����ת
					request.getRequestDispatcher(
							"TeacherMgr?act=forDealLeave&callback=true")
							.forward(request, response);
				}
			} catch (DataAccessException de) { // ���ݷ����쳣��ϵͳ������
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("getNotRead".equals(act)) { // ��ȡ�ý�ʦ��δ����Ϣ
			// ��ȡ����
			String tid = request.getParameter("tid");
			// System.out.println(tid);
			// ��ѯ����
			int count = teacherService.getNotDealLeaveByTid(Integer
					.parseInt(tid));
			// ���ؿͻ���
			PrintWriter out = response.getWriter();
			out.print(count);
			out.flush();
			out.close();
		} else if ("chageStatus".equals(act)) {
			String status = request.getParameter("status");
			long sid = Long.parseLong(request.getParameter("sid"));
			Student student = null;
			try {
				student = studentService.getStudentById(sid);
				if (student == null) {
					response.getWriter().write("0"); // ʧ��
				} else {
					student.setSdeletestatus(status);
					studentService.updateStudent(student);
					response.getWriter().write("1"); // �ɹ�
				}
			} catch (DataAccessException de) { // ���ݷ����쳣��ϵͳ������
				response.getWriter().write("0"); // ʧ��
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
