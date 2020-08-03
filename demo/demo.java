class Person {
    void speak(){
        System.out.println("I am a person");
    }
}

final class Student extends Person {
    void speak(){
        super.speak();
        System.out.println("And I am also a student");
    }
}

final class Lecturer extends Person {
    void speak(){
        super.speak();
        System.out.println("And I am also a lecturer");
    }
}

class Demo {
    public static void main(String[] args){
        Person[] persons = new Person[]{ new Person(), new Student(), new Lecturer()};

        for (Person person: persons) {
            person.speak();
        }
    }
}