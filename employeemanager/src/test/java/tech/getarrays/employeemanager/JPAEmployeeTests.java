package tech.getarrays.employeemanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JPAEmployeeTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void should_store_a_employee() {
        Employee employee = employeeRepository.save(new Employee("Jon", "jon@companyx.com", "Javascript", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536"));

        assertThat(employee).hasFieldOrPropertyWithValue("name", "Jon");
        assertThat(employee).hasFieldOrPropertyWithValue("email", "jon@companyx.com");
        assertThat(employee).hasFieldOrPropertyWithValue("jobTitle", "Javascript");
        assertThat(employee).hasFieldOrPropertyWithValue("phone", "45702052654");
        assertThat(employee).hasFieldOrPropertyWithValue("imageUrl", "https://bootdey.com/img/Content/avatar/avatar1.png");
        assertThat(employee).hasFieldOrPropertyWithValue("employeeCode", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
    }

    @Test
    public void should_find_all_employees() {
        Employee emp1 = new Employee("Jon", "jon@companyx.com", "Javascript", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp1);

        Employee emp2 = new Employee("Ravi", "ravi@companyx.com", "Java", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp2);

        Employee emp3 = new Employee("Richard", "richard@companyx.com", "Go", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp3);

        Iterable<Employee> employees = employeeRepository.findAll();

        assertThat(employees).hasSize(4).contains(emp1, emp2, emp3);
    }

    @Test
    public void should_find_employee_by_id() {
        Employee emp1 = new Employee("Jon", "jon@companyx.com", "Javascript", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp1);

        Employee emp2 = new Employee("Ravi", "ravi@companyx.com", "Java", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp2);

        Employee foundEmployee = employeeRepository.findById(emp2.getId()).get();

        assertThat(foundEmployee).isEqualTo(emp2);
    }

    @Test
    public void should_update_employee_by_id() {
        Employee emp1 = new Employee("Jon", "jon@companyx.com", "Javascript", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp1);

        Employee emp2 = new Employee("Ravi", "ravi@companyx.com", "Java", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp2);

        Employee updatedEmp = new Employee("updated Ravi", "updateravi@companyx.com", "Java", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");

        Employee emp = employeeRepository.findById(emp2.getId()).get();
        emp.setName(updatedEmp.getName());
        emp.setEmail(updatedEmp.getEmail());
        emp.setJobTitle(updatedEmp.getJobTitle());
        emp.setPhone(updatedEmp.getPhone());
        emp.setImageUrl(updatedEmp.getImageUrl());
        emp.setEmployeeCode(updatedEmp.getEmployeeCode());
        employeeRepository.save(emp);

        Employee checkEmp = employeeRepository.findById(emp2.getId()).get();

        assertThat(checkEmp.getId()).isEqualTo(emp2.getId());
        assertThat(checkEmp.getName()).isEqualTo(updatedEmp.getName());
        assertThat(checkEmp.getEmail()).isEqualTo(updatedEmp.getEmail());
        assertThat(checkEmp.getJobTitle()).isEqualTo(updatedEmp.getJobTitle());
        assertThat(checkEmp.getPhone()).isEqualTo(updatedEmp.getPhone());
        assertThat(checkEmp.getImageUrl()).isEqualTo(updatedEmp.getImageUrl());
        assertThat(checkEmp.getEmployeeCode()).isEqualTo(updatedEmp.getEmployeeCode());
    }

    @Test
    public void should_delete_employee_by_id() {
        Employee emp1 = new Employee("Jon", "jon@companyx.com", "Javascript", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp1);

        Employee emp2 = new Employee("Ravi", "ravi@companyx.com", "Java", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp2);

        Employee emp3 = new Employee("Richard", "richard@companyx.com", "Go", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536");
        entityManager.persist(emp3);

        employeeRepository.deleteById(emp2.getId());

        Iterable employees = employeeRepository.findAll();

        assertThat(employees).hasSize(3).contains(emp1, emp3);
    }

    @Test
    public void should_delete_all_employees() {
        entityManager.persist(new Employee("Jon", "jon@companyx.com", "Javascript", "45702052654", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536"));
        entityManager.persist(new Employee("Ravi", "ravi@companyx.com", "Java", "45704042554", "https://bootdey.com/img/Content/avatar/avatar1.png", "bc1fdb1a-f038-4709-a0f8-f1743678a536"));

        employeeRepository.deleteAll();

        assertThat(employeeRepository.findAll()).isEmpty();
    }
}
