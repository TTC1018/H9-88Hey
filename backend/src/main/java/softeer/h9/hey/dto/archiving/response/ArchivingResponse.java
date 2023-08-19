package softeer.h9.hey.dto.archiving.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.ArchivingDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingResponse {
	private Integer nextOffset;
	private List<ArchivingDto> archivings;

	public ArchivingResponse of(final int nextOffset, List<ArchivingDto> archivings) {
		return new ArchivingResponse(nextOffset, archivings);
	}
}
