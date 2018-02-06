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
