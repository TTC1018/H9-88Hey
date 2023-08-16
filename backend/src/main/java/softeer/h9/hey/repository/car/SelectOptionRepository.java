package softeer.h9.hey.repository.car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.domain.car.SelectOptionCategory;
import softeer.h9.hey.domain.car.SubOption;
import softeer.h9.hey.dto.car.DisabledOptionIdDto;
import softeer.h9.hey.dto.car.SelectOptionByModelDto;

@Repository
@RequiredArgsConstructor
public class SelectOptionRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<SelectOptionByModelDto> findAllSelectOptionByModelId(final int modelId) {
		String sql = "SELECT id, name, category FROM selectOption";

		List<SelectOption> selectOptions;

		selectOptions = namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
				SelectOption selectOption = new SelectOption();
				selectOption.setId(rs.getString("id"));
				selectOption.setName(rs.getString("name"));
				selectOption.setSelectOptionCategory(SelectOptionCategory.findByName(rs.getString("category")));
				return selectOption;
			}
		);

		return mapToSelectOptionByModelDtos(selectOptions);
	}

	private List<SelectOptionByModelDto> mapToSelectOptionByModelDtos(List<SelectOption> selectOptions) {
		List<SelectOptionByModelDto> selectOptionByModelDtos = new ArrayList<>();
		for (SelectOption selectOption : selectOptions) {
			selectOptionByModelDtos.add(SelectOptionByModelDto.of(selectOption.getId(), selectOption.getName(),
				selectOption.getSelectOptionCategory().getName()));
		}
		return selectOptionByModelDtos;
	}

	public List<SelectOption> findSelectOptionsByCarCode(String carCode) {
		return findSelectOptions(carCode, SelectOptionCategory.SELECT_OPTION);
	}

	public List<SelectOption> findNPerformanceByCarCode(String carCode) {
		return findSelectOptions(carCode, SelectOptionCategory.N_PERFORMANCE);
	}

	public List<SelectOption> findHGenuineAccessoriesByCarCode(final String carCode) {
		return findSelectOptions(carCode, SelectOptionCategory.H_GENUINE);
	}

	public List<DisabledOptionIdDto> findDisabledOptionIdsBySelectOptionIds(final List<String> selectOptionIds) {
		if (selectOptionIds == null) {
			return null;
		}

		String selectOptionsString = selectOptionIds.stream()
			.map(id -> "\'" + id + "\'")
			.collect(Collectors.joining(", "));

		String sql = "SELECT DISTINCT disabled_option_id "
			+ "FROM disabledOption "
			+ "where selected_option_id in ( "
			+ selectOptionsString
			+ ")";

		List<DisabledOptionIdDto> disabledOptionIdDtos = new ArrayList<>();
		namedParameterJdbcTemplate.query(sql, result -> {
			disabledOptionIdDtos.add(DisabledOptionIdDto.of(result.getString("disabled_option_id")));
		});

		return disabledOptionIdDtos;
	}

	private List<SelectOption> findSelectOptions(String carCode, SelectOptionCategory selectOptionCategory) {
		String sql =
			"select selectOption.id, selectOption.category,selectOption.name, selectOption.image_url, "
				+ "selectOption.additional_price, \n"
				+ "       subOption.id, subOption.name, subOption.image_url, subOption.description\n"
				+ "from selectOption_carTrims\n"
				+ "         left join selectOption on selectOption.id = selectOption_carTrims.select_option_id\n"
				+ "         left join selectOption_subOption map on selectOption.id = map.select_option_id\n"
				+ "         left join subOption on map.sub_option_id = subOption.id\n"
				+ "where car_normal_types_id = :carCode\n"
				+ "   AND category = :category ;";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode)
			.addValue("category", selectOptionCategory.getName());

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
		selectOption.setSelectOptionCategory(SelectOptionCategory.findByName(rs.getString("selectOption.category")));
		selectOption.setName(rs.getString("selectOption.name"));
		selectOption.setImageUrl(rs.getString("selectOption.image_url"));
		selectOption.setAdditionalPrice(rs.getInt("selectOption.additional_price"));
		return selectOption;
	}

	private SelectOption mapSelectNormalOption(ResultSet rs) throws SQLException {
		SelectOption selectOption = new SelectOption();
		selectOption.setId(rs.getString("id"));
		selectOption.setSelectOptionCategory(SelectOptionCategory.findByName(rs.getString("category")));
		selectOption.setName(rs.getString("name"));
		return selectOption;
	}
}
