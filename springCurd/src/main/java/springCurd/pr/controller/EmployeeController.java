package springCurd.pr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springCurd.pr.exception.ResourceNotFoundException;
import springCurd.pr.model.Employee;
import springCurd.pr.repository.EmployeesReporistory;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeesReporistory employeeRepository;
	
	
	// get employees
	@GetMapping("employees")
	public List<Employee> getAllEmployees()
	{
		return this.employeeRepository.findAll();
	}
	
	
	// get employee by id rest api 
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId)
	throws ResourceNotFoundException
	{
		Employee employee = 
				employeeRepository.findById(employeeId).orElseThrow
				(
						()-> new ResourceNotFoundException("Employee not found for this id : :" +employeeId)
				);
				
				return ResponseEntity.ok().body(employee);
	}
	
	
	// save employees
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return this.employeeRepository.save(employee);
		
	}
	
	
	//update
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		
		Employee employee = employeeRepository.findById(employeeId).
				orElseThrow(()-> new ResourceNotFoundException("Employee Not Found For This Id:  "+employeeId));
						
		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		
		
		return ResponseEntity.ok(this.employeeRepository.save(employee));
		
		
	}
	
	
	//delete
	@DeleteMapping("employees/{id}")
	public Map<String, Boolean>deleteEmployee(@PathVariable(value="id")Long employeeId) throws ResourceNotFoundException{
		
		
		Employee employee = employeeRepository.findById(employeeId).
				orElseThrow(()-> new ResourceNotFoundException("Employee Not Found For This Id:  "+employeeId));
		
		this.employeeRepository.delete(employee);
		
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		
		return response;
	}
	

}
