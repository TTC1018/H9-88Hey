package softeer.h9.hey.repository.car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.Category;
import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.domain.car.SubOption;

@Repository
@RequiredArgsConstructor
public class SelectOptionRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<SelectOption> findSelectOptionsByCarCode(String carCode) {
		return findSelectOptions(carCode, Category.SELECT_OPTION);
	}

	public List<SelectOption> findNPerformanceByCarCode(String carCode) {
		return findSelectOptions(carCode, Category.N_PERFORMANCE);
	}

	private List<SelectOption> findSelectOptions(String carCode, Category category) {
		String sql =
			"select selectOption.id, selectOption.category,selectOption.name, selectOption.image_url, "
				+ "selectOption.additional_price, \n"
				+ "       subOption.id, subOption.name, subOption.image_url, subOption.description\n"
				+ "from selectOption_carTrims\n"
				+ "         left join selectOption on selectOption.id = selectOption_carTrims.select_option_id\n"
				+ "         left join selectOption_subOption map on selectOption.id = map.select_option_id\n"
				+ "         left join subOption on map.sub_option_id = subOption.id\n"
				+ "where car_normal_types_id = :carCode\n"
				+ "   AND category = \"" + category.getName() + "\"\n;";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode);

		return fetchSelectOptionWithSubOptions(sql, params);
	}

	private List<SelectOption> fetchSelectOptionWithSubOptions(String sql, SqlParameterSource params) {

		HashMap<String, SelectOption> selectOptionMap = new HashMap<>();
		namedParameterJdbcTemplate.query(sql, params, rs -> {
			String selectOptionId = rs.getString("id");
			SelectOption selectOption = selectOptionMap.getOrDefault(
				selectOptionId,
				mapSelectOption(rs)
			);
			SubOption subOption = mapSubOption(rs);
			selectOption.addSubOption(subOption);

			selectOptionMap.putIfAbsent(selectOptionId, selectOption);
		});

		return new ArrayList<>(selectOptionMap.values());
	}

	private SubOption mapSubOption(ResultSet rs) throws SQLException {
		SubOption subOption = new SubOption();
		subOption.setId(rs.getString("subOption.id"));
		subOption.setName(rs.getString("subOption.name"));
		subOption.setImageUrl(rs.getString("subOption.image_url"));
		subOption.setDescription(rs.getString("subOption.description"));
		return subOption;
	}

	private SelectOption mapSelectOption(ResultSet rs) throws SQLException {
		SelectOption selectOption = new SelectOption();
		selectOption.setId(rs.getString("selectOption.id"));
		selectOption.setCategory(Category.findByName(rs.getString("selectOption.category")));
		selectOption.setName(rs.getString("selectOption.name"));
		selectOption.setImageUrl(rs.getString("selectOption.image_url"));
		selectOption.setAdditionalPrice(rs.getInt("selectOption.additional_price"));
		return selectOption;
	}
}
