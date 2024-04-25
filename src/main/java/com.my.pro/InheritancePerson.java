package com.my.pro;

public class InheritancePerson {

    public static void main(String[] args) {
        Person person = new Person();
        person.sayHow(getHow());

        Person employee = new Employee();
        employee.sayHow(getHow());

        User user = employee;
        user.sayHow(getHow());
    }

    private static String getHow() {
        return null;
    }
}

interface How {
    default void sayHow(String how) {
        sayHow(how, 1);
        try {
            if (null == how) throw new RuntimeException("again");
        } catch (RuntimeException rte) {
            // Обработка исключения
        }
    }

    void sayHow(String how, int n);
}

class User implements How {
    @Override
    public void sayHow(String how, int n) {
        try {
            if (null == how) throw new RuntimeException("again user");
        } catch (Exception rte) {
            System.out.println("user");
        }
    }
}

class Person extends User {
    @Override
    public void sayHow(String how, int n) {
        try {
            if (null == how) throw new RuntimeException("again person");
        } catch (RuntimeException rte) {
            System.out.println("person");
        }
    }
}

class Employee extends Person {
    @Override
    public void sayHow(String how, int n) {
        try {
            if (null == how) throw new RuntimeException("again employee");
        } catch (RuntimeException rte) {
            System.out.println("employee");
        }
    }
}
