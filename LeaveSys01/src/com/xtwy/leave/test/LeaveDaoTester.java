package com.xtwy.leave.test;

import com.xtwy.leave.dao.impl.LeaveDaoImpl;
import com.xtwy.leave.dao.inter.LeaveDaoInter;
import com.xtwy.leave.domain.Leave;

public class LeaveDaoTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeaveDaoInter leaveDao = new LeaveDaoImpl();
		Leave leave = null;
		// 根据ID查询
		leave = leaveDao.getLeaveById(45);
		System.out.println(leave);

		// 根据学生ID查询请假记录列表
		// List<Leave> list = leaveDao.getLeaveBySid(32);
		// for (Leave l : list) {
		// System.out.println(l);
		// }

		// 根据教师ID查询还有几条未处理的请假记录
		// int count = leaveDao.getNotDealLeaveByTid(2);
		// System.out.println(count);

		// 根据教师ID查询自己学生的请假记录列表
		// List<Leave> list = leaveDao.getLeaveByTid(2);
		// for (Leave l : list) {
		// System.out.println(l);
		// }

	}

}
