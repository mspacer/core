package com.core.filestream;

import com.bean.Person;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.StringJoiner;

/**
 * <p>ObjectOutputStream. Конструктор принимает OutputStream.
 * transient и static означают, что поля, помеченные ими, не могут быть предметом сериализации</p>
 * <p>Десериализация происходит следующим образом: под объект выделяется память, после чего его поля заполняются значениями из потока.
 * Конструктор сериализуемого класса при этом не вызывается, но вызываются все конструкторы суперклассов в заданной
 * последовательности до класса, имплементирующего Serializable.</p>
 * <p>Базовый класс должен имете конструктор по умолчанию, либо быть сереализуемым
 * <p>Если полем класса является ссылка на другой тип, то необходимо, чтобы агрегированный тип также реализовывал
 * интерфейс Serializable, иначе при попытке сериализации будет сгенерировано исключение NotSerializableException</p>
 * <p>При сериализации объекта класса, реализующего интерфейс Serializable, учитывается порядок объявления полей в классе.
 * Поэтому при изменении порядка, имен и типов полей или добавлении новых полей в класс структура информации,
 * содержащейся в сериализованном объекте, будет серьезно отличаться от новой структуры класса. Поэтому десериализация
 * может пройти некорректно. Этим обусловлена необходимость добавления программистом в каждый класс, реализующий
 * интерфейс Serializable, поля private static final long serialVersionUID на стадии разработки класса.
 * Это поле содержит уникальный идентификатор версии класса. Оно задается программистом или вычисляется по содержимому
 * класса — полям, их порядку объявления, методам, их порядку объявления. Для этого применяются специальные программы-генераторы UID.
 * Это поле записывается в поток при сериализации класса. Это тот случай, когда static-поле сериализуется.
 * При десериализации значение этого поля сравнивается с имеющимся у класса в виртуальной машине. Если значения не совпадают,
 * инициируется исключение java.io.InvalidClassException. Соответственно, при любом изме нении в первую очередь полей класса
 * значение поля serialVersionUID должнобыть изменено программистом или генератором.</p>
 * <p></p>
 * <p>Если в обьекте, что серелезуется обьявлены методы writeObject|readObject, то они будут вызваны при серелизации/десерелизации</p>
 * <p>Для сериализации singleton-обьекта необходимо обьявить readResolve() метод и взвращать текущей инстантс.
 * Это предотвратит создание копий.</p>
 * <p>writeReplace() заменяет серелезуемый обьект, возвращаемым методом обьект. </p>
 */

public class SerializeTest {
    public static void main(String[] args) {
        try {
            serializeObject();
            serializeSingletonObject();
            externalizable();
            xmlCodec();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void serializeObject() throws IOException {
        System.out.println("--------serializeObject-----------");
        File f = new File("data/serialize.txt");
        f.delete();

        Student student = new Student("Janka", 555777, "VKL_1410");
        System.out.println(student.hashCode());
        System.out.println(student.addr.hashCode());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(student);
        }

        Student desStudent = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serialize.txt"))) {
            desStudent = (Student) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(desStudent);
        System.out.println(desStudent.hashCode());
        System.out.println(desStudent.addr.hashCode());
        System.out.println();
    }

    private static void serializeSingletonObject() throws IOException {
        System.out.println("--------serializeSingletonObject-----------");
        File f = new File("data/serializeSingleton.txt");
        f.delete();

        Singleton sn1 = Singleton.getInstance();
        sn1.setValue(1);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(sn1);
        }

        sn1.setValue(10);
        System.out.println(sn1);

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializeSingleton.txt"))) {
            Singleton sn2 = (Singleton) input.readObject();
            System.out.println(sn2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void externalizable() throws IOException, ClassNotFoundException {
        System.out.println("--------externalizable-----------");
        Car c = new Car(1, "shkoda");

        FileOutputStream fileOutputStream = new FileOutputStream("data/externalizable.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        c.writeExternal(objectOutputStream);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("data/externalizable.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Car c2 = new Car();
        c2.readExternal(objectInputStream);

        System.out.println(c2);

        objectInputStream.close();
        fileInputStream.close();
    }

    private static void xmlCodec() throws IOException {
        System.out.println("--------xmlCodec-----------");
        File f = new File("data/serial.xml");
        f.delete();

        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)))) {
            Person p = new Person("shkoda", 1, 1.23, true, "QWERTY");
            xmlEncoder.writeObject(p);
            xmlEncoder.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)))) {
            Person p = (Person) xmlDecoder.readObject();
            System.out.println(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Man /*implements Serializable*/ {
    protected int code;

    public Man() {
    }

    public Man(int code) {
        this.code = code;
    }
}

class Student extends Man implements Serializable {
    static String faculty = "MMF";
    private String name;
    private int id;
    private /*transient*/ String password;
    public Address addr = new Address("Kiev");
    private static final long serialVersionUID = 3L;

    public Student(String name, int id, String password) {
        super(10);
        this.name = name;
        this.id = id;
        this.password = password;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        System.out.println("Student.writeObject call");
        this.password = "xyz" + password;
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        System.out.println("Student.readObject call");
        ois.defaultReadObject();
        this.password = password.substring(3);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'").add("id=" + id)
                .add("password='" + password + "'")
                .add("code='" + code + "'")
                .add("address='" + addr + "'")
                .toString();
    }
}

class Address implements Serializable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}

class Car implements Externalizable {
    private String model;
    private int code;

    public Car() {
    }

    public Car(int code, String model) {
        this.code = code;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Car {" +
                "code='" + code + "', " +
                "model='" + model + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(model);
        out.writeInt(code);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.model = in.readUTF();
        this.code = in.readInt();
    }
}

class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Singleton INSTANCE = new Singleton();

    private int value;

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private Object writeReplace() throws ObjectStreamException {
        System.out.println("call writeReplace");
        Singleton INSTANCE_2 = new Singleton();
        INSTANCE_2.value = 11;
        return INSTANCE_2;
    }

/*
    private Object readResolve() throws ObjectStreamException {
        System.out.println("call readResolve");
        return INSTANCE;
    }
*/

    @Override
    public String toString() {
        return "Singleton{" +
                "value=" + value +
                ", hashCode=" + this.hashCode() +
                '}';
    }
}
