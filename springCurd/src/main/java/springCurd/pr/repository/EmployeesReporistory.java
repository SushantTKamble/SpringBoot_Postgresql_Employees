package springCurd.pr.repository;

 
import org.springframework.stereotype.Repository;

import springCurd.pr.model.Employee;


@Repository
public interface EmployeesReporistory extends org.springframework.data.jpa.repository.JpaRepository<Employee,Long>{
	
	
	

}
