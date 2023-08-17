package softeer.h9.hey.dto.archiving.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.ArchivingDto;

@Getter
@Setter
@NoArgsConstructor
public class ArchivingResponse {
	private Integer nextOffset;
	private List<ArchivingDto> archivings;
}
