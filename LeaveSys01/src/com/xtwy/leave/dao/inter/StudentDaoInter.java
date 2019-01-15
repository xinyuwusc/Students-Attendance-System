package com.xtwy.leave.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.xtwy.leave.domain.Student;

public interface StudentDaoInter {
	// 根据sid查询学生
	Student getStudentBySid(Serializable sid);

	// 根据sno查询学生
	Student getStudentBySno(String sno);

	// 新增学生
	void addStudent(Student student);

	// 修改学生
	void updateStudent(Student student);

	// 获取某位教师的所有学生
	List<Student> getStudentsByTid(Serializable tid);

	// 获取某位教师的所有学生（分页）
	List<Student> getStudentsByTidByPage(Serializable tid, int pageNum,
			int pageSize);

	// 获取某位教师所有学生的总数
	int getStudentsCountByTid(Serializable tid);

	// 一般而言，定义接口都是由具备一定项目经验的人来做的。
	// 普通的程序员只需要实现接口即可。

	// 一般而言，一张表会建一个Dao类。会给它提供五个常用的方法（五件套）：
	// 添加（add）
	// 更新（update）
	// 删除（delete）
	// 根据id查询（getById）
	// 查询所有信息【分页查询部分信息】（getAll/getAllByPage）
}
