
package com.civicops.complaint.dto;

import com.civicops.complaint.entity.ComplaintStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateComplaintStatusRequest {

    @NotNull(message = "Complaint status is required")
    private ComplaintStatus status;

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }
}
