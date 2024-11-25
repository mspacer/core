package com.core.collection;

/**
 * <p> Базовый интерфейс коллекций. Наследники List, Set, Queue
 * <p> Map - отдельная базовая коллекция</p>
 * <p> Доступ по индексу (get(i)) есть только у List
 * <p> Методы:
 * <p> boolean add (E item): добавляет в коллекцию объект item. При удачном добавлении возвращает true, при неудачном - false
 * <p> boolean addAll (Collection<? extends E> col): добавляет в коллекцию все элементы из коллекции col. При удачном добавлении возвращает true, при неудачном - false
 * <p> void clear (): удаляет все элементы из коллекции
 * <p> boolean contains (Object item): возвращает true, если объект item содержится в коллекции, иначе возвращает false
 * <p> boolean containsAll(Collection<?> c);	Проверяет – есть ли в коллекции элементы из с
 * <p> boolean isEmpty (): возвращает true, если коллекция пуста, иначе возвращает false
 * <p> Iterator<E> iterator (): возвращает объект Iterator для обхода элементов коллекции
 * <p> boolean remove (Object item): возвращает true, если объект item удачно удален из коллекции, иначе возвращается false
 * <p> boolean removeIf(Predicate<? super E> filter) — удаляет по условию (Collection);
 * <p> boolean removeAll (Collection<?> col): удаляет все объекты коллекции col из текущей коллекции. Если текущая коллекция изменилась, возвращает true, иначе возвращается false
 * <p> boolean retainAll (Collection<?> col): удаляет все объекты из текущей коллекции, кроме тех, которые содержатся в коллекции col. Если текущая коллекция после удаления изменилась, возвращает true, иначе возвращается false
 * <p> int size (): возвращает число элементов в коллекции
 * <p> Object[] toArray (): возвращает массив, содержащий все элементы коллекции
 * <p> T[] toArray(T[] a): аналог toArray() с разницей, что данные копируются в массив а и он же возвращается. Если розмера недостаточно - создается новый.
 * <p> Stream<E> stream(), parallelStream() - возвращает поток Stream (parallel c возможностью параллельной работы операций)
 * <p> Spliterator<E> spliterator()
 * <p>
 * <p> Iterator - базовый интерфейс итераторов
 * <p> Методы:
 * <p> boolean hasNext() Возвращает true, если есть ещё элементы. В противном случае возвращает false.
 * <p> Object next() Возвращает следующий элемент. Вызывает исключение NoSuchElementException, если не существует следующего элемента.
 * <p> void remove() Удаляет текущий элемент. Выбрасывает IllegalStateException, если делается попытка вызвать remove(), которому не предшествует вызов next().
 */
public class CollectionInfo {
}
