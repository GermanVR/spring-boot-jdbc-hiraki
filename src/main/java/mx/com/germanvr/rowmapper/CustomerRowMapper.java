package mx.com.germanvr.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.germanvr.beans.CustomerBean;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public class CustomerRowMapper implements RowMapper<CustomerBean> {

	@Override
	public CustomerBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerBean bean = new CustomerBean();
		bean.setId(rs.getLong("ID"));
		bean.setName(rs.getString("NAME"));
		bean.setAge(rs.getInt("AGE"));
		return bean;
	}

}
