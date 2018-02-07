package somepackage

import basics.Person
import spock.lang.Specification

class PersonSpec extends Specification {

    def 'tests access to the basics.Person class which is in another package'() {
        given:
        Person p = new Person()
        p.setName('John') // Automatically generated, because name is public
        p.setLastName('Doe')
        p.name = 'Jo' // This calls the setter, it's not a direct access to the field! (name has no access modifier)
        println "${p.name} ${p.lastName}"
        p.fullName = 'Not Jo Doe' // still works, even though fullName is private!!! .|.
        println p.fullName
        println p.@lastName // direct call to the field, not the getter
    }
}
