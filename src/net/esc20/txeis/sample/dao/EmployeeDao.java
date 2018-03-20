package net.esc20.txeis.sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.esc20.txeis.sample.domains.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao extends NamedParameterJdbcDaoSupport{

	@Autowired
	public EmployeeDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	
	public Employee getEmployeeByNbr(String empNbr) {
		String sql = "select emp_nbr, name_f, name_l, name_m, race_white, dob, marital_stat, sex from bhr_emp_demo where emp_nbr = :empNbr";
		Employee emp = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("empNbr", empNbr);
		
		try {
			emp = this.getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, new EmployeeMapper());
		} catch (EmptyResultDataAccessException e) {
			emp = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			this.getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return emp;
	}
	
	static class EmployeeMapper implements ParameterizedRowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int row) throws SQLException {
			Employee emp = new Employee();
			
			emp.setEmp_nbr(rs.getString(1));
			emp.setName_f(rs.getString(2));
			emp.setName_l(rs.getString(3));
			emp.setName_m(rs.getString(4));
			emp.setRace_white(rs.getString(5).equals("1") ? true : false);
			try {
				emp.setDob(new SimpleDateFormat("yyyyMMdd").parse(rs.getString(6)));
			} catch (ParseException e) {
				emp.setDob(null);
			}
			emp.setMarital_stat(rs.getString(7));
			emp.setSex(rs.getString(8));
			
			return emp;
		}
		
	}
	
	
	public void saveEmployee(Employee employee) throws Exception {
		
		String sql = "update bhr_emp_demo set name_f = :nameF, name_m = :nameM, name_l = :nameL, race_white = :race, dob = :dob, marital_stat = :marital, sex = :sex where emp_nbr = :empNbr ";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("empNbr", employee.getEmp_nbr());
		paramMap.put("nameF", employee.getName_f());
		paramMap.put("nameM", employee.getName_m());
		paramMap.put("nameL", employee.getName_l());
		paramMap.put("race", employee.getRace_white() ? "1" : "0");
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		paramMap.put("dob", df.format(employee.getDob()));
		paramMap.put("marital", employee.getMarital_stat().substring(0, 1));
		paramMap.put("sex", employee.getSex());
		
		int rc = this.getNamedParameterJdbcTemplate().update(sql, paramMap);
		
		//if (rc == 1) {
		//	throw new Exception("Employee Update Succeeded.");
		//}
		
		if (rc != 1) {
			throw new Exception("Employee Update failed.");
		}
		
	}
	
	public long countTest() throws Exception {
		String sql = "set rowcount :count select count(*) from BAR_CUSTOMER where cust_name like 'S%' set rowcount 0";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("count", 100);
		long rc = this.getNamedParameterJdbcTemplate().queryForLong(sql, paramMap);
		
		return rc;
	}
	
	
	public List<Employee> getAllEmployees(){
		String sql = "select emp_nbr, name_f, name_l, name_m, race_white, dob, marital_stat, sex from bhr_emp_demo";
		List<Employee> emps = new ArrayList<Employee>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			emps = this.getNamedParameterJdbcTemplate().query(sql, paramMap, new EmployeeMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emps;
	}
}
