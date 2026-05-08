package com.example.demo.service;

import com.example.demo.model.Character;
import com.example.demo.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public List<Character> getByThreatLevel(String threatLevel) {
        return characterRepository.findByThreatLevel(threatLevel);
    }

    public List<Character> getByStatus(String status) {
        return characterRepository.findByStatus(status);
    }

    public List<Character> searchByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Character> getById(Long id) {
        return characterRepository.findById(id);
    }

    public Character create(Character character) {
        return characterRepository.save(character);
    }

    public Character update(Long id, Character updated) {
        Character existing = characterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));

        existing.setName(updated.getName());
        existing.setAlias(updated.getAlias());
        existing.setSpecies(updated.getSpecies());
        existing.setOrigin(updated.getOrigin());
        existing.setHeight(updated.getHeight());
        existing.setThreatLevel(updated.getThreatLevel());
        existing.setBounty(updated.getBounty());
        existing.setLastSeen(updated.getLastSeen());
        existing.setStatus(updated.getStatus());
        existing.setAbilities(updated.getAbilities());
        existing.setBio(updated.getBio());
        existing.setImageUrl(updated.getImageUrl());

        return characterRepository.save(existing);
    }

    public void delete(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new RuntimeException("Character not found with id: " + id);
        }
        characterRepository.deleteById(id);
    }
}