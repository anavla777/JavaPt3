package guru.qa.db.impl;

import guru.qa.db.DataBaseNotesRepository;
import guru.qa.db.DataSourceProvider;
import guru.qa.db.NoteRowMapper;
import guru.qa.domain.Note;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostgresNotesRepository implements DataBaseNotesRepository {
    private static final JdbcTemplate template =
            new JdbcTemplate(DataSourceProvider.INSTANCE.getDataSource());

    @Override
    public List<Note> getAllNotes() {
        return template.query("SELECT * FROM notes", new NoteRowMapper());
    }

    @Override
    public List<Note> getNotesByUsername(String noteName) {
        return template.query("SELECT * FROM notes WHERE username = ?", new NoteRowMapper(), noteName);
    }

    @Override
    public void saveNote(Note note) {
        template.update(
                "INSERT INTO notes (username, text) VALUES (?, ?)",
                note.username(), note.toString()
        );
    }
}
