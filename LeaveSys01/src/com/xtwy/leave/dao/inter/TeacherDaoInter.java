package com.xtwy.leave.dao.inter;

import com.xtwy.leave.domain.Teacher;

public interface TeacherDaoInter {
	// 根据taccount查询教师
	Teacher getTeacherByTaccount(String taccount);

}
