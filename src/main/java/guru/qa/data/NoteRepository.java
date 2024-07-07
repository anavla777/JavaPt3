package guru.qa.data;

import guru.qa.domain.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface NoteRepository {

    List<Note> findAllByUsername(String username);

    void save(Note note);

    class MockNoteRepository implements NoteRepository {

        private List<Note> store = new ArrayList<>(
                List.of(
                        new Note("dima", "Выучи автоматизацию наконец!"),
                        new Note("dima", "Начни меньше работать!")
                )
        );

        @Override
        public List<Note> findAllByUsername(String username) {
            if ("dima".equals(username)) {
                return store;
            }
            return Collections.emptyList();
        }

        @Override
        public void save(Note note) {
            if ("dima".equals(note.username())) {
                store.add(note);
            }
        }
    }
}
