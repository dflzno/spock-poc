import spock.lang.Specification

class SimpleTest extends Specification {
    // https://www.pluralsight.com/guides/java-and-j2ee/introduction-to-testing-with-bdd-and-the-spock-framework
    def test1() {
    }

    def "test 2"() { // Groovy's strings as methods' names
    }

    def "should add one integer to another"() {
        given: "An integer with value 5" // given: (or setup:) block is implicit and can be omitted
        def five = 5

        and: //optional label
        def seven = 7

        when: "The sum operation is performed"
        def result = five + seven
        result == 10 // WONT'T be part of the assertions (test won't fail)

        then: "The result should be 12"
        result == 12 // no need for assert keyword, statements in the then: block will be evaluated as boolean
    }
}
