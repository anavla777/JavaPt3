package guru.qa.data;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import guru.qa.domain.Note;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class FileNotesRepository implements NoteRepository {

    private final Path path;

    public FileNotesRepository(Path path) {
        this.path = path;
    }

    @Override
    public List<Note> findAllByUsername(String username) {
        try (InputStream is = Files.newInputStream(path);
             CSVReader reader = new CSVReader(
                     new InputStreamReader(is)
             )) {
            return reader.readAll().stream()
                    .filter(line -> line[0].equals(username))
                    .map(line -> new Note(line[0], line[1]))
                    .toList();

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Note note) {
        try {
            OutputStream os = Files.newOutputStream(path, APPEND);
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(os), ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(new String[]{note.username(), note.toString()});
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
