package mx.com.germanvr.service;

import java.sql.SQLException;
import java.util.List;

import mx.com.germanvr.beans.CustomerBean;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public interface ICustomerService {

	public CustomerBean getCustomerById(Long teamId);

	public List<CustomerBean> getAllCustomers();

	public void saveCustomer(final CustomerBean customerBean) throws SQLException;

	public CustomerBean updateCustomer(final CustomerBean customerBean, final Long id) throws SQLException;

	public void deleteCustomer(final Long id);
}
