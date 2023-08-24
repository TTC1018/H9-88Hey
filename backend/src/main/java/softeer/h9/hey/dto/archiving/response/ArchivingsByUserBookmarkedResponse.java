package softeer.h9.hey.dto.archiving.response;

import java.util.List;

import lombok.Getter;

@Getter
public class ArchivingsByUserBookmarkedResponse {
	private int nextOffset;
	private List<ArchivingDetailResponse> archivingsByUser;

	private ArchivingsByUserBookmarkedResponse(List<ArchivingDetailResponse> archivingsByUser, int nextOffset) {
		this.archivingsByUser = archivingsByUser;
		this.nextOffset = nextOffset;
	}

	public static ArchivingsByUserBookmarkedResponse of(List<ArchivingDetailResponse> archivingsByUser,
		int nextOffset) {

		return new ArchivingsByUserBookmarkedResponse(archivingsByUser, nextOffset);
	}
}
