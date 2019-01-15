package com.xtwy.leave.dao.basic;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.xtwy.leave.exception.DataAccessException;

public class BasicDaoJDBC {

	// 定义变量
	private static Connection ct = null;
	// 大多数情况下用preparedstatement替代statement
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	// 连接数据库的参数
	private static String url = "";
	private static String username = "";
	private static String driver = "";
	private static String passwd = "";

	private static CallableStatement cs = null;

	public static CallableStatement getCs() {
		return cs;
	}

	private static Properties pp = null;
	private static InputStream fis = null;
	// 加载驱动，只需要一次，用静态代码块
	static {
		try {
			// 从dbinfo.properties
			pp = new Properties();
			fis = BasicDaoJDBC.class.getClassLoader().getResourceAsStream(
					"database.properties");
			// fis = new FileInputStream();
			pp.load(fis);
			driver = pp.getProperty("driver");
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			passwd = pp.getProperty("password");

			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
			fis = null;// 垃圾回收站上收拾
		}

	}

	/**
	 * 得到连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}
		return ct;
	}

	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	// *************callPro1存储过程函数1*************
	// public static CallableStatement callPro1(String sql, Object[] parameters)
	// {
	// try {
	// ct = getConnection();
	// cs = ct.prepareCall(sql);
	// if (parameters != null) {
	// for (int i = 0; i < parameters.length; i++) {
	// cs.setObject(i + 1, parameters[i]);
	// }
	// }
	// cs.execute();
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new DataAccessException(e.getMessage());
	// } finally {
	// close(rs, cs, ct);
	// }
	// return cs;
	// }

	// *******************callpro2存储过程2************************
	// public static CallableStatement callPro2(String sql, Object[]
	// inparameters,
	// Integer[] outparameters) {
	// try {
	// ct = getConnection();
	// cs = ct.prepareCall(sql);
	// if (inparameters != null) {
	// for (int i = 0; i < inparameters.length; i++) {
	// cs.setObject(i + 1, inparameters[i]);
	// }
	// }
	// // cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
	// if (outparameters != null) {
	// for (int i = 0; i < outparameters.length; i++) {
	// cs.registerOutParameter(inparameters.length + 1 + i,
	// outparameters[i]);
	// }
	// }
	// cs.execute();
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new DataAccessException(e.getMessage());
	// } finally {
	//
	// }
	// return cs;
	// }

	/**
	 * 统一的哈讯语句
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static ResultSet executeQuery(String sql, Object[] parameters) {
		try {
			// 连接数据库
			ct = getConnection();
			// 准备sql语句与相应的参数
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}
			// 执行查询
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			// close(rs, ps, ct);
		}
		return rs;
	}

	// public static void executeUpdate2(String[] sql, Object[][] parameters) {
	// try {
	// ct = getConnection();
	// ct.setAutoCommit(false);
	//
	// for (int i = 0; i < sql.length; i++) {
	//
	// if (null != parameters[i]) {
	// ps = ct.prepareStatement(sql[i]);
	// for (int j = 0; j < parameters[i].length; j++) {
	// ps.setObject(j + 1, parameters[i][j]);
	// }
	// ps.executeUpdate();
	// }
	//
	// }
	//
	// ct.commit();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// try {
	// ct.rollback();
	// } catch (SQLException e1) {
	// e1.printStackTrace();
	// throw new DataAccessException(e.getMessage());
	// }
	// throw new DataAccessException(e.getMessage());
	// } finally {
	// close(rs, ps, ct);
	// }
	//
	// }

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param parameters
	 * @param begin
	 * @param end
	 * @return
	 */
	public static ResultSet executeQueryByPage(String sql, Object[] parameters,
			int pageNum, int pageSize) {
		// 定义begin和end 第四页 ，每页10条 1-10 11-20 21-30 31-40
		Integer begin = (pageNum - 1) * pageSize + 1;
		Integer end = pageNum * pageSize;

		try {
			// 连接数据库
			ct = getConnection();
			// 准备sql语句与相应的参数
			String pagesql = "select * from (select a1.*,rownum rn from ("
					+ sql + ") a1 where rownum <= ?) where rn >= ?";
			ps = ct.prepareStatement(pagesql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}
			ps.setObject(parameters.length + 1, end);
			ps.setObject(parameters.length + 2, begin);
			// 执行查询
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			// close(rs, ps, ct);
		}
		return rs;
	}

	/**
	 * 统一的数据更新方法
	 * 
	 * @param sql
	 * @param parameters
	 */
	public static void executeUpdate(String sql, Object[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}

			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();// 开发阶段
			throw new DataAccessException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
	}

	/**
	 * 关闭相关资源
	 * 
	 * @param rs
	 * @param ps
	 * @param ct
	 */
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		// 关闭资源(先开后关)
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}
		if (null != ct) {
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ct = null;
		}
	}
}
