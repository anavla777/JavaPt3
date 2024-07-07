package guru.qa.ui;

import guru.qa.data.NoteRepository;
import guru.qa.db.DataBaseNotesRepository;
import guru.qa.db.impl.PostgresNotesRepository;
import guru.qa.domain.Note;
import guru.qa.domain.User;
import guru.qa.service.Session;

import javax.swing.*;
import java.util.List;

public class MainUIComponent implements UIComponent {

    static DataBaseNotesRepository dbRepository = new PostgresNotesRepository();
    private final NoteRepository noteRepository;

    public MainUIComponent(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Session render(Session session) {
        final User user = session.unwrap();
        List<Note> currentNotesInFile = noteRepository.findAllByUsername(user.username());
        List<Note> currentNotesFromDb = dbRepository.getNotesByUsername(user.username());
        renderNotes(currentNotesInFile);
        renderNotes(currentNotesFromDb);
        final String newNoteText = JOptionPane.showInputDialog("New note: ");
        Note note = new Note()
                .setUserName(user.username())
                .setText(newNoteText);
        dbRepository.saveNote(note);
        renderNotes(currentNotesFromDb);
        noteRepository.save(
                new Note(
                        user.username(),
                        newNoteText
                )
        );
        renderNotes(currentNotesInFile);
        int flag = getConfirmation();
        if (flag == 0) {
            return render(session);
        } else {
            System.exit(0);
        }
        return session;
    }

    private int getConfirmation() {
        return JOptionPane.showConfirmDialog(
                null,
                "Continue?",
                "Confirmation",
                JOptionPane.OK_CANCEL_OPTION
        );
    }

    private void renderNotes(List<Note> notes) {
        JOptionPane.showMessageDialog(
                null,
                notes,
                "Current notes:",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
