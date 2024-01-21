package com.core.inner;

import java.util.Arrays;

/**
 - Внутренний класс(inner)
 - не может содержать статические переменные и методы. Внутренний класс неотделим от внешнего
   Переменная может быть static final
 - Объект внутреннего класса нельзя создать в статическом методе «внешнего» класса.
 - к приватному внутреннему классу нет доступа даже через возвращаемую методом ссылку на его экземпляр
 - Доступ к элементам внутреннего класса возможен из внешнего только через объект внутреннего класса,
    который должен быть создан в коде метода внешнего класса
 - Методы внутреннего класса имеют прямой доступ ко всем полям и методам внешнего класса, как будто они его собственные
 - Объект внутреннего класса всегда ассоциируется (скрыто хранит ссылку) с создавшим его объектом внешнего класса —
    так называемым внешним, или enclosing объектом
 - Для наследования от внутреннего класса нужно правильно реализовать конструктор
    new Simple() - можно заменить получаемым параметром
    super(); - конструктор базового внутреннего класса

        public InheritorInner() {
            new Simple().super();
         }

 - Если внутренний класс наследуется обычным образом другим классом, то он теряет доступ к полям своего внешнего класса,
    в котором был объявлен. Доступ через наследуемые методы сохраняется.
 - this внутри класса Inner указывает на его собственный объект, и ни в коем случае не на его владельца.
   Доступ к переменной внешнего класса - Simple.this.var1
 - Внутренний класс может быть объявлен внутри метода или логического блока внешнего класса,
    видимость класса регулируется видимостью того блока, в котором он объявлен. Однако внутренний класс сохраняет доступ
    ко всем полям и методам внешнего класса, а также final-переменным, объявленным в текущем блоке кода;
 - Локальному внутреннему классу, объявленному внутри метода или логического блока, модификатор доступа не требуется,
    так как он все равно не доступен напрямую вне метода


 */
public class InnerTest {

    public static void main(String[] args) {
        Simple simple = new Simple();

        System.out.println("var1 внутреннего класса " + simple.getInnerVar());
        System.out.println(Simple.SimpleInnerPublic.ID);

        Simple.SimpleInnerPublic simpleInnerPublic = simple/* new Simple()*/.new SimpleInnerPublic(103);
        System.out.println("var1 внешнего класса " +  simpleInnerPublic.getVar());

        testHeirInner();

        //нет доступа к приватном внутреннему классу
        //System.out.println(new Simple().getSimpleInnerPrivate().toString());

        System.out.println(Simple.InnerEnum.INNER_ENUM.value);
        Arrays.stream(EnumAsAnonimous.values()).forEach(s -> System.out.println(s.computeSquare()));

    }

    private static void testHeirInner() {
        InheritorInner heir = new InheritorInner();
        System.out.println("var1 внешнего класса из наследника " +  heir.getVarForHeir());
        System.out.println("local var1 внутреннего класса " +  heir.getLocalVar());
    }



}

class InheritorInner extends Simple.SimpleInnerPublic {

    public InheritorInner() {
        new Simple().super(104);
    }

    public int getVarForHeir() {
        //return var1; - нет прямого доступа
        return getVar();
    }
}