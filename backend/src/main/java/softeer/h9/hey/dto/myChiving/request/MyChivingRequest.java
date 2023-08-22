package softeer.h9.hey.dto.myChiving.request;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MyChivingRequest {
	private int offset;
	private int limit;
}
