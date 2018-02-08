package traits;

public class UsesTrait implements Greeter {

    /*
    * When implementing a Groovy Trait with default methods in a Java Class, the
    * class won't have those default methods, but will interpret them as abstract
    * and will have to implement them
    * */
    public String greet() {
        return null;
    }
}
