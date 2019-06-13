package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreDao extends BaseDao {
	public List<Score> search() {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from v_emp_dep_pro_sc";
			rs = stat.executeQuery(sql);
			// ·â×°¶ÔÏó
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("scId"));
				sc.setValue(rs.getDouble("value"));
				sc.setGrade(rs.getString("grade"));
				Employee emp = new Employee();
				sc.setEmp(emp);
				emp.setId(rs.getInt("empId"));
				emp.setName(rs.getString("empName"));

				Department dep = new Department();
				dep.setId(rs.getInt("depId"));
				dep.setName(rs.getString("depName"));
				emp.setDep(dep);

				Project pro = new Project();
				pro.setId(rs.getInt("proId"));
				pro.setName(rs.getString("proName"));
				sc.setPro(pro);
				list.add(sc);
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

	public boolean add(Connection conn, PreparedStatement pstat, Score sc) throws SQLException {
		int rs = 0;
		String sql = "insert into score (e_id,p_id,value) values(?,?,?)";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1, sc.getEmp().getId());
		pstat.setInt(2, sc.getPro().getId());
		pstat.setDouble(3, sc.getValue());
		rs = pstat.executeUpdate();
		return rs > 0;
	}

	public boolean update(Connection conn, PreparedStatement pstat, Score sc) throws SQLException {
		int rs = 0;
		String sql = "update score set value=? where id =?";
		pstat = conn.prepareStatement(sql);
		pstat.setDouble(1, sc.getValue());
		pstat.setInt(2, sc.getId());
		rs = pstat.executeUpdate();
		return rs > 0;
	}

	public void save(Set<Score> saveScores) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			for (Score sc : saveScores) {
				if (sc.getId() == 0) {
					add(conn, pstat, sc);
				} else {
					update(conn, pstat, sc);
				}
			}
			conn.commit();
		} catch (ClassNotFoundException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
	}
}
