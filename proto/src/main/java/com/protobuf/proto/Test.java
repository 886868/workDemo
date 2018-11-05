package com.protobuf.proto;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by yueminrui on 2018/4/24.
 */
public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("aaa");
        builder.setEmail("aaa@aa.com");

        PersonModel.Person person = builder.build();

        System.out.println("before: " + person.toString());

        System.out.println("========Person Byte========");
        for (byte b :
                person.toByteArray()) {
            System.out.println(b);
        }

        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("========================");

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
    }
}
