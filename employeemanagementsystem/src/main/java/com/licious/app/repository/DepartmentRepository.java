package com.licious.app.repository;

import com.licious.app.model.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>,
        PagingAndSortingRepository<Department, Integer> {

}
