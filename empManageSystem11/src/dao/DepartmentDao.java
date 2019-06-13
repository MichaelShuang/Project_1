package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao extends BaseDao {
	public List<Department> search() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from department";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpNum(rs.getInt("empnums"));
				list.add(dep);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public Department search(int id) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Department dep = new Department();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from department where id =" + id;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpNum(rs.getInt("empnums"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return dep;
	}

	public List<Department> search(Department condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from department where 1=1";
			if (!condition.getName().equals("")) {
				sql += " and name like '%" + condition.getName() + "%'";
			}
			if (condition.getEmpNum() != -1) {
				if (condition.getEmpNum() == 0) {
					sql += " and (empNums=0 or empNums is null)";
				} else {
					sql += " and empNums=" + condition.getEmpNum();
				}
			}
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpNum(rs.getInt("empnums"));
				list.add(dep);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Department dep) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "insert into department (name) values('" + dep.getName() + "')";
			rs = stat.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}

	public boolean update(Department dep) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "update department set name = '" + dep.getName() + "' where id = " + dep.getId();
			rs = stat.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}

	public boolean delete(int id) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			String sql = "delete from department where id=" + id;
			rs = stat.executeUpdate(sql);
			sql = "update employee set d_id = null where d_id=" + id;
			rs = stat.executeUpdate(sql);
			conn.commit();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}
}
