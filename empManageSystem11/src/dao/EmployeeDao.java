package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;

public class EmployeeDao extends BaseDao {

	public List<Employee> search() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select e.*,d.name as depName,d.empNums from employee as e left join department as d on e.d_id=d.id";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("depName"));
				dep.setEmpNum(rs.getInt("empNums"));
				emp.setDep(dep);
				list.add(emp);
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

	public Employee search(int id) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Employee emp = new Employee();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from employee where id =" + id;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
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
		return emp;
	}

	public List<Employee> search(Employee condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>();
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select e.*,d.name as depName,d.empNums from employee as e left join department as d on e.d_id=d.id where 1=1";
			if (!condition.getName().equals("")) {
				sql += " and name='" + condition.getName() + "'";
			}
			if (!condition.getSex().equals("")) {
				sql += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				sql += " and age=" + condition.getAge();
			}
			if (condition.getDep().getId() != null) {
				sql += " and d_id =" + condition.getDep().getId();
			}
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("depName"));
				dep.setEmpNum(rs.getInt("empNums"));
				emp.setDep(dep);
				list.add(emp);
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

	public boolean add(Employee emp) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "insert into employee (name,sex,age,d_id) values('" + emp.getName() + "','" + emp.getSex()
					+ "','" + emp.getAge() + "'," + emp.getDep().getId() + ")";
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

	public boolean update(Employee emp) {
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "update employee set name = '" + emp.getName() + "',sex = '" + emp.getSex() + "',age = "
					+ emp.getAge() + ",d_id= " + emp.getDep().getId() + " where id = " + emp.getId();
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
			stat = conn.createStatement();
			String sql = "delete from employee where id=" + id;
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
}
