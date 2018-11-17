import spock.lang.Specification


/**
 * Created by mtumilowicz on 2018-11-17.
 */
class LeftCurrying extends Specification {

    def "closure left currying"() {
        given:
        def divide = { x, y -> x / y }

        when:
        def curriedDivide = divide.curry(10)

        then:
        curriedDivide(2) == 5
    }

    def "closure right currying"() {
        given:
        def divide = { x, y -> x / y }

        when:
        def curriedDivide = divide.rcurry(10)

        then:
        curriedDivide(20) == 2
    }

    def "closure ncurrying"() {
        given:
        def concat = { a, b, c, d, e -> String.join(",", a, b, c, d, e) }

        when:
        def currierConcat = concat.ncurry(0, "a", "b", "c")

        then:
        currierConcat("d", "e") == "a,b,c,d,e"
    }

    def "closure ncurrying from 2"() {
        given:
        def concat = { a, b, c, d, e -> String.join(",", a, b, c, d, e) }

        when:
        def currierConcat = concat.ncurry(2, "c", "d", "e")

        then:
        currierConcat("a", "b") == "a,b,c,d,e"
    }

    def "currying with method reference"() {
        given:
        def curriedAdd = LeftCurrying.&add.curry(5)

        expect:
        curriedAdd(2) == 7
    }

    static int add(x, y) {
        return x + y
    }
}