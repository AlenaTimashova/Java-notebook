package repository.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Note;
import repository.GBRepository;
import util.mapper.impl.NoteMapper;

import static util.DBConnector.DB_PATH;

public class NoteRepository implements GBRepository<Note, Long> {
    private final NoteMapper mapper;
    

    public NoteRepository() {
        this.mapper = new NoteMapper();
       
    }


    @Override
    public List<Note> readAll() {
        List<String> lines = new ArrayList<>();
        List<Note> notes = new ArrayList<>();

        try {
            File file = new File(DB_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            if (line != null) lines.add(line);

            while (line != null) {
                line = reader.readLine();

                if (line != null) lines.add(line);
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line: lines) notes.add(mapper.toOutput(line));

        return notes;
    }
        

    @Override
    public Note create(Note e) {
        List<Note> notes = readAll();
        long max = 0L;
        for (Note n : notes) {
            long id = n.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        e.setId(next);
        notes.add(e);
        List<String> lines = new ArrayList<>();
        for (Note n: notes) {
            lines.add(mapper.toInput(n));
        }
        saveAll(lines);
        return e;
    }
    
    @Override
    public Optional<Note> update(Long id, Note update) {
        List<Note> notes = readAll();
        Note editNote = notes.stream()
                .filter(u -> u.getId()
                        .equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Note not found"));
        editNote.setHeader(update.getHeader());
        editNote.setText(update.getText());
        write(notes);
        return Optional.of(update);
    }

    @Override
    public boolean delete(Long id) {
        List<Note> notes = readAll();
        Note editNote = notes.stream()
                .filter(u -> u.getId()
                        .equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Note not found"));
        notes.remove(editNote);
        System.out.println("The note is deleted");
        write(notes);
        return true;
    }


    @Override
    public void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(DB_PATH, false)) {
            for (String line : data) {
                
                writer.write(line);
                
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void write(List<Note> notes) {
        List<String> lines = new ArrayList<>();
        for (Note u: notes) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }
}
