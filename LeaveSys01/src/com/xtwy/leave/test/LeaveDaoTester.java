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
		// ����ID��ѯ
		leave = leaveDao.getLeaveById(45);
		System.out.println(leave);

		// ����ѧ��ID��ѯ��ټ�¼�б�
		// List<Leave> list = leaveDao.getLeaveBySid(32);
		// for (Leave l : list) {
		// System.out.println(l);
		// }

		// ���ݽ�ʦID��ѯ���м���δ�������ټ�¼
		// int count = leaveDao.getNotDealLeaveByTid(2);
		// System.out.println(count);

		// ���ݽ�ʦID��ѯ�Լ�ѧ������ټ�¼�б�
		// List<Leave> list = leaveDao.getLeaveByTid(2);
		// for (Leave l : list) {
		// System.out.println(l);
		// }

	}

}
