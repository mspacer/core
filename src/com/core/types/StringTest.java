package com.core.types;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;

public class StringTest {
    /**
     * - Строки в Java представляют собой константы, которые не могут быть изменены
     * - Создать объект класса String можно двумя способами: при помощи строкового литерала и конструктора
     * - Строковый литерал сохраняется в пул строк, если до этого он там отсутствовал
     * - Строка, созданная при помощи конструктора, сохраняется в heap, а не в пул строк
     * - Java 6: Пул строк хранится в памяти фиксированного размера, именуемого PermGen.
     * <p>
     * - Java 7, 8: Пул строк хранится в heap и, соответственно, для пула строк можно использовать всю память приложения
     * - При помощи параметра -XX:StringTableSize=N, где N — размер HashMap, можно изменить размер пула строк.
     * Его размер является фиксированным, поскольку он реализован, как HashMap со списками в корзинах
     * - Процесс помещения строк в пул называется интернирование (от англ. interning).
     * - Инженеры по оптимизации Java компании Oracle настоятельно не рекомендуют самостоятельно интернировать строки,
     * поскольку это приводит к замедлению работы приложения. Их рекомендация — дедупликация
     * - Oднажды интернированную строку в версии Java ниже 7й нельзя деинтернировать: она будет занимать память программы даже тогда, когда перестанет быть нужна
     * - Риск интернирования строк в область PermGen (вместо кучи) заключается в том, что мы можем получить от JVM ошибку OutOfMemoryError.
     * <p>
     * - Для активации дедупликации необходимо в параметрах виртуальной машины указать -XX:+UseStringDeduplication,
     * а также активировать сборщик мусора G1 (если он не используется по умолчанию), указав также -XX:+UseG1GC.
     * - До Java 8 (включительно) строка внутренне представлялась, как массив символов char[] в кодировке UTF-16,
     * каждый из которых занимал по два байта в памяти.
     * - В Java 9 было внедрено новое представление для типа String, получившее название компактные строки (Compact Strings).
     * Параметр String.COMPACT_STRINGS
     * Благодаря новому формату хранения строк (в зависимости от контента) делается выбор между массивом символов char[] и массивом байт byte[].
     * Поскольку новый способ хранения объектов типа String использует кодировку UTF-16 лишь в том случае, когда в этом есть необходимость,
     * объем памяти, занимаемый пулом строк в куче, будет значительно ниже, что в, свою очередь, уменьшит издержки работы сборщика мусора.
     * <p>
     * - В Java 6 единственной оптимизацией, которую мы могли сделать — это увеличить размер PermGen во время запуска программы, используя опцию JVM — MaxPermSize:
     * -XX:MaxPermSize=1G
     * - В Java 7 разработчикам предоставили более гибкую возможность настройки (увеличение/уменьшение) размера пула строк. Существуют две возможности посмотреть размер пула:
     * -XX:+PrintStringTableStatistics и -XX:+PrintFlagsFinal
     * - размер пула строк и другие данные
     * java -XX:+PrintStringTableStatistics
     * - Если мы хотим увеличить размер пула, то для этого необходимо воспользоваться опцией StringTableSize:
     * -XX:StringTableSize=4901
     * - Во время сборки мусора GC проверяет живые (имеющие рабочие ссылки) объекты в куче на возможность провести их дедупликацию.
     * Ссылки на подходящие объекты вставляются в очередь для последующей обработки. Далее происходит попытка дедупликации каждого
     * объекта String из очереди, а затем удаление из нее ссылок на объекты, на которые они ссылаются. Также для отслеживания всех
     * уникальных массивов байт, используемых объектами String, используется хеш-таблица. При дедупликации в этой хеш-таблице
     * выполняется поиск идентичных массивов байт (символов). При положительном результате значение поля value объекта String переприсваивается так,
     * чтобы указывать на этот существующий массив байт. Соответственно, предыдущий массив байт value становится ненужным — на него ничего не ссылается
     * и впоследствии он попадает под сборку мусора. При отрицательном результате, массив байт, соответствующий value, вставляется в хеш-таблицу,
     * чтобы впоследствии быть использованным совместно с новым объектом String в какой-то другой момент в будущем.
     * - Дедупликация строк работает только со сборщиком мусора G1. Для его активации в Java 8 необходимо указать параметр для виртуальной машины
     * -XX:+UseG1GC.
     * Начиная с Java 9, G1 является сборщиком мусора по умолчанию
     */
    public static void main(String[] args) {
        String str1 = "TopJava"; // string pool
        String str2 = "TopJava";
        String str3 = new String("TopJava"); // heap
        System.out.println("Строка 1 равна строке 2? " + (str1 == str2));
        System.out.println("Строка 2 равна строке 3? " + (str2 == str3));
        System.out.println("Строка 2 equal строке 3? " + (str2.equals(str3)));

        String str4 = new String("На русском");
        String str4_1 = new String("На русском другой");
        System.out.println("Строка 4 equal строке 4_1? " + (str4.equals(str4_1)));
        System.out.println("Строка 4 compareTo строке 4_1? " + (str4.compareTo(str4_1)));

        System.out.println("--------interning---------");
        String str5 = (new String("TopJava")).intern();
        String str6 = (new String("TopJava")).intern();
        System.out.println("Строка 2 равна строке 5? " + (str2 == str5));
        System.out.println("Строка 5 равна строке 6? " + (str5 == str6));

        String str7 = "interned TopJava";
        String str8 = ("interned " + str2).intern();
        System.out.println("Строка 7 равна строке 8? " + (str7 == str8));

        System.out.println("--------Дедупликация---------");
        try {
            deduplicationDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--------methods---------");
        String str9 = String.join("-", "java", "14", "SE");
        System.out.println(str9);

        str9.codePoints().filter(o -> o != '-').forEach(o -> System.out.print((char) o + "/" + o + "/"));
        System.out.println("");

        StringJoiner joiner = new StringJoiner(":", "<<", ">>");
        String result = joiner.add("blanc").add("rouge").add("blanc").toString();
        System.out.println(result);

        String str10 = "  ;1dfsf dfdfd df;   ";
        //java 11
        //System.out.println("strip: '" + str10.strip() + "'");
        str10 = str10.trim();
        System.out.println("strip: '" + str10 + "'");

        char[] value = {'a', 's', 'd', 'f', 'g'};
        str10.getChars(1, 3, value, 2);
        System.out.println("value: '" + new String(value) + "'");

        String s = new String("3");
        System.out.println(1 + 2 + s + 4 + 5);

        String[] strings = new String[]{"a", "b", "c"};
        int k = 0;
        for (String element : strings) {
            strings[k].concat(String.valueOf(k));
            ++k;
        }
        System.out.print(Arrays.toString(strings));
        System.out.println("");

        stringJoinerExample();
    }

    static void deduplicationDemo() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        char[] chars = {'T', 'o', 'p', 'J', 'a', 'v', 'a'};
        String[] strings = {new String(chars), new String(chars)};
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);

        System.out.println("Хеш первого объекта: " + value.get(strings[0]));
        System.out.println("Хеш второго объекта: " + value.get(strings[1]));

        System.gc();
        System.out.println("Запустили сборщик мусора");
        Thread.sleep(100);

        System.out.println("Хеш первого объекта: " + value.get(strings[0]));
        System.out.println("Хеш второго объекта: " + value.get(strings[1]));
    }

    private static void stringJoinerExample() {
        System.out.println("stringJoinerExample");

        StringJoiner sj1 = new StringJoiner(";", "pref", "suf");
        sj1.add("one").add("two");
        System.out.println(sj1);

        StringJoiner sj2 = new StringJoiner("/", "pf2", "sf2");
        sj2.add("str2");

        sj1.merge(sj2);
        System.out.println(sj1);
    }
}