package softeer.h9.hey.dto.archiving.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchivingRequest {

	private int modelId;
	private int offset;
	private int limit;
	private List<String> selectOptions;

	private ArchivingRequest(int model_id, int offset, int limit, List<String> select_option) {
		this.modelId = model_id;
		this.offset = offset;
		this.limit = limit;
		this.selectOptions = select_option;
	}

	public static ArchivingRequest of(final int modelId, final int offset, final int limit,
		final List<String> selectOptions) {
		return new ArchivingRequest(modelId, offset, limit, selectOptions);
	}
}
