package com.cst438.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository <Assignment, Integer> {
	
	@Query("select a from Assignment a where a.id = :id")
	Assignment findById(int id);

	@Query("select a from Assignment a where a.needsGrading=1 and a.dueDate < current_date and a.course.instructor= :email order by a.id")
	List<Assignment> findNeedGradingByEmail(String email);
}
