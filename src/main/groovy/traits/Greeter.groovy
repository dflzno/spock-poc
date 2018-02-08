package traits

import basics.Person

trait Greeter {

    private String className = 'Greeter'

    /**
     Only public and private methods are supported in traits.
     Abstract methods are supported
     */

    private String greetingMessage() {
        'Hello from a private method!'
    }

    String greet() {
        def m = greetingMessage()
        println m
        m
    }
}