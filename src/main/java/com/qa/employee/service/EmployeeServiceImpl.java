package com.qa.employee.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.employee.entity.Employee;
import com.qa.employee.exception.EmployeeAlreadyExistsException;
import com.qa.employee.exception.EmployeeNotFoundException;
import com.qa.employee.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	/*
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	} */

	@Override
	public List<Employee> getAllEmployees() {
		
		return this.empRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {		
		
		 Optional<Employee> findByIdOptional = this.empRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new EmployeeNotFoundException();
		return findByIdOptional.get();
	}

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
		/*
		 * Check if employee is already present in the list  if yes
		 * throw EmployeeAlreadyExistsException
		 * 
		 */
		 Optional<Employee> findByIdOptional = this.empRepository.findById(employee.getId());
		 if(findByIdOptional.isPresent())
			 throw new EmployeeAlreadyExistsException();
		 else
			 return this.empRepository.save(employee);
		
	}

}
