package softeer.h9.hey.dto.archiving.request;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingRequest {

	private final int modelId;
	private final int offset;
	private final int limit;
	private final List<String> selectOptions;

	public static ArchivingRequest of(final int modelId, final int offset, final int limit,
		final List<String> selectOptions) {
		return new ArchivingRequest(modelId, offset, limit, selectOptions);
	}
}
