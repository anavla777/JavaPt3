package guru.qa.db;

import guru.qa.domain.Note;

import java.util.List;

public interface DataBaseNotesRepository {
    List<Note> getAllNotes();

    List<Note> getNotesByUsername(String noteName);

    void saveNote(Note note);
}
