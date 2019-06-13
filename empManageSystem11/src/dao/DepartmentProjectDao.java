package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class DepartmentProjectDao extends BaseDao {
	public List<Project> searchByDep(int depId) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Project> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from v_dep_pro where depId=" + depId;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("proId"));
				pro.setName(rs.getString("proName"));
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

	public List<Project> searchByNoDep(Integer depId) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Project> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from project where id not in(select proId from v_dep_pro where depId=" + depId + ")";
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

	public boolean add(int depId, int proId) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rs = 0;
		List<Project> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "insert into m_dep_pro values(?,?)";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, depId);
			stat.setInt(2, proId);
			rs = stat.executeUpdate();

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

	public boolean delete(int depId, int proId) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rs = 0;
		List<Project> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "delete from m_dep_pro where d_id =? and p_id=?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, depId);
			stat.setInt(2, proId);
			rs = stat.executeUpdate();
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
}
