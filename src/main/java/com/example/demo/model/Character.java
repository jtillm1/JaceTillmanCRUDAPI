package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "character_aliases", joinColumns = @JoinColumn(name = "character_id"))
    @Column(name = "alias")
    private List<String> alias;

    private String species;
    private String origin;
    private String height;

    @Column(name = "threat_level", nullable = false)
    private String threatLevel;

    private Long bounty;

    @Column(name = "last_seen")
    private String lastSeen;

    private String status;

    @ElementCollection
    @CollectionTable(name = "character_abilities", joinColumns = @JoinColumn(name = "character_id"))
    @Column(name = "ability")
    private List<String> abilities;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getAlias() { return alias; }
    public void setAlias(List<String> alias) { this.alias = alias; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }
    public String getThreatLevel() { return threatLevel; }
    public void setThreatLevel(String threatLevel) { this.threatLevel = threatLevel; }
    public Long getBounty() { return bounty; }
    public void setBounty(Long bounty) { this.bounty = bounty; }
    public String getLastSeen() { return lastSeen; }
    public void setLastSeen(String lastSeen) { this.lastSeen = lastSeen; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}