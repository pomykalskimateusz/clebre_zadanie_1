package pl.pomykalskimateusz.repository;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import pl.pomykalskimateusz.dto.NoteDto;

import java.util.UUID;

@Data
@Builder
public class Note
{
    @PrimaryKey
    private UUID noteId;
    private String title;
    private String content;
    private String author;
    private Long timestamp;

    public static Note fromDto(NoteDto dto)
    {
        return Note
                .builder()
                .noteId(Uuids.timeBased())
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
