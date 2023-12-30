package com.carrenting.employee.ports.out;

import com.carrenting.employee.ports.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount
    Optional<Employee> findByEmailAndPassword(String email, String password);

    //Rest ueber REST API
}
