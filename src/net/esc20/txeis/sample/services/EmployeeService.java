package net.esc20.txeis.sample.services;

import java.util.List;

import net.esc20.txeis.sample.dao.EmployeeDao;
import net.esc20.txeis.sample.domains.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Employee getEmployeeByNbr(String empNbr) {
		return employeeDao.getEmployeeByNbr(empNbr);
	}

	public long getCustomerCount() throws Exception {
		return employeeDao.countTest();
	}
	
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}
	
	@Transactional (propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void saveEmployee(Employee employee) throws Exception {
		int j = 0;
		
		for (int i = 0; i < 101; i++) {
			if (i % 10 == 0 ) {
				j++;
			}
			System.err.println("i= " + i + ", j=" + j);
		}
		
		employeeDao.saveEmployee(employee);
	}
}
