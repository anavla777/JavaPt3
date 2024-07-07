package guru.qa.db;

import guru.qa.domain.Note;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteRowMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Note()
                .setId(rs.getObject("id", java.util.UUID.class))
                .setUserName(rs.getString("username"))
                .setText(rs.getString("text"));
    }
}
