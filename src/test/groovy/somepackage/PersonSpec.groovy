package somepackage

import basics.Person
import spock.lang.Specification

class PersonSpec extends Specification {

    def 'tests access to the basics.Person class which is in another package'() {
        given:
        Person p = new Person()
        p.setName('John') // Automatically generated, because name is public
        p.setLastName('Doe')
        p.name = 'Jo'
        println "${p.name} ${p.lastName}"
    }
}
