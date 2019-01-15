package com.xtwy.leave.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xtwy.leave.dao.basic.BasicDaoJDBC;
import com.xtwy.leave.dao.inter.LeaveDaoInter;
import com.xtwy.leave.domain.Leave;
import com.xtwy.leave.exception.DataAccessException;
import com.xtwy.leave.model.LeaveDetail;

public class LeaveDaoImpl implements LeaveDaoInter {

	// sql语句 很多应用程序都会把所有的SQL语句写到Dao的最上面，在方法调用时直接使用字符串变量名即可
	// private final static String ADD_STUDENT =
	// "insert into leave_leave values(seq_leave.nextval,?,?,?,?,?,?,?,?";

	/**
	 * 新增请假记录
	 */
	public void addLeave(Leave leave) {
		String sql = "insert into leave_leave values(seq_leave.nextval,?,?,?,?,?,?,?,?)";
		BasicDaoJDBC
				.executeUpdate(
						sql,
						new Object[] {
								leave.getSid(),
								leave.getTid(),
								new java.sql.Timestamp(leave.getLapplytime()
										.getTime()),
								new java.sql.Timestamp(leave.getLbegintime()
										.getTime()),
								new java.sql.Timestamp(leave.getLendtime()
										.getTime()), leave.getLreason(),
								leave.getLapprovestatus(), leave.getLfeedback() });
	}

	/**
	 * 修改请假记录
	 */
	public void updateLeave(Leave leave) {
		BasicDaoJDBC
				.executeUpdate(
						"update leave_leave set sid=?,tid=?,lapplytime=?,lbegintime=?,lendtime=?,lreason=?,lapprovestatus=?,lfeedback=? where lid=?",
						new Object[] {
								leave.getSid(),
								leave.getTid(),
								new java.sql.Timestamp(leave.getLapplytime()
										.getTime()),
								new java.sql.Timestamp(leave.getLbegintime()
										.getTime()),
								new java.sql.Timestamp(leave.getLendtime()
										.getTime()), leave.getLreason(),
								leave.getLapprovestatus(),
								leave.getLfeedback(), leave.getLid() });
	}

	/**
	 * 根据学生ID查询请假记录列表
	 */
	public List<Leave> getLeaveBySid(Serializable sid) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select lid,sid,tid,lapplytime,lbegintime,lendtime,lreason,lapprovestatus,lfeedback from leave_leave where sid=? order by lapplytime desc",
						new Object[] { sid });
		List<Leave> leaveList = null;
		try {
			leaveList = new ArrayList<Leave>();
			while (rset.next()) {
				Leave leave = new Leave();
				leave.setLid(rset.getLong("lid"));
				leave.setSid(rset.getLong("sid"));
				leave.setTid(rset.getLong("tid"));
				leave.setLapplytime(rset.getDate("lapplytime"));
				leave.setLbegintime(rset.getDate("lbegintime"));
				leave.setLendtime(rset.getDate("lendtime"));
				leave.setLreason(rset.getString("lreason"));
				leave.setLapprovestatus(rset.getString("lapprovestatus"));
				leave.setLfeedback(rset.getString("lfeedback"));

				leaveList.add(leave);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return leaveList;
	}

	/**
	 * 根据教师ID查询请假记录列表
	 */
	public List<Leave> getLeaveByTid(Serializable tid) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select lid,sid,tid,lapplytime,lbegintime,lendtime,lreason,lapprovestatus,lfeedback from leave_leave where tid=? order by lapplytime desc",
						new Object[] { tid });
		List<Leave> leaveList = null;
		try {
			leaveList = new ArrayList<Leave>();
			while (rset.next()) {
				Leave leave = new Leave();
				leave.setLid(rset.getLong("lid"));
				leave.setSid(rset.getLong("sid"));
				leave.setTid(rset.getLong("tid"));
				leave.setLapplytime(rset.getDate("lapplytime"));
				leave.setLbegintime(rset.getDate("lbegintime"));
				leave.setLendtime(rset.getDate("lendtime"));
				leave.setLreason(rset.getString("lreason"));
				leave.setLapprovestatus(rset.getString("lapprovestatus"));
				leave.setLfeedback(rset.getString("lfeedback"));

				leaveList.add(leave);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return leaveList;
	}

	/**
	 * 根据ID查询
	 */
	public Leave getLeaveById(Serializable lid) {
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select lid,sid,tid,lapplytime,lbegintime,lendtime,lreason,lapprovestatus,lfeedback from leave_leave where lid=?",
						new Object[] { lid });
		Leave leave = null;
		try {
			if (rset.next()) {
				leave = new Leave();
				leave.setLid(rset.getLong("lid"));
				leave.setSid(rset.getLong("sid"));
				leave.setTid(rset.getLong("tid"));
				leave.setLapplytime(rset.getDate("lapplytime"));
				leave.setLbegintime(rset.getDate("lbegintime"));
				leave.setLendtime(rset.getDate("lendtime"));
				leave.setLreason(rset.getString("lreason"));
				leave.setLapprovestatus(rset.getString("lapprovestatus"));
				leave.setLfeedback(rset.getString("lfeedback"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return leave;
	}

	/**
	 * 根据教师ID查询还有几条未处理的请假记录
	 */
	public int getNotDealLeaveByTid(Serializable tid) {
		// oracle数据库的select查询，where的条件判读是从右往左执行的，所以我们要把可以过滤更多无用数据的条件放在后面
		ResultSet rset = BasicDaoJDBC
				.executeQuery(
						"select count(*) as count from leave_leave where tid=? and lapprovestatus=?",
						new Object[] { tid, "0" });
		int count = 0;
		try {
			if (rset.next()) {
				count = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			BasicDaoJDBC
					.close(rset, BasicDaoJDBC.getPs(), BasicDaoJDBC.getCt());
		}

		return count;
	}

	/**
	 * 查询请假详细信息（表关联查询）
	 */
	public LeaveDetail getLeaveDetailById(long lid) {
		String sql = "select b.lid lid,a.sno sno,a.sname sname,b.lapplytime lapplytime,b.lbegintime lbegintime,b.lendtime lendtime,b.lreason lreason,b.lapprovestatus lapprovestatus,b.lfeedback lfeedback from leave_leave b,leave_student a where a.sid = b.sid and b.lid=?";
		// 执行查询
		ResultSet rset = BasicDaoJDBC.executeQuery(sql, new Object[] { lid });
		LeaveDetail leaveDetail = null;
		try {

			if (rset.next()) {
				leaveDetail = new LeaveDetail();
				leaveDetail.setLid(rset.getLong("lid"));
				leaveDetail.setSno(rset.getString("sno"));
				leaveDetail.setSname(rset.getString("sname"));
				leaveDetail.setLapplytime(rset.getDate("lapplytime"));
				leaveDetail.setLbegintime(rset.getDate("lbegintime"));
				leaveDetail.setLendtime(rset.getDate("lendtime"));
				leaveDetail.setLreason(rset.getString("lreason"));
				leaveDetail.setLapprovestatus(rset.getString("lapprovestatus"));
				leaveDetail.setLfeekback(rset.getString("lfeedback"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}
		return leaveDetail;
	}
}
