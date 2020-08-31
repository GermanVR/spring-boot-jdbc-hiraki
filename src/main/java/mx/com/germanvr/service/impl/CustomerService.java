package mx.com.germanvr.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.germanvr.beans.CustomerBean;
import mx.com.germanvr.exceptions.CustomerNotFoundException;
import mx.com.germanvr.repository.ICrudRepository;
import mx.com.germanvr.service.ICustomerService;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
@Service
@Slf4j
public class CustomerService implements ICustomerService {

	@Autowired
	private ICrudRepository<CustomerBean, Long> customerRepository;

	@Override
	public CustomerBean getCustomerById(Long teamId) {
		Optional<CustomerBean> customer = customerRepository.findById(teamId);
		log.info("{}", customer);
		return customer.isPresent() ? customer.get() : new CustomerBean();
	}

	@Override
	public List<CustomerBean> getAllCustomers() {
		List<CustomerBean> players = customerRepository.findAll();
		players.stream().forEach(a -> log.info("{}", a));
		return players;
	}

	/*
	 * Verificar la transaccion para saveCustomer con una excepcion throw new
	 * RuntimeException("Quise Tronar");
	 */

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void saveCustomer(CustomerBean customerBean) throws SQLException {
		customerRepository.save(customerBean);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public CustomerBean updateCustomer(CustomerBean customerBean, Long id) throws SQLException {
		Optional<CustomerBean> customerFindBean = customerRepository.findById(id);
		if (customerFindBean.isPresent()) {
			customerRepository.update(customerBean, id);
		} else {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		return customerBean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void deleteCustomer(Long id) {
		customerRepository.delete(id);
	}

}
