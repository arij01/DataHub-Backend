package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // Action performed (e.g., "CREATE", "UPDATE", "DELETE")
    private String entityName; // Name of the entity affected
    private Long entityId; // ID of the entity affected
    private int userid; // Username of the user performing the action (if applicable)
    private String ipAddress; // IP address of the user
    private String httpMethod; // HTTP method used for the action
    private String requestUri; // Request URI
    private LocalDateTime timestamp; // Timestamp of when the action occurred
}
