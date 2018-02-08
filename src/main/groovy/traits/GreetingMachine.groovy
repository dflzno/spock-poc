package traits

class GreetingMachine implements Greeter {

    static void main(String[] args) {
        def g = new GreetingMachine()
        assert g.greet() == 'Hello from a private method!'
        try {
            assert g.greetingMessage() // so in Groovy 2.4.13 (and other versions) private methods in traits seem to be the only private members that are truly private
        } catch (MissingMethodException e) {
            println 'greetingMessage() is private in trait'
        }
        println g.traits_Greeter__className //private field is accessible, but will have to be accessed with the fully qualified name

    }
}
