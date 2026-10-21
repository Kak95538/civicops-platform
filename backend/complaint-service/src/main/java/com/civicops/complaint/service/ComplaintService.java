
package com.civicops.complaint.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civicops.complaint.dto.ComplaintResponse;
import com.civicops.complaint.dto.CreateComplaintRequest;
import com.civicops.complaint.entity.Complaint;
import com.civicops.complaint.entity.ComplaintStatus;
import com.civicops.complaint.repository.ComplaintRepository;

@Service
public class ComplaintService {

	private final ComplaintRepository complaintRepository;

	public ComplaintService(ComplaintRepository complaintRepository) {
		this.complaintRepository = complaintRepository;
	}

	@Transactional
	public ComplaintResponse createComplaint(CreateComplaintRequest request, String citizenEmail) {

		Complaint complaint = new Complaint();

		complaint.setTitle(request.getTitle().trim());
		complaint.setDescription(request.getDescription().trim());
		complaint.setCategory(request.getCategory().trim().toUpperCase());
		complaint.setLocation(request.getLocation().trim());

		// Set trusted ownership from the authenticated user.
		complaint.setCitizenEmail(citizenEmail);
		complaint.setStatus(ComplaintStatus.SUBMITTED);

		Complaint savedComplaint = complaintRepository.save(complaint);

		return toResponse(savedComplaint);
	}

	private ComplaintResponse toResponse(Complaint complaint) {
		return new ComplaintResponse(complaint.getId(), complaint.getTitle(), complaint.getDescription(),
				complaint.getCategory(), complaint.getLocation(), complaint.getStatus(), complaint.getCreatedAt());
	}

	@Transactional(readOnly = true)
	public List<ComplaintResponse> getMyComplaints(String citizenEmail) {

		return complaintRepository.findByCitizenEmail(citizenEmail).stream().map(this::toResponse)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ComplaintResponse getMyComplaintById(Long id, String citizenEmail) {

		Optional<Complaint> complaintOptional = complaintRepository.findByIdAndCitizenEmail(id, citizenEmail);

		Complaint complaint = complaintOptional
				.orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
						org.springframework.http.HttpStatus.NOT_FOUND, "Complaint not found"));

		return toResponse(complaint);
	}

	@Transactional
	public ComplaintResponse updateComplaintStatus(Long id, ComplaintStatus newStatus) {

		Complaint complaint = complaintRepository.findById(id)
				.orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
						org.springframework.http.HttpStatus.NOT_FOUND, "Complaint not found"));

		complaint.setStatus(newStatus);

		Complaint updatedComplaint = complaintRepository.save(complaint);

		return toResponse(updatedComplaint);
	}

}
