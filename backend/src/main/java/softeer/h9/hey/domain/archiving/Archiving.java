package softeer.h9.hey.domain.archiving;

import java.sql.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Archiving {
	private Long id;
	private Boolean isPurchase;
	private Date createdAt;
	private String review;
	public String carNormalTypesId;
}
