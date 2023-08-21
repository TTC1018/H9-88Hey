package softeer.h9.hey.repository.myChiving;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.f4b6a3.tsid.TsidCreator;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.BodyTypeDto;
import softeer.h9.hey.dto.archiving.EngineDto;
import softeer.h9.hey.dto.archiving.ExteriorColorDto;
import softeer.h9.hey.dto.archiving.InteriorColorDto;
import softeer.h9.hey.dto.archiving.TrimDto;
import softeer.h9.hey.dto.archiving.WheelDriveDto;
import softeer.h9.hey.dto.myChiving.ModelDto;
import softeer.h9.hey.dto.myChiving.MyChivingDto;
import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionFetchDto;

@RequiredArgsConstructor
@Repository
public class MyChivingRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Transactional
	public Long saveMyCarToMyChiving(final MyChivingSaveDto myChivingSaveDto) {
		Long id = myChivingSaveDto.getId();
		Integer userId = myChivingSaveDto.getUserId();
		Integer engineId = myChivingSaveDto.getEngineId();
		Integer trimId = myChivingSaveDto.getTrimId();
		Integer bodyTypeId = myChivingSaveDto.getBodyTypeId();
		Integer wheelTypeId = myChivingSaveDto.getWheelTypeId();
		Integer exteriorColorId = myChivingSaveDto.getExteriorColorId();
		Integer interiorColorId = myChivingSaveDto.getInteriorColorId();
		List<String> selectOptionIdList = myChivingSaveDto.getSelectOptionIdList();
		boolean isSubmit = myChivingSaveDto.isSubmitted();

		//myArchiving update용 sql 선언
		StringBuilder sqlBuilder = new StringBuilder();
		MapSqlParameterSource myArchivingParams = new MapSqlParameterSource();

		//현재 시간 구하기
		LocalDateTime currentDateTime = LocalDateTime.now();

		//tsId, 쿼리 생성
		Long myChivingId = buildMyChivingSqlAndGetRecordId(id, sqlBuilder);

		//파라미터 초기화
		myArchivingParams
			.addValue("id", myChivingId)
			.addValue("userId", userId)
			.addValue("engineId", engineId)
			.addValue("trimId", trimId)
			.addValue("bodyTypeId", bodyTypeId)
			.addValue("wheelTypeId", wheelTypeId)
			.addValue("exteriorColorId", exteriorColorId)
			.addValue("interiorColorId", interiorColorId)
			.addValue("isSubmit", isSubmit)
			.addValue("lastModified", currentDateTime);

		//마이카이빙 먼저 집어넣고 선택옵션 기존꺼 삭제하고 선택옵션 새로 집어넣기
		insertMyChiving(sqlBuilder.toString(), myArchivingParams);
		deleteSelectOption(myChivingId);
		insertSelectOption(myChivingId, selectOptionIdList);

		return myChivingId;
	}

	@Transactional(readOnly = true)
	public List<MyChivingDto> findMyChivingsByUserIdLimitAndOffset(Integer userId, Integer limit, Integer startIndex) {
		List<MyChivingDto> myChivingDtoList = new ArrayList<>();

		String sql = buildMyChivingSelectQuery();

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
			.addValue("userId", userId)
			.addValue("limit", limit)
			.addValue("startIndex", startIndex);

		namedParameterJdbcTemplate.query(sql, sqlParameterSource, rs -> {
			MyChivingDto myChivingDto = getNewMyChivingDto(rs);
			myChivingDtoList.add(myChivingDto);
		});

		return myChivingDtoList;
	}

	public List<MyChivingSelectOptionFetchDto> findSelectOptionIdByMyChivingIdList(List<Long> myChivingIdList) {
		String selectOptionsString = myChivingIdList.stream()
			.map(id -> "\'" + id + "\'")
			.collect(Collectors.joining(", "));

		String sql = "SELECT \n"
			+ "ms.select_option_id, \n"
			+ "seo.name as select_option_name,\n"
			+ "seo.image_url,\n"
			+ "seo.additional_price,\n"
			+ "ms.myArchiving_id as my_chiving_id, \n"
			+ "suo.name as sub_option_name \n"
			+ "FROM 88hey.myArchiving_selectOption ms \n"
			+ "left join selectOption_subOption ss on ms.select_option_id  = ss.select_option_id\n"
			+ "left join selectOption seo  on seo.id = ms.select_option_id\n"
			+ "left join subOption suo on ss.sub_option_id = suo.id  \n"
			+ "where ms.myArchiving_id in ( "
			+ 	selectOptionsString
			+ ")";


		return namedParameterJdbcTemplate.query(sql, myChivingSelectOptionRowMapper());
	}

	private MyChivingDto getNewMyChivingDto(ResultSet rs) throws SQLException {
		return MyChivingDto.builder()
			.myChivingId(rs.getLong("id"))
			.lastModifiedDate(rs.getObject("last_modified", LocalDateTime.class))
			.isSaved(rs.getBoolean("is_submitted"))
			.modelDto(ModelDto.of(rs.getObject("model_id", Integer.class), rs.getString("model_name")))
			.trim(TrimDto.of(rs.getObject("trim_id", Integer.class), rs.getString("trim_name"), rs.getObject("trim_price", Integer.class)))
			.engine(EngineDto.of(rs.getObject("engine_id", Integer.class), rs.getString("engine_name"),
				rs.getObject("engine_additional_price", Integer.class)))
			.bodyType(BodyTypeDto.of(rs.getObject("body_type_id", Integer.class), rs.getString("body_type_name"),
				rs.getObject("body_type_additional_price", Integer.class)))
			.wheelDrive(WheelDriveDto.of(rs.getObject("wheel_type_id", Integer.class), rs.getString("wheel_type_name"),
				rs.getObject("wheel_type_additional_price", Integer.class)))
			.interiorColor(InteriorColorDto.of(rs.getObject("interior_color_id", Integer.class), rs.getString("interior_color_name"),
				rs.getString("interior_color_image_url")))
			.exteriorColor(ExteriorColorDto.of(rs.getObject("exterior_color_id", Integer.class), rs.getString("exterior_color_name"),
				rs.getString("exterior_car_image_url"), rs.getString("exterior_color_image_url"),
				rs.getObject("exterior_color_additional_price", Integer.class)))
			.build();
	}

	private String buildMyChivingSelectQuery() {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select\n")
			.append("\tma.*,")
			.append("    it.name as interior_color_name,")
			.append("    it.color_image_url as interior_color_image_url,")
			.append("    it.additional_price as interior_color_additional_price,")
			.append("    et.name as exterior_color_name,")
			.append("    et.exterior_image_url as exterior_car_image_url,")
			.append("    et.color_image_url as exterior_color_image_url,")
			.append("    et.additional_price as exterior_color_additional_price,")
			.append("    bt.name as body_type_name,")
			.append("    bt.additional_price as body_type_additional_price,")
			.append("    wt.name as wheel_type_name,")
			.append("    wt.additional_price as wheel_type_additional_price,")
			.append("    tr.name as trim_name,")
			.append("    tr.price as trim_price,")
			.append("    eg.name as engine_name,")
			.append("    eg.additional_price as engine_additional_price,")
			.append("    md.id as model_id,")
			.append("    md.name as model_name")
			.append(" from\n")
			.append("\tmyArchiving ma\n")
			.append("left join\n")
			.append("\tinteriorColor it on it.id = ma.interior_color_id\n")
			.append("left join\n")
			.append("\texteriorColor et on et.id = ma.exterior_color_id\n")
			.append("left join\n")
			.append("\tbodyType bt on bt.id = ma.body_type_id\n")
			.append("left join\n")
			.append("\twheelType wt on wt.id = ma.wheel_type_id\n")
			.append("left join\n")
			.append("\ttrim tr on tr.id = ma.trim_id\n")
			.append("left join\n")
			.append("\tengine eg on eg.id = ma.engine_id\n")
			.append("left join\n")
			.append("\tmodel md on md.id = tr.model_id\n")
			.append("where\n")
			.append("\tma.user_id = :userId\n")
			.append("order by\n")
			.append("\tma.is_submitted,\n")
			.append("\tma.last_modified desc\n")
			.append("limit :limit\n")
			.append("offset :startIndex");

		return queryBuilder.toString();
	}

	//마이카이빙 테이블 쿼리 작성
	private Long buildMyChivingSqlAndGetRecordId(Long id, StringBuilder sqlBuilder) {
		//신규 삽입
		if (id == null) {
			long tsId = TsidCreator.getTsid().toLong();
			buildMyChivingInsertQuery(sqlBuilder);
			return tsId;
		}

		//덮어쓰기
		buildMyChivingUpdateQuery(sqlBuilder);
		return id;
	}

	//삽입 쿼리
	private void buildMyChivingInsertQuery(StringBuilder sqlBuilder) {
		sqlBuilder
			.append("INSERT INTO myArchiving ")
			.append(
				"(id , user_id, engine_id, trim_id, body_type_id, wheel_type_id, exterior_color_id, interior_color_id, is_submitted, last_modified) ")
			.append(
				"VALUES (:id, :userId , :engineId , :trimId, :bodyTypeId, :wheelTypeId , :exteriorColorId , :interiorColorId , :isSubmit , :lastModified);");
	}

	//덮어쓰기 쿼리
	private void buildMyChivingUpdateQuery(StringBuilder sqlBuilder) {
		sqlBuilder
			.append("UPDATE myArchiving SET ")
			.append("engine_id = :engineId, ")
			.append("trim_id = :trimId, ")
			.append("body_type_id = :bodyTypeId, ")
			.append("wheel_type_id = :wheelTypeId, ")
			.append("exterior_color_id = :exteriorColorId, ")
			.append("interior_color_id = :interiorColorId, ")
			.append("is_submitted = :isSubmit, ")
			.append("last_modified = :lastModified ")
			.append("WHERE id = :id;");
	}

	private void insertMyChiving(String sql, SqlParameterSource sqlParameterSource) {
		namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}

	private void deleteSelectOption(Long myChivingId) {
		String sql = "DELETE FROM myArchiving_selectOption WHERE myArchiving_id = :myChiving_id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("myChiving_id", myChivingId);

		namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}

	private void insertSelectOption(Long myChivingId, List<String> selectOptionIdList) {
		if (selectOptionIdList == null || selectOptionIdList.isEmpty()) {
			return;
		}

		String selectOptionsString = selectOptionIdList.stream()
			.map(selectOptionId -> "(\'" + selectOptionId + "\', " + myChivingId + " )")
			.collect(Collectors.joining(", "));

		String sql = "INSERT INTO myArchiving_selectOption (select_option_id, myArchiving_id)"
			+ "VALUES " + selectOptionsString;

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource();

		namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}

	private RowMapper<MyChivingSelectOptionFetchDto> myChivingSelectOptionRowMapper() {
		return BeanPropertyRowMapper.newInstance(MyChivingSelectOptionFetchDto.class);
	}
}
