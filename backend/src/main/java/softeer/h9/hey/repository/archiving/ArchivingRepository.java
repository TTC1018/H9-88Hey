package softeer.h9.hey.repository.archiving;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.Archiving;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;

@Repository
@RequiredArgsConstructor
public class ArchivingRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<Archiving> findArchivingIdByCondition(final ArchivingRequest request) {
		int modelId = request.getModelId();
		int limit = request.getLimit();
		int offset = request.getOffset();
		List<String> selectOptions = request.getSelectOptions();

		String sql = "SELECT archiving.id, archiving.is_purchase, archiving.created_at, archiving.review, archiving.car_normal_types_id\n"
			+ "FROM archiving, carNormalTypes, trim, model "
			+ "WHERE archiving.id IN "
			+ "      (SELECT archiving_selectOption.archiving_id "
			+ "       FROM archiving_selectOption "
			+ "       WHERE archiving_selectOption.select_option_id IN (:selectOptions) "
			+ "       GROUP BY archiving_selectOption.archiving_id "
			+ "       HAVING COUNT(DISTINCT archiving_selectOption.select_option_id) = :count) "
			+ "AND archiving.car_normal_types_id = carNormalTypes.id "
			+ "AND carNormalTypes.trim_id = trim.id "
			+ "AND model_id = (:modelId)"
			+ "ORDER BY archiving.id DESC "
			+ "LIMIT :limit OFFSET :offset";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("selectOptions", selectOptions)
			.addValue("count", selectOptions.size())
			.addValue("modelId", modelId)
			.addValue("limit", limit)
			.addValue("offset", calcOffset(limit, offset));

		return jdbcTemplate.query(sql, params, archivingRowMapper());
	}

	private int calcOffset(final int limit, final int offset) {
		return (offset - 1) * limit;
	}

	private RowMapper<Archiving> archivingRowMapper() {
		return BeanPropertyRowMapper.newInstance(Archiving.class);
	}
}
