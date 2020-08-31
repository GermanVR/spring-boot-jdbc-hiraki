package mx.com.germanvr.repository.impl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.com.germanvr.beans.CustomerBean;
import mx.com.germanvr.repository.ICrudRepository;
import mx.com.germanvr.rowmapper.CustomerRowMapper;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
@Component
@Slf4j
public class CustomerRepository implements ICrudRepository<CustomerBean, Long> {

	private static final String NUMBER_OF_ROWS_AFFECTED = "Number of rows affected: {}";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbc;

	private static final String SELECT = "SELECT * FROM CUSTOMER";
	private static final String DELETE = "DELETE FROM CUSTOMER WHERE id=:id";
	private static final String INSERT = "INSERT INTO CUSTOMER(NAME,AGE) VALUES(:name, :age)";
	private static final String UPDATE = "UPDATE CUSTOMER SET NAME = :name, AGE= :age WHERE id=:id";
	private static final String SELECT_WHERE_ID = "SELECT * FROM CUSTOMER WHERE id = :id";

	@Override
	public CustomerBean save(final CustomerBean customer) throws SQLException {
		final SqlParameterSource paramSource = new BeanPropertySqlParameterSource(customer);
		final int saved = namedParameterJdbc.update(INSERT, paramSource);
		log.info(NUMBER_OF_ROWS_AFFECTED, saved);
		return customer;
	}

	/*
	 * Forma Tradicional para findById de Obtener un recurso, cuando, no encuentra, o encuentra mas de 1
	 * Optional<CustomerBean> customerBean = Optional.empty(); try {
	 * customerBean = Optional.of(namedParameterJdbc.queryForObject(SELECT_WHERE_ID,
	 * mapValues, new CustomerRowMapper())); } catch
	 * (IncorrectResultSizeDataAccessException e) {
	 * log.warn("Cant Get Resource: {}", e.getMessage()); }
	 */
	@Override
	public Optional<CustomerBean> findById(final Long id) {
		Map<String, Object> mapValues = new LinkedHashMap<>();
		mapValues.put("id", id);

		return namedParameterJdbc.query(SELECT_WHERE_ID, mapValues, new CustomerRowMapper()).stream().findFirst();
	}

	@Override
	public List<CustomerBean> findAll() {
		return namedParameterJdbc.query(SELECT, (rs, rowNum) -> {
			CustomerBean bean = new CustomerBean();
			bean.setId(rs.getLong("ID"));
			bean.setName(rs.getString("NAME"));
			bean.setAge(rs.getInt("AGE"));
			return bean;
		});
	}

	@Override
	public void delete(final Long id) {
		int deleted = namedParameterJdbc.update(DELETE, new MapSqlParameterSource().addValue("id", id));
		log.info(NUMBER_OF_ROWS_AFFECTED, deleted);
	}

	@Override
	public CustomerBean update(CustomerBean customer, final Long id) throws SQLException {
		Map<String, Object> values = new LinkedHashMap<>();
		values.put("id", id);
		values.put("name", customer.getName());
		values.put("age", customer.getAge());
		SqlParameterSource paramSource = new MapSqlParameterSource(values);
		int updated = namedParameterJdbc.update(UPDATE, paramSource);
		log.info(NUMBER_OF_ROWS_AFFECTED, updated);
		return customer;
	}

}
