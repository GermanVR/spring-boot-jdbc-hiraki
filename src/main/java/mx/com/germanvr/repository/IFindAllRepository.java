package mx.com.germanvr.repository;

import java.util.List;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public interface IFindAllRepository<T> {

	public List<T> findAll();
}
