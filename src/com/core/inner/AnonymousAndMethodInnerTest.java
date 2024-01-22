package com.core.inner;

import java.util.Arrays;

/**
 - Объявление анонимного класса выполняется одновременно с созданием его объекта посредством оператора new.
 - Конструктор анонимного класса определить невозможно
 - анонимный класс может быть создан от абстрактного класса
 - новые поля и методы будут невидимы вне этого класса
 - имеет доступ к переменным и методам внешнего класса как обычный inner класс, а также лакальным переменным метода,
    а котором создан, которые должны быть помечены как final (т.е. константы)
 - Класс, объявленный внутри метода, не может быть объявлен как static,
    а также не может содержать статические поля (только final static) и методы.
 - нельзя создать анонимный статический вложенный класс. статической становится только переменная, но не класс

 - Для перечисления объявление анонимного внутреннего класса выглядит несколько иначе, так как инициализация всех элементов
    происходит при первом обращении к типу. Поэтому и анонимный класс реализуется только внутри объявления типа enum
 - Ситуации, в которых следует использовать внутренние классы:
    • выделение самостоятельной логической части сложного класса;
    • сокрытие реализации;
    • одномоментное использование переопределенных методов;
    • реализация обработчиков событий;
    • запуск потоков выполнения;
    • отслеживание внутреннего состояния, например, с помощью enum.
 */
public class AnonymousAndMethodInnerTest {
    private static String localVar = "localVar";
    private String localVar2 = "localVar2";

    static SimpleStatic.SubNested staticNested = new SimpleStatic.SubNested() { };

    public static void main(String[] args) {
        localVar += " changed";
        AnonymousAndMethodInnerTest anonymousInnerTest = new AnonymousAndMethodInnerTest();
        anonymousInnerTest.localVar2 += " changed";
        anonymousInnerTest.testAnonymous(2);

        System.out.println(Simple.InnerEnum.INNER_ENUM.value);
        Arrays.stream(EnumAsAnonymous.values()).forEach(s -> System.out.println(s.computeSquare()));

        AbstractTeacher teacher = anonymousInnerTest.createMethodInner(65);
        teacher.remandStudent("Ivanov");
        System.out.println(teacher);
    }

    private void testAnonymous(final int addBase) {
        StudentAction action = new StudentAction();// usually object
        //addBase++; //Variable 'addBase' is accessed from within inner class, needs to be final or effectively final
        localVar += " ch-2";
        localVar2 += " ch-2";
        /*static*/ StudentAction actionAnon = new StudentAction() {// anonymous class object
            /*private*/ final static int base = 9; // invisible
            @Override
            public double defineScholarship(float averageMark) {
                System.out.println("anonymous defineScholarship " + localVar + "/" + localVar2+ "/" + addBase);
                localMethod();

                double value = 100;
                if (averageMark > base) {
                    value *= 1 + (base / 10.0);
                }
                return value;
            }

            // invisible. Inner classes cannot have static declarations
            public /*static*/ void newMethod() {

            }
        };
        System.out.println(action.defineScholarship(9.05f));
        System.out.println(actionAnon.defineScholarship(9.05f));
        //((StudentAction) actionAnon).newMethod();
    }

    private AbstractTeacher createMethodInner(int id) {
        int value = 0;

        // class declaration inside a method
        /*static*/ class Rector extends AbstractTeacher {
            Rector(int id) {
                super(id);
            }
            @Override
            public String remandStudent(String student) {
                studentName = student;
                // value++; compile error
                return student + " !!!";
            }

            @Override
            public String toString() {
                return super.toString();
            }
        } // inner class: end

        return new Rector(id);
    }

    private void localMethod() {
        System.out.println("localMethod");
    }

}

class StudentAction {
    private final static int BASE_COEFFICIENT = 6;
    public double defineScholarship(float averageMark) {
        System.out.println("origin defineScholarship");
        double value = 100;
        if (averageMark > BASE_COEFFICIENT) {
            value *= 1 + (BASE_COEFFICIENT / 10.0);
        }
        return value;
    }
}

abstract class AbstractTeacher {
    private int id;
    protected String studentName;
    public AbstractTeacher(int id) {
        this.id = id;
    }
    public abstract String remandStudent(String student);

    @Override
    public String toString() {
        return "AbstractTeacher{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}