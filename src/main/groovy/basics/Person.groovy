package basics

class Person {
    String name
    String lastName
    private String fullName

    void setName(String name) {
        this.name = name
        println 'name attribute was changed through setter method'
    }

    String getLastName() {
        lastName + "."
    }
}
