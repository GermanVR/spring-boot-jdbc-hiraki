package mx.com.germanvr.repository;

import java.sql.SQLException;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public interface IUpdateRepository<T, D> {

	public T update(final T entity, final D key) throws SQLException;

}
