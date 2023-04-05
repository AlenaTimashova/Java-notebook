package repository;

import java.util.List;
import java.util.Optional;

public interface GBRepository<E, I> {
    List<E> readAll();
    E create(E e);
    Optional<E> update(I id, E e);
    boolean delete(I id);
    void saveAll(List<String> data);
}
