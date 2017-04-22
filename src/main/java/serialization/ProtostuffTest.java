package serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by oahnus on 2017/4/22
 * 21:25.
 */
public class ProtostuffTest {

    public static void main(String... args) {
        RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        Person person = new Person("jack", 12);
        // store
        byte[] bytes = ProtobufIOUtil.toByteArray(person, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        // get
        Person p = schema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, p, schema);
        System.out.println(p.getName());
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}