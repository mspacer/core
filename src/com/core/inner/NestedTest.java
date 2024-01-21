package com.core.inner;

public class NestedTest {

/**
 - вложенный класс(nested)
 - статический внутренний класс более независим и отвязан от внешнего, т.к. не требует создания его объекта
 - для доступа к нестатическим членам и методам внешнего класса должен создавать объект внешнего класса,
    а напрямую иметь доступ только к статическим полям и методам внешнего класса.
- Статическому классу доступно только статическое содержимое класса-владельца
- Класс, вложенный в интерфейс, по умолчанию статический. На него не накладывается никаких особых ограничений,
    и он может содержать поля и методы как статические, так и нестатические.
- Подкласс вложенного класса не наследует возможность доступа к членам внешнего класса, которыми наделен его суперкласс

 - enum может быть внутренним статическим классом

 - анонимный/локальный внутренний класс
*/
    public static void main(String[] args) {
        SimpleStatic simpleStatic = new SimpleStatic();
        SimpleStatic.SubNested subNested = new SimpleStatic.SubNested(simpleStatic);
        System.out.println(subNested.getVar());

        System.out.println(new SimpleStatic.SubNested().getStaticOwnerVar());
        System.out.println(SimpleStatic.SubNested.getStaticOwnerVar2());

        SimpleStatic st1 = new SimpleStatic(2341757, "Mazaliyk", 5.42f);
        SimpleStatic st2 = new SimpleStatic(2341742, "Polovinkin", 5.42f);

        SimpleStatic.NameComparator nameComparator = new SimpleStatic.NameComparator();
        int result1 = nameComparator.compare(st1, st2);
        System.out.println(st1.getName() + " [" + result1 + "] " + st2.getName());
        
        SimpleStatic.MarkComparator markComparator = new SimpleStatic.MarkComparator();
        int result2 = markComparator.compare(st1, st2);
        System.out.println(st1.getMark() +" [" + result2+ "] "
                + st2.getMark());
        
        SimpleStatic.GroupComparator groupComparator = new SimpleStatic.GroupComparator();
        int result3 = groupComparator.compare(st1, st2);
        System.out.println(st1.getGroup() + " [" + result3+ "] " + st2.getGroup());

        ILogic.NestedLogic nestedLogic = new ILogic.NestedLogic();
        nestedLogic.accept();
        ILogic.NestedLogic.assign();
    }
}
