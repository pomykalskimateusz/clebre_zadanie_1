package pl.pomykalskimateusz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.dto.NoteDto;
import pl.pomykalskimateusz.repository.Note;
import pl.pomykalskimateusz.repository.NoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService
{
    private final NoteRepository noteRepository;

    public Note create(NoteDto noteDto)
    {
        return noteRepository.save(Note.fromDto(noteDto));
    }

    public boolean delete(UUID noteId)
    {
        try
        {
            noteRepository.deleteById(noteId);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    public List<Note> findAll()
    {
        return noteRepository.findAll();
    }

    public Optional<Note> update(UUID noteId, NoteDto noteDto)
    {
        return noteRepository
                .findById(noteId)
                .map(note -> updateNote(note, noteDto))
                .map(noteRepository::save);
    }

    private Note updateNote(Note note, NoteDto noteDto)
    {
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setAuthor(noteDto.getAuthor());
        return note;
    }
}
