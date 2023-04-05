package util.mapper.impl;

import model.Note;
import util.mapper.IMapper;

public class NoteMapper implements IMapper<Note,String> {

    @Override
    public String toInput(Note note) {
        return String.format("%s, %s, %s", note.getId(), note.getHeader(), note.getText());
    }

    @Override
    public Note toOutput(String t) {
        String[] lines = t.split(", ");
        long id;
        if (isDigit(lines[0])) {
            id = Long.parseLong(lines[0]);
            return new Note(id, lines[1], lines[2]);
        }
        throw new NumberFormatException("Id must be a large number");
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
