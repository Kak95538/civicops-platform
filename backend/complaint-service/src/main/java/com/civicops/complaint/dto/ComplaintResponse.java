
package com.civicops.complaint.dto;

import java.time.LocalDateTime;

import com.civicops.complaint.entity.ComplaintStatus;

public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String location;
    private ComplaintStatus status;
    private LocalDateTime createdAt;

    public ComplaintResponse(
            Long id,
            String title,
            String description,
            String category,
            String location,
            ComplaintStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
