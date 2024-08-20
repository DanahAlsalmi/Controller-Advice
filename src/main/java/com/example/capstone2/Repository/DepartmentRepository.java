package com.example.capstone2.Repository;

import com.example.capstone2.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select d from Department d where d.id=?1")
    Department findDepartmentById(Integer id);
}
