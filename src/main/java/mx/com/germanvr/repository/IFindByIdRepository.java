package mx.com.germanvr.repository;

import java.util.Optional;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public interface IFindByIdRepository<T, D> {

	public Optional<T> findById(final D id);
}
