package basics

class GroovyNotSoBasic {

    static void main(String[] args) {
        // Method references
        def action = GroovyNotSoBasic.&describe // also works for non-static methods
        def dogs = [new Dog('Bobby', 2), new Dog('Fifi', 4)]
        dogs = transform(dogs, action)
        println dogs
        //

        // Spread operator
        def anotherListOfDogs = [new Dog('Sussie', 10), new Dog('Puffy', 1)]
        anotherListOfDogs = anotherListOfDogs*.name // invokes an action on all items of the list and collects the result, it's null-safe (will return null for a null element, like ['a', 'b', null, 'd'])
        println anotherListOfDogs

        def xyz = [1, 2, 3]

        //

        // Type coercion
        println (new Dog("Sam", 1) as String)
        //

        // Constructors
        println ((['Bobby', 5] as Dog) as String)
        Dog d = ['Firulais', 2]
        println (d as String)
        //

    }

    static transform(List elements, Closure action) {
        def result = []
        elements.each {
            result << action(it)
        }
        result
    }

    static String describe(Dog g) {
        "$g.name's age is $g.age"
    }

    static sumOfThreeNumbers(int x, int y, int z) {
        x + y + z
    }
}

class Dog {
    String name
    int age

    Dog(String name, int age) {
        this.name = name
        this.age = age
    }

    def asType(Class target) {
        if(target == String) {
            name
        }
    }
}