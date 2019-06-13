package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ProjectDao extends BaseDao {
	public List<Project> search() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Project> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from project";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
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

	public Project search(int id) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Project pro = new Project();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from project where id =" + id;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
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
		return pro;
	}

	public List<Project> search(Project condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Project> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from project where 1=1";
			if (!condition.getName().equals("")) {
				sql += " and name like '%" + condition.getName() + "%'";
			}
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
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

	public boolean add(Project pro) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "insert into project (name) values('" + pro.getName() + "')";
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

	public boolean update(Project pro) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "update project set name = '" + pro.getName() + "' where id = " + pro.getId();
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
			String sql = "delete from project where id=" + id;
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
