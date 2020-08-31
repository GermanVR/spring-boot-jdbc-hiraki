package mx.com.germanvr.repository;

import java.sql.SQLException;

/**
 * @Autor Luis German Vazquez Renteria
 *
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public interface ISaveRepository<T> {

	public T save(final T entity) throws SQLException;

}
