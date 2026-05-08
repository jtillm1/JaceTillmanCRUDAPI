package com.example.demo.controller;

import com.example.demo.model.Character;
import com.example.demo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "*")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    // GET /api/characters
    // Optional: ?threatLevel=critical  ?status=at-large  ?search=krix
    @GetMapping
    public ResponseEntity<List<Character>> getAll(
            @RequestParam(required = false) String threatLevel,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {

        if (search != null) return ResponseEntity.ok(characterService.searchByName(search));
        if (threatLevel != null) return ResponseEntity.ok(characterService.getByThreatLevel(threatLevel));
        if (status != null) return ResponseEntity.ok(characterService.getByStatus(status));
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    // GET /api/characters/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return characterService.getById(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Character not found with id: " + id)));
    }

    // POST /api/characters
    @PostMapping
    public ResponseEntity<Character> create(@RequestBody Character character) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(characterService.create(character));
    }

    // PUT /api/characters/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Character character) {
        try {
            return ResponseEntity.ok(characterService.update(id, character));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE /api/characters/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            characterService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Character deleted successfully."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }
}