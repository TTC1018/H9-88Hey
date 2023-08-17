package softeer.h9.hey.repository.car;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.f4b6a3.tsid.TsidCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MyChivingRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Transactional
	public Long saveMyCarToMyChiving(final Long id, final Integer engineId, final Integer trimId,
		final Integer bodyTypeId, final Integer wheelTypeId, final Integer exteriorColorId,
		final Integer interiorColorId, final List<String> selectOptionIdList, final boolean isSubmit) {

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
			.addValue("userId",1)
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
		if(selectOptionIdList == null || selectOptionIdList.isEmpty()) {
			return;
		}

		String selectOptionsString = selectOptionIdList.stream()
			.map(selectOptionId -> "(\'" + selectOptionId + "\', " + myChivingId + " )")
			.collect(Collectors.joining(", "));

		String sql = "INSERT INTO myArchiving_selectOption (select_option_id, myArchiving_id)"
			+ "VALUES " + selectOptionsString;

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource();

		namedParameterJdbcTemplate.update(sql,sqlParameterSource);
	}
}
