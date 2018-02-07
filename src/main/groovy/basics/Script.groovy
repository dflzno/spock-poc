package basics

import groovy.transform.Field

println '''\
This is a script
And there's no need to put it in a class' body, the compiler will turn this
into a class that extends groovy.lang.Script
'''

new ClassInScript().sayHello()

var = 1
printVar()
def printVar() {
    println var //has access to var through script binding, optionally use @Field to make them instance variables
}

class ClassInScript {
    def sayHello() {
        println 'Hello'
    }
}

// coercing a instance to a interface
interface Runner {
    def run()
}

class MarathonRunner { // doesn't implement Runner interface
    def run() {
        println "Running a marathon..."
    }
}

 class TreadmillRunner {
    def letsSayRun() {
        println "Running on a treadmill..."
    }
 }

marathonRunner = new MarathonRunner()
println marathonRunner.class.name
marathonRunner = marathonRunner as Runner
println marathonRunner.class.name
marathonRunner.run()

treadmillRunner = new TreadmillRunner()
treadmillRunner = treadmillRunner as Runner // still possible
println treadmillRunner.class.name
//treadmillRunner.run() // this will fail in runtime
treadmillRunner.letsSayRun() // surprisingly, this works


