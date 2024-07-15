package com.example.demo.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TraceabilityEntry")

public class TraceabilityEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String dataPath;  // Le chemin de la donnée

    @Column(nullable = false)
    private String userAccessed;  // L'utilisateur qui a accédé à la donnée

    @Column(nullable = false)
    private Instant accessTimestamp;  // Le timestamp de l'accès

    @Column(nullable = false)
    private String accessedAction;  // L'action effectuée sur la donnée (lecture, modification, suppression, etc.)

    @Column(nullable = false)
    private String accessedResult;  // Le résultat de l'action sur la donnée (succès, échec, etc.)

    @Column
    private String accessedDetails;  // Des détails supplémentaires sur l'accès

    // Nouveaux attributs pour répondre aux besoins spécifiques
    @Column
    private String modificationType;  // Suivi des modifications (ajout, mise à jour, suppression)

    @Column
    private String dataLineage;  // Linéage des données

    @Column
    private String temporalTraceability;  // Traçabilité temporelle

    @Column
    private String complianceAudit;  // Audit de conformité

    @Column
    private String failurePoints;  // Identification des points de défaillance

    @Column
    private String sensitiveDataTraceability;  // Traçabilité des données sensibles

    @Column
    private String interactionVisualization;  // Visualisation des interactions

    @Column
    private String automaticAlerts;  // Alertes automatiques

    @Column
    private String logRetention;  // Conservation des logs
}
