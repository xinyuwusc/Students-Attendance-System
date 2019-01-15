package com.xtwy.leave.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.model.LeaveDetail;

public interface LeaveDaoInter {
	// 新增请假记录
	void addLeave(Leave leave);

	// 修改请假记录
	void updateLeave(Leave leave);

	// 根据学生ID查询请假记录列表
	List<Leave> getLeaveBySid(Serializable sid);

	// 根据教师ID查询自己学生的请假记录列表
	List<Leave> getLeaveByTid(Serializable tid);

	// 根据ID查询
	Leave getLeaveById(Serializable lid);

	// 查询请假详细信息（表关联查询）
	LeaveDetail getLeaveDetailById(long lid);

	// 根据教师ID查询还有几条未处理的请假记录（用于ajax调用）
	int getNotDealLeaveByTid(Serializable tid);

}
