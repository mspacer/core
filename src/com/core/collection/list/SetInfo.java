package com.core.collection.list;

/**
 * <p> Множества
 * <p> Интерфейс Set<E> объявляет поведение коллекции, не допускающей дублирования элементов.
 * Наследует Collection<E> и не добавляет новых методов.
 * <p> Интерфейсы SortedSet наследует Set<E> и объявляет поведение набора, отсортированного в возрастающем порядке, заранее определенном для класса.
 * <p> NavigableSet наследует SortedSet. Существенно облегчает поиск элементов, например, расположенных рядом с заданным.
 * <p> AbstractSet реализует Set и является предком для TreeSet и HashSet. LinkedHashSet расширяет HashSet.
 * <p> TreeSet как упорядоченное множество реализует NavigableSet
 * <p>
 * <p> Методы SortedSet
 * <p> Comparator comparator() Возвращает компаратор вызывающего отсортированного списка. Если для этого набора используется естественный порядок, возвращается null.
 * <p> Object first() Возвращает первый элемент в вызывающем отсортированном наборе.
 * <p> SortedSet headSet(Object end) Возвращает SortedSet, содержаний те элементы, которые меньше, чем end, и которые содержатся в вызывающем отсортированном наборе. Элементы в возвращаемом отсортированном наборе также ссылаются на вызывающий отсортированный набор.
 * <p> Object last() Возвращает последний элемент в вызывающем отсортированном наборе.
 * <p> SortedSet subSet(Object start, Object end) Возвращает SortedSet, который включает эти элементы между start и end.
 *      Элементы возвращенной коллекции также ссылаются на вызывающий объект.
 * <p> SortedSet tailSet(Object start) Возвращает SortedSet, который содержит те элементы, которые больше или равны start,
 *     которые содержатся в отсортированном наборе. Элементы в возвращаемом наборе также ссылаются на вызывающий объект.
 * <p>
 * <p> Методы NavigableSet
 * <p> Е ceiling(E obj) - ищет в наборе наименьший элемент е, для которого истинно е >= obj. Если такой элемент найден, он возвращается.
 *      В противном случае возвращается null.
 * <p> Е floor(Е obj) - ищет в наборе наибольший элемент е, для которого истинно е <= obj. Если такой элемент найден, он возвращается, иначе null.
 * <p> Е higher(Е obj) - ищет в наборе наибольший элемент е, для которого истинно е > obj. Если такой элемент найден, он возвращается, иначе null.
 * <p> Е lower(Е obj) - ищет в наборе наименьший элемент е, для которого истинно е < obj. Если такой элемент найден, он возвращается, иначе null.
 * <p> NavigableSet headSet(Е upperBound, boolean incl) - возвращает NavigableSet, включающий все элементы вызывающего набора, меньшие upperBound.
 *      Результирующий набор поддерживается вызывающим набором (по другому это называется backed-collection).
 * <p> NavigableSet subSet(Е lowerBound, boolean lowlncl, Е upperBound, boolean highIncl) - возвращает NavigableSet,
 *      включающий все элементы вызывающего набора, которые больше lowerBound и меньше upperBound.
 *      Если lowlncl равно true, то элемент, равный lowerBound, включается. Если highlncl равно true, также включается элемент, равный upperBound.
 * <p> NavigableSet tailSet(Е fromElement, boolean inclusive) - возвращает NavigableSet, включающий все элементы вызывающего набора,
 *      которые больше (или равны, если inclusive равно true) чем fromElement.
 *      Результирующий набор поддерживается вызывающим набором (по другому это называется backed-collection).
 * <p> E pollLast() - возвращает c удалением последний элемент. Поскольку набор сортирован, это будет элемент с наибольшим значением.
 *      Возвращает null в случае пустого набора.
 * <p> Е pollFirst() - возвращает c удалением первый элемент. Поскольку набор сортирован, это будет элемент с наименьшим значением, иначе null.
 * <p> Iterator descendingIterator() - возвращает итератор, перемещающийся от большего к меньшему, другими словами, обратный итератор.
 * <p> NavigableSet descendingSet() - возвращает NavigableSet, представляющий собой обратную версию вызывающего набора.
 *      Результирующий набор поддерживается вызывающим набором.
 * <p>
 * <p>
 */
public class SetInfo {
}
