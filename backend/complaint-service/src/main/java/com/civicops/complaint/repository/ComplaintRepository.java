
package com.civicops.complaint.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civicops.complaint.entity.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	List<Complaint> findByCitizenEmail(String citizenEmail);
	Optional<Complaint> findByIdAndCitizenEmail(
	        Long id,
	        String citizenEmail
	);
	
	
}
