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
 */

public class SerializeTest {
    public static void main(String[] args) {
        try {
            objectOutputStream();
            externalizable();
            xmlCodec();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void objectOutputStream() throws IOException {
        System.out.println("--------objectOutputStream-----------");
        File f = new File("data/serialize.txt");
        f.delete();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            Student student = new Student("Janka", 555777, "VKL_1410");
            oos.writeObject(student);
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serialize.txt"))) {
            Student student = (Student) input.readObject();
            System.out.println(student);
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

class Student implements Serializable {
    static String faculty = "MMF";
    private String name;
    private int id;
    private transient String password;
    private Address addr = new Address("Kiev");
    private static final long serialVersionUID = 3L;

    public Student(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'").add("id=" + id)
                .add("password='" + password + "'")
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
