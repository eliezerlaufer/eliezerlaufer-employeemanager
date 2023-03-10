package tech.getarrays.employeemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.model.Employee;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /*
    * With name convention spring is able to understand this language and than create query for us.
    * */
    void deleteEmployeeById(Long id);

    Optional<Employee> findEmployeeById(Long id);
}
