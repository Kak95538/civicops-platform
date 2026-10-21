
package com.civicops.complaint.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AssignComplaintRequest {

    @NotBlank(message = "Officer email is required")
    @Email(message = "Please provide a valid officer email")
    private String officerEmail;

    public String getOfficerEmail() {
        return officerEmail;
    }

    public void setOfficerEmail(String officerEmail) {
        this.officerEmail = officerEmail;
    }
}
