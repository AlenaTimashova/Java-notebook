package util.mapper;

public interface IMapper <E, T> {
        T toInput(E e);
        E toOutput(T t);
}
