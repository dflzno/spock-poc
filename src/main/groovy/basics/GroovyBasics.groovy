package basics

class GroovyBasics {
    // https://leanpub.com/groovytutorial/read
    // https://www.youtube.com/watch?v=B98jc8hdu9g
    // http://www.baeldung.com/groovy-language

    static void main(String[] args) {

        // Dynamic typing
        Duck duck = new Duck()
        Cat cat = new Cat()
        def list = [duck, cat]
        list.each { obj ->
            println obj.getName() // even though there's no common interface
        }
        //

        // Truthy conversion
        println "12 ${12 ? 'TRUE' : 'FALSE'}"
        println "0 ${0 ? 'TRUE' : 'FALSE'}" // false
        println "new Object() ${new Object() ? 'TRUE' : 'FALSE'}"
        println "'' ${'' ? 'TRUE' : 'FALSE'}"
        println "new CustomizedTruthyConversion() ${new CustomizedTruthyConversion() ? 'TRUE' : 'FALSE'}"
        //

        // Operators
        Person p = null;
        println "p.name ${p?.name}" // null-safe dereference
        println "p ${p ?: 'FALSE'}" // Elvis operator
        println "5 <=> 10 ${5 <=> 10}" // Spaceship operator
        println "5 <=> null ${5 <=> null}" // handles null values
        //

        // Collections and Maps
        List wordsList = ['Hello', 'World']
        Set wordsSet = ['Hello', 'World']
        def wordsSet2 = ['Hello', 'World'] as Set
        def map = [
            'a': 'A',
            'b': 'B',
            'c': 'C'
        ]
        println map['a']
        println map.b
        println "wordsList's type: ${wordsList.class.name}"
        println "wordsSet's type: ${wordsSet.class.name}"
        println "map's type: ${map.getClass().name}" // Can't get class by property because of the dot notation of maps

        def letters = ['a', 'b', 'c'] //as LinkedList as String[] <-- to change the default type (ArrayList)
        println letters[0]
        letters[0] = 'A'
        println letters
        letters << 'd' // << not available for arrays
        println letters
        println letters[0..2]
        println letters[1,4] // no index out of bounds for lists, but it will happen for arrays
        println letters[-2]

        def key = 'name'
        def person = [key: 'David']
        println "name = ${person.get('name')}" // null, because key was not used as a variable, but as a literal 'key'
        println "name = ${person.get('key')}" // this works

        def newKey = 'name'
        person << [(newKey): 'Dave'] // resolves the variable to use its content
        println "name = ${person.get('name')}" // now it works
        //

        // Closures
        def power = { int x, int y ->
            Math.pow(x, y)
        }
        println "power(2, 3) ${power(2, 3)}" // alternative use power.call(2, 3)
        def myprintln = {println "it -> ${it}"}
        myprintln 'Groovy'
        myprintln 12
        //

        // Strings vs GStrings
        def number = 1
        def eagerGString = "value == ${number}"
        def lazyGString = "value == ${-> number}"

        println 'eagerGString' + eagerGString
        println 'lazyGString' + lazyGString

        number = 2
        println 'eagerGString' + eagerGString // value is still 1
        println 'lazyGString' + lazyGString   // value is 2, because a closure in an interpolated String gets lazyly evaluated

        def oneAsString = "1"
        def oneAsGString = "${1}"
        /* Not the same hashCode, GString's hashCode varies as the interpolated String varies, don't use  GStrings as map keys*/
        println "String's hashCode ${oneAsString.hashCode()}, GString's hashCode ${oneAsGString.hashCode()}"

        def printChar = {char c -> println c}
        printChar "A" as char // won't work without type coercion
        printChar 'B' as char // won't work without type coercion
        char c = "C"
        printChar c
        printChar((Character)'D') // or (char)'D'
        //

        // Numbers
        def num1 = 1
        println num1.class.name
        def num2 = 6456434234
        println num2.class.name
        def num3 = 12.4343 // defaults to BigDecimal
        println num3.class.name

        def reallyLongNumber = 4729_3472_9387_4923_7492_3749_2374_9987_3492_7394_7329_4273_9423_749 // _ helps to group them for easier readability
        println reallyLongNumber

        println "1/2: ${1/2}, 1.intdiv(2): ${1.intdiv(2)}"
        //
    }
}

class Duck {
    String getName() { 'Duck' }
}

class Cat {
    String getName() { 'Cat' }
}

class CustomizedTruthyConversion {
    def asBoolean() {
        false // whatever makes sense for your object to be considered true or false
    }
}
