package controller;

import java.util.List;
import java.util.Objects;

import log.ILogger;
import log.impl.ConsoleLogger;
import model.Note;
import repository.GBRepository;

public class Controller {
    private final GBRepository<Note, Long> repository;
    private ILogger logger;

    public Controller(GBRepository<Note, Long> repository, ILogger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    public void saveNote(Note note) {
        repository.create(note);
        logger.log("The new note is created");
    }

    public Note readNote(Long NoteId) throws Exception {
        List<Note> Notes = repository.readAll();
        for (Note note : Notes) {
            if (Objects.equals(note.getId(), NoteId)) {
                logger.log("The note is read: " + note.getId());
                return note;
            }
        }
        
        throw new RuntimeException("User not found");
    }

    public void updateNote(String noteId, Note update) {
        update.setId(Long.parseLong(noteId));
        repository.update(Long.parseLong(noteId), update);
        logger.log("The note is updated: " + noteId);
    }

    public void deleteNote(String userId) {
        repository.delete(Long.parseLong(userId));
        logger.log("The note is deleted: " + userId);
    }
}
