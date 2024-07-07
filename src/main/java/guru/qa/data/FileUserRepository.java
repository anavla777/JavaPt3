package guru.qa.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import guru.qa.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileUserRepository implements UserRepository {

    private final Path path;

    public FileUserRepository(Path path) {
        this.path = path;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (InputStream is = Files.newInputStream(path);
             CSVReader reader = new CSVReader(
                     new InputStreamReader(is)
             )) {
            Optional<String[]> userLine = reader.readAll().stream()
                    .filter(line -> line[0].equals(username))
                    .findFirst();

            if (userLine.isPresent()) {
                return Optional.of(
                        new User(
                                userLine.get()[0],
                                userLine.get()[1]
                        )
                );
            } else {
                return Optional.empty();
            }

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
