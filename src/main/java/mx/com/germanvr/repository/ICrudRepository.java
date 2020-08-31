package mx.com.germanvr.repository;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
public interface ICrudRepository<T, D> extends IDeleteRepository<D>, IFindByIdRepository<T, D>, ISaveRepository<T>, IFindAllRepository<T>, IUpdateRepository<T, D> {

}
