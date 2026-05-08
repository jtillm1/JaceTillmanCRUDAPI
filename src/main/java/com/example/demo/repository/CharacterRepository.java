package com.example.demo.repository;

import com.example.demo.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByThreatLevel(String threatLevel);
    List<Character> findByStatus(String status);
    List<Character> findByNameContainingIgnoreCase(String name);
}