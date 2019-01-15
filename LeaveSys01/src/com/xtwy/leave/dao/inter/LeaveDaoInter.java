package com.xtwy.leave.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.model.LeaveDetail;

public interface LeaveDaoInter {
	// ������ټ�¼
	void addLeave(Leave leave);

	// �޸���ټ�¼
	void updateLeave(Leave leave);

	// ����ѧ��ID��ѯ��ټ�¼�б�
	List<Leave> getLeaveBySid(Serializable sid);

	// ���ݽ�ʦID��ѯ�Լ�ѧ������ټ�¼�б�
	List<Leave> getLeaveByTid(Serializable tid);

	// ����ID��ѯ
	Leave getLeaveById(Serializable lid);

	// ��ѯ�����ϸ��Ϣ���������ѯ��
	LeaveDetail getLeaveDetailById(long lid);

	// ���ݽ�ʦID��ѯ���м���δ�������ټ�¼������ajax���ã�
	int getNotDealLeaveByTid(Serializable tid);

}
