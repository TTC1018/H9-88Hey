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
import softeer.h9.hey.domain.archiving.ArchivingResult;
import softeer.h9.hey.domain.archiving.Feed;

@Repository
@RequiredArgsConstructor
public class ArchivingRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<Archiving> findArchivingIdByCondition(final int modelId, final int limit, final int offset,
		final List<String> selectOptions) {

		String sql = initializeSql(selectOptions);

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("selectOptions", selectOptions)
			.addValue("count", selectOptions.size())
			.addValue("modelId", modelId)
			.addValue("limit", limit)
			.addValue("offset", calcOffset(limit, offset));

		return jdbcTemplate.query(sql, params, archivingRowMapper());
	}

	public List<ArchivingResult> findDetailByFeedId(final long feedId) {
		String sql = "SELECT archiving.id                     AS feed_id,\n"
			+ "       model.name                       AS model_name,\n"
			+ "       archiving.review,\n"
			+ "       archiving.is_purchase,\n"
			+ "       archiving.created_at             AS creation_date,\n"
			+ "       trim.id                          AS trim_id,\n"
			+ "       trim.name                        AS trim_name,\n"
			+ "       trim.price                       AS trim_price,\n"
			+ "       engine.id                        AS engine_id,\n"
			+ "       engine.name                      AS engine_name,\n"
			+ "       engine.additional_price          AS engine_price,\n"
			+ "       bodyType.id                      AS body_type_id,\n"
			+ "       bodyType.name                    AS body_type_name,\n"
			+ "       bodyType.additional_price        AS body_type_price,\n"
			+ "       wheelType.id                     AS wheel_drive_id,\n"
			+ "       wheelType.name                   AS wheel_drive_name,\n"
			+ "       wheelType.additional_price       AS wheel_drive_price,\n"
			+ "       exteriorColor.id                 AS exterior_color_id,\n"
			+ "       exteriorColor.name               AS exterior_color_name,\n"
			+ "       exteriorColor.exterior_image_url AS exterior_color_car_image_url,\n"
			+ "       exteriorColor.color_image_url    AS exterior_color_color_image_url,\n"
			+ "       exteriorColor.additional_price   AS exterior_color_additional_price,\n"
			+ "       interiorColor.id                 AS interior_color_id,\n"
			+ "       interiorColor.name               AS interior_color_name,\n"
			+ "       interiorColor.color_image_url    AS interior_color_color_image_url,\n"
			+ "       selectOption.id                  AS select_option_id,\n"
			+ "       selectOption.name                AS select_option_name,\n"
			+ "       selectOption.image_url           AS select_option_image_url,\n"
			+ "       selectOption.additional_price    AS select_option_additional_price,\n"
			+ "       selectOption.category            AS select_option_category,\n"
			+ "       archiving_selectOption.review    AS select_option_review,\n"
			+ "       subOption.name                   AS sub_option_name\n"
			+ "\n"
			+ "FROM model\n"
			+ "         LEFT JOIN trim ON model.id = trim.model_id\n"
			+ "         LEFT JOIN carNormalTypes ON trim.id = carNormalTypes.trim_id\n"
			+ "         LEFT JOIN engine ON carNormalTypes.engine_id = engine.id\n"
			+ "         LEFT JOIN bodyType ON carNormalTypes.body_type_id = bodyType.id\n"
			+ "         LEFT JOIN wheelType ON carNormalTypes.wheel_type_id = wheelType.id\n"
			+ "         LEFT JOIN archiving ON carNormalTypes.id = archiving.car_normal_types_id\n"
			+ "         LEFT JOIN archiving_interiorColor ON archiving.id = archiving_interiorColor.archiving_id\n"
			+ "         LEFT JOIN interiorColor ON archiving_interiorColor.interior_color_id = interiorColor.id\n"
			+ "         LEFT JOIN archiving_exteriorColor ON archiving.id = archiving_exteriorColor.archiving_id\n"
			+ "         LEFT JOIN exteriorColor ON archiving_exteriorColor.exterior_color_id = exteriorColor.id\n"
			+ "         LEFT JOIN archiving_selectOption ON archiving.id = archiving_selectOption.archiving_id\n"
			+ "         LEFT JOIN selectOption ON archiving_selectOption.select_option_id = selectOption.id\n"
			+ "         LEFT JOIN selectOption_subOption ON selectOption.id = selectOption_subOption.select_option_id\n"
			+ "         LEFT JOIN subOption ON selectOption_subOption.sub_option_id = subOption.id\n"
			+ "WHERE archiving.id = :feedId";

		SqlParameterSource params = new MapSqlParameterSource().addValue("feedId", feedId);

		return jdbcTemplate.query(sql, params, archivingResultRowMapper());
	}

	public List<Feed> findArchivingsByUserIdWithPagination(final int userId, final int limit, final int offset) {
		String sql = "SELECT DISTINCT archiving_id\n AS feed_id "
			+ "FROM archiving\n"
			+ "LEFT JOIN feed ON archiving.id = feed.archiving_id\n"
			+ "LEFT JOIN user ON feed.user_id = user.id\n"
			+ "WHERE user.id = :userId\n"
			+ "LIMIT :limit OFFSET :offset";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("limit", limit)
			.addValue("offset", calcOffset(limit, offset))
			.addValue("userId", userId);

		return jdbcTemplate.query(sql, params, archivingIdRowMapper());
	}

	private RowMapper<Feed> archivingIdRowMapper() {
		return BeanPropertyRowMapper.newInstance(Feed.class);
	}

	private RowMapper<ArchivingResult> archivingResultRowMapper() {
		return BeanPropertyRowMapper.newInstance(ArchivingResult.class);
	}

	private String initializeSql(final List<String> selectOptions) {
		if (selectOptions.isEmpty()) {
			return
				"SELECT archiving.id,\n"
					+ "       model.name AS model_name,\n"
					+ "       archiving.is_purchase,\n"
					+ "       archiving.created_at,\n"
					+ "       archiving.review,\n"
					+ "       archiving.car_normal_types_id\n"
					+ "FROM archiving\n"
					+ "         LEFT JOIN carNormalTypes ON archiving.car_normal_types_id = carNormalTypes.id\n"
					+ "         LEFT JOIN trim ON carNormalTypes.trim_id = trim.id\n"
					+ "         LEFT JOIN model ON trim.model_id = model.id\n"
					+ "WHERE model_id = (:modelId)\n"
					+ "ORDER BY archiving.id DESC\n"
					+ "LIMIT :limit OFFSET :offset";
		}
		return
			"SELECT archiving.id,\n"
				+ "       model.name AS model_name,\n"
				+ "       archiving.is_purchase,\n"
				+ "       archiving.created_at,\n"
				+ "       archiving.review,\n"
				+ "       archiving.car_normal_types_id\n"
				+ "FROM model\n"
				+ "         INNER JOIN trim ON model.id = trim.model_id\n"
				+ "         INNER JOIN carNormalTypes ON trim.id = carNormalTypes.trim_id\n"
				+ "         INNER JOIN archiving ON carNormalTypes.id = archiving.car_normal_types_id\n"
				+ "WHERE model_id = (:modelId)\n"
				+ "  AND archiving.id IN\n"
				+ "      (SELECT archiving_selectOption.archiving_id\n"
				+ "       FROM archiving_selectOption\n"
				+ "       WHERE archiving_selectOption.select_option_id IN (:selectOptions)\n"
				+ "       GROUP BY archiving_selectOption.archiving_id\n"
				+ "       HAVING COUNT(DISTINCT archiving_selectOption.select_option_id) = :count)\n"
				+ "ORDER BY archiving.id DESC\n"
				+ "LIMIT :limit OFFSET :offset";
	}

	private int calcOffset(final int limit, final int offset) {
		return (offset - 1) * limit;
	}

	private RowMapper<Archiving> archivingRowMapper() {
		return BeanPropertyRowMapper.newInstance(Archiving.class);
	}
}
