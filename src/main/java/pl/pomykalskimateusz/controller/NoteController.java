package pl.pomykalskimateusz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pomykalskimateusz.dto.NoteDto;
import pl.pomykalskimateusz.repository.Note;
import pl.pomykalskimateusz.service.NoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NoteController
{
    private final NoteService noteService;

    @GetMapping("/notes")
    public List<Note> findAllDiets()
    {
        return noteService.findAll();
    }

    @PostMapping("/notes")
    public Note saveNote(@RequestBody NoteDto noteDto)
    {
        return noteService.create(noteDto);
    }

    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity deleteNote(@PathVariable UUID noteId)
    {
        boolean result = noteService.delete(noteId);
        if(result)
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/notes/{noteId}")
    public ResponseEntity updateNote(@PathVariable UUID noteId, @RequestBody NoteDto noteDto)
    {
        return noteService
                .update(noteId, noteDto)
                .map(note -> new ResponseEntity(note, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
