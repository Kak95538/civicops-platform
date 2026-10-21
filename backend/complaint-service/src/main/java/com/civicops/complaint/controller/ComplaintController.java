
package com.civicops.complaint.controller;

import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civicops.complaint.dto.ComplaintResponse;
import com.civicops.complaint.dto.CreateComplaintRequest;
import com.civicops.complaint.dto.UpdateComplaintStatusRequest;
import com.civicops.complaint.service.ComplaintService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

	private final ComplaintService complaintService;

	public ComplaintController(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	@PostMapping
	public ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody CreateComplaintRequest request,
			Authentication authentication) {

		String citizenEmail = authentication.getName();

		ComplaintResponse response = complaintService.createComplaint(request, citizenEmail);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ComplaintResponse>> getMyComplaints(Authentication authentication) {

		String citizenEmail = authentication.getName();

		List<ComplaintResponse> complaints = complaintService.getMyComplaints(citizenEmail);

		return ResponseEntity.ok(complaints);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComplaintResponse> getMyComplaintById(@PathVariable Long id, Authentication authentication) {

		String citizenEmail = authentication.getName();

		ComplaintResponse complaint = complaintService.getMyComplaintById(id, citizenEmail);

		return ResponseEntity.ok(complaint);
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<ComplaintResponse> updateComplaintStatus(@PathVariable Long id,
			@Valid @RequestBody UpdateComplaintStatusRequest request) {

		ComplaintResponse response = complaintService.updateComplaintStatus(id, request.getStatus());

		return ResponseEntity.ok(response);
	}

}
