import spock.lang.Ignore
import spock.lang.Specification

class SimpleTest extends Specification {
    // http://spockframework.org/spock/docs//current/index.html
    // https://www.pluralsight.com/guides/java-and-j2ee/introduction-to-testing-with-bdd-and-the-spock-framework
    // https://semaphoreci.com/community/tutorials/stubbing-and-mocking-in-java-with-the-spock-testing-framework
    
    def setup() {}         // runs before every feature method
    def cleanup() {}       // runs after every feature method
    def setupSpec() {}     // runs before the first feature method
    def cleanupSpec() {}   // runs after the last feature method

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

        /* As a guideline, use when:-then: to describe methods with side effects,
           and expect: to describe purely functional methods */
        expect:
        4 == 2 * 2 // assume 2 * 2 is a method call
    }

    def 'data-driven test'() {
        expect:
        x + y == z

        where:
        x  | y  | z
        3  | 4  | 7
        19 | 23 | 42
        71 | 12 | 83

        // Another way to write this where: block
        /*
        where:
        x << [3, 19, 71]
        y << [4, 23, 12]
        z << [7, 42, 83]
        */
    }

    def "data-drive test 2"() {
        expect:
        x + y == z

        where:
        x   |   _
        1   |   _
        2   |   _
        3   |   _
        y << [1, 2, 3]
        z = x + y
    }

    def "using real implementation of DataProvider"() {
        given: 'The service uses a real instance of DataProvider'
        def service = new Service(new DataProvider())

        when: 'The service is requested to query data'
        def result = service.data

        then: 'The size of the data returned by the service should be 2'
        result.size() == 2
    }

    def "sets up a stub for DataProvider"() {
        given: 'The service uses a stubbed instance of DataProvider'
        def dataProvider = Stub(DataProvider.class)
        dataProvider.provideData() >> "Stubbed data"

        def service = new Service(dataProvider)

        when: 'The service is requested to query data'
        def result = service.data

        then: 'The size of the data returned by the service should be 1'
        result.size() == 1
        // 1 * dataProvider.provideData // Won't work, DataProvider is not a mock
    }

    def "sets up a mock for a dependency of the service"() {
        given: 'The service uses a mocked instance of some object'
        def someObject = Mock(Object.class)
        def service = new Service(new DataProvider(), someObject)

        when: 'The service is requested to query data'
        def result = service.data

        then: 'The size of the data returned by the service should be 1'
        result.size() == 2
        1 * someObject.hashCode()
        1 * someObject.equals({
            anotherObj -> anotherObj == 'anotherObjectHere'
        })
    }

    @Ignore
    def "tests MyInt"() {
        given:
        def myInt = new MyInt(2)

        when:
        def result = myInt."+"(3)

        then:
        result == 5
    }

    class MyInt {

        private int value;

        MyInt(value) {
            this.value = value
        }

        def "+"(int anotherValue) {
            return value + anotherValue
        }
    }
}
