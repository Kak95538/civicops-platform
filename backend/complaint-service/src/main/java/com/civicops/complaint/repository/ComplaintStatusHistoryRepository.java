
package com.civicops.complaint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civicops.complaint.entity.ComplaintStatusHistory;

public interface ComplaintStatusHistoryRepository
        extends JpaRepository<ComplaintStatusHistory, Long> {

    List<ComplaintStatusHistory> findByComplaintIdOrderByChangedAtDesc(
            Long complaintId);
}
