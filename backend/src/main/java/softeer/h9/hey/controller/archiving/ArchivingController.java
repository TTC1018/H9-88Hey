package softeer.h9.hey.controller.archiving;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;
import softeer.h9.hey.dto.archiving.response.ArchivingDetailResponse;
import softeer.h9.hey.dto.archiving.response.ArchivingResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.archiving.ArchivingService;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingController {

	private final ArchivingService archivingService;

	@GetMapping("/archiving")
	public GlobalResponse<ArchivingResponse> findArchivingByModelIdAndOptions(final ArchivingRequest request) {
		if (request.getSelectOptions() == null) {
			request.setSelectOptions(new ArrayList<>());
		}
		return GlobalResponse.ok(archivingService.getArchivingFeeds(request));
	}

	@GetMapping("/archiving/{feed_id}")
	public GlobalResponse<ArchivingDetailResponse> findArchivingDetailById(
		@PathVariable(value = "feed_id") final Long feedId) {
		return GlobalResponse.ok(archivingService.getArchivingDetail(feedId));
	}
}
