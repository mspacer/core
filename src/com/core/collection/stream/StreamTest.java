package com.core.collection.stream;

import com.core.collection.list.ArrayLIstTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Интерфейс java.util.stream.Stream<T> — поток объектов для преобразования коллекций, массивов.
 * IntStream, LongStream, DoubleStream - частные случаи стримов.
 *
 * <p>Операции в стриме двух типов - промежуточные (intermediate operation) и термальные (terminal operation)
 * intermediate операции возвращают новый stream, термальные завершают выполнение потока. После термальной операции
 * работать со стримом нельзя (java.lang.IllegalStateException)
 * <p>Комбинации операций формируют Пайплайн (Pipeline) - последовательность операций в потоке, применяемых к данным
 * <p>Все промежуточные операции выполняются только при вызове терминальной операции, которая запускает общую цепочку обработки.
 * <p>Промежуточные операции делятся на Stateless (без состояния) и Statefull (сохраняющие состояние) операции.
 *  * <p>Stateless операции такие как map() и filter(), обрабатывают каждый элемент потока независимо от других.
 * Они не требуют информации о других элементах для своей работы, что делает их идеально подходящими для параллельной обработки.
 *  * <p>Сохраняющие состояние (stateful), такие как sorted(), distinct() или limit(), требуют знания о других элементах для своей работы.
 * Это означает, что им приходится учитывать все (или часть) элементы в потоке перед выдачей какого-либо результата.
 *  * <p>Если ваш пайплайн содержит только операции без состояния, то он может быть обработан "в один проход".
 * Если же он содержит операции с состоянием, то пайплайн разбивается на секции, где каждая секция заканчивается операцией с состоянием.
 *
 * <p>При выполнении Stream исходные данные не изменяются. В результате своей работы Stream создает новую структуру данных.
 * (т.е. ни добавить в коллекцию, ни удалить из нее нельзя.)
 *
 * <p>Стримы могут быть последовательными и параллельными. Первые выполняются в текущем потоке, вторые используют общий пул ForkJoinPool.commonPool().
 * В параллельном стриме элементы разделяются на группы. Их обработка проходит в каждом потоке по отдельности.
 * Затем они снова объединяются, чтобы вывести результат. С помощью методов parallel и sequential можно явно указать,
 * что нужно сделать параллельным, а что — последовательным.
 * Не рекомендуется применять параллельность для выполнения долгих операций (например, извлечения данных из базы),
 * потому что все стримы работают с общим пулом. Долгие операции могут остановить работу всех параллельных стримов в Java Virtual Machine из-за того,
 * что в пуле не останется доступных потоков. Чтобы избежать такой проблемы, используйте параллельные стримы только для коротких операций,
 * выполнение которых занимает миллисекунды, а не секунды и тем более минуты.
 *
 * <p>Промежуточные операции
 * <p>filter - отработает как фильтр, вернет значения, которые подходят под заданное условие
 *      <pre>collection.stream().filter(«e22»::equals).count()</pre>
 * <p>sorted	отсортирует элементы в естественном порядке; можно использовать Comparator
 *      <pre>collection.stream().sorted().collect(Collectors.toList())</pre>
 * <p>limit	лимитирует вывод по тому, количеству, которое вы укажете
 *      <pre>collection.stream().limit(10).collect(Collectors.toList())</pre>
 * <p>skip	пропустит указанное вами количество элементов
 *      <pre>collection.stream().skip(3).findFirst().orElse("4")</pre>
 * <p>distinct	найдет и уберет элементы, которые повторяются; вернет элементы без повторов
 *      <pre>collection.stream().distinct().collect(Collectors.toList())</pre>
 * <p>peek	выполнить действие над каждым элементом элементов, вернет стрим с исходными элементами
 *      <pre>collection.stream().map(String::toLowerCase).peek((e) -> System.out.print("," + e)). collect(Collectors.toList())</pre>
 * <p>map	выполнит действия над каждым элементом; вернет элементы с результатами функций
 *      <pre>Stream.of("3", "4", "5").map(Integer::parseInt).map(x -> x + 10).forEach(System.out::println);</pre>
 * <p>mapToInt, mapToDouble, mapToLong	Сработает как map, только вернет числовой stream
 *      <pre>collection.stream().mapToInt((s) -> Integer.parseInt(s)).toArray()</pre>
 * <p>flatMap, flatMapToInt, flatMapToDouble, flatMapToLong	преобразовывает один объект, как правило составной, в объект более простой структуры,
 * например, массив в строку, список в объект, список списков в один список:
 *      <pre>collection.stream().flatMap((p) -> Arrays.stream(Arrays.asList(p.split("\\s+")).toArray())).collect(Collectors.toList()); </pre>
 *
 * <p>Терминальные
 * <p>findFirst	вернет элемент, соответствующий условию, который стоит первым
 *      <pre>collection.stream().findFirst().orElse("10")</pre>
 * <p>findAny	вернет любой элемент
 *      <pre>collection.stream().findAny().orElse("10")</pre>
 * <p>collect	соберет результаты обработки в коллекции и не только
 *      <pre>collection.stream().filter((s) -> s.contains("10")).collect(Collectors.toList())</pre>
 * <p>count	посчитает и выведет, сколько элементов, соответствующих условию
 *      <pre>collection.stream().filter("f5"::equals).count()</pre>
 * <p>anyMatch	True, когда хоть один элемент соответствует условиям
 *      <pre>collection.stream().anyMatch("f5"::equals)</pre>
 * <p>noneMatch	True, когда ни один элемент не соответствует условиям
 *      <pre>collection.stream().noneMatch("b6"::equals)</pre>
 * <p>allMatch	True, когда все элементы соответствуют условиям
 *      <pre>collection.stream().allMatch((s) -> s.contains("8"))</pre>
 * <p>min	найдет самый маленький элемент, используя переданный сравнитель
 *      <pre>collection.stream().min(String::compareTo).get()</pre>
 * <p>max	найдет самый большой элемент, используя переданный сравнитель
 *      <pre>collection.stream().max(String::compareTo).get()</pre>
 * <p>forEach	применит функцию ко всем элементам, но порядок выполнения гарантировать не может
 *      <pre>set.stream().forEach((p) -> p.append("_2"));</pre>
 * <p>forEachOrdered	применит функцию ко всем элементам по очереди, порядок выполнения гарантировать может
 *      <pre>list.stream().forEachOrdered((p) -> p.append("_nv"));</pre>
 * <p>toArray	приведет значения стрима к массиву
 *      <pre>collection.stream().map(String::toLowerCase).toArray(String[]::new);</pre>
 * <p>reduce	преобразует все элементы в один объект
 *      <pre>collection.stream().reduce((c1, c2) -> c1 + c2).orElse(0)</pre>
 *
 * <p>Метод collect позволяет гибко управлять преобразованием значений в разные типы: коллекции, массивы, map.
 * Делается это благодаря статистическим методам Collectors.
 * <p> Примеры Collectors
 * <p>toList — стрим приводится к списку;
 * <p>toCollection — получаем коллекцию;
 * <p>toSet — получаем множество;
 * <p>toConcurrentMap, toMap — если нужен map;
 * <p>summingInt, summingDouble, summingLong — если требуется получить сумму чисел;
 * <p>averagingInt, averagingDouble, averagingLong — вернуть среднее значение;
 * <p>groupingBy — если необходимо разбить коллекцию на части.
 * <p>joining(CharSequence delimiter) — обеспечивает конкатенацию строк с заданным разделителем
 * <p>mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream) — позволяет преобразовать элементы одного типа в элементы другого типа;
 * <p>filtering (Predicate<? super T> predicate, Collector<? super T, A, R> downstream) — выполняет фильтрацию элементов;
 * <p>counting() — позволяет посчитать количество элементов потока;
 * <p>summingInt(ToIntFunction<? super T> mapper) — выполняет суммирование элементов. Существуют версии для Long и Double;
 * <p>averagingInt(ToIntFunction<? super T> mapper) — вычисляет среднее арифметическое элементов потока. Существуют версии для Long и Double;
 * <p>reducing(T identity, BinaryOperator<T> op) — коллектор, осуществляющий редукцию (сведение) элементов на основании заданного бинарного оператора;
 * <p>
 * <p>
 */
public class StreamTest {

    public static void main(String[] args) {
        final List<String> list = Arrays.asList("one", "two", "three");
        Stream<String> stream = list.stream();
        stream.filter(s -> {
                    System.out.println("filter: " + s);
                    return s.length() <= 3;
                })
                .map(s1 -> {
                    System.out.println("map: " + s1);
                    return s1.toUpperCase();
                })
                .peek(s -> s += "peek_")
                .peek(s -> System.out.println("peek: " + s ))
                //.sorted()
                .forEach(x -> {
                    System.out.println("forEach: " + x);
                });

        Stream<String> valuesS = Stream.of("one", "two", "three");
        System.out.println(valuesS.reduce((s, s2) -> s + "/" + s2).get());

        Stream<Object> build = Stream.builder().add("One(1)")
                .add("Two(2)")
                .build();
        build.forEach(System.out::println);

        //System.out.println(stream.findFirst()); - error java.lang.IllegalStateException

        ArrayList<Data> list1 = new ArrayList<>();
        list1.add(new Data(11));
        list1.add(new Data(12));
        list1.add(new Data(13));

        list1.stream()
                .forEach(data -> data.pos += 100);
        System.out.println(list1);

        List<Data> list2 = list1.stream()
                .filter(data -> data.pos == 113)
                .map(data -> {
                    data.pos += 100;
                    return data;
                })
                //.collect(Collectors.toList());
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list1);
        System.out.println(list2);

        String[] split = "as a the d on and".split("\\s+");
        Stream.of(split)
                .map(String::toUpperCase)
                .forEach(System.out::print);
        System.out.println();

        Stream<String> valuesList = Stream.of("one {1}", "two {2}", "three {3}");
        List<Object> collect = valuesList.flatMap((p) -> Arrays.stream(Arrays.asList(p.split("\\s+")).toArray()))
                .collect(Collectors.toList());
        System.out.println(collect);

        Stream.of("ваы ваыа ва ыа ыа".toCharArray())
                .forEach(System.out::print);
        System.out.println();

        collectorsExamples();
    }

    private static void collectorsExamples() {
        System.out.println("collectorsExamples:");
        List<String> strings = Arrays.asList("as a the d on and".split("\\s+"));

        String result = strings.stream()
                .map(String::toUpperCase).collect(Collectors.joining(" : "));
        System.out.println(result);

        List<String> collect = strings.stream()
                .collect(Collectors.mapping(s -> s.toUpperCase(), Collectors.toList()));
        System.out.println(collect);

        int max = strings.stream()
                .collect(Collectors.mapping(s -> (int) s.charAt(0),
                        Collectors.maxBy(Integer::compareTo)))
                .orElse(-1);
        System.out.println(max + "/" + (char)max);

        int length = strings.stream()
                .collect(Collectors.summingInt(String::length));
        System.out.println(length);

        int sumCodeFirstChars = strings.stream()
                .map(s -> (int)s.charAt(0))
                .collect(Collectors.reducing(0, (a, b) -> a + b));
        System.out.println(sumCodeFirstChars);
        int otherLength = strings.stream()
                .collect(Collectors.reducing(0, o -> o.length() , (a, b) -> a + b));
        System.out.println(otherLength);

    }

    private static class Data {
        int pos;

        Data(int pos) {
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "pos=" + pos +
                    '}';
        }
    }
}