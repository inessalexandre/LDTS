import spock.lang.Specification
import com.aor.numbers.DivisibleByFilter

class DivisibleByFilterSpockTest extends Specification {
    private DivisibleByFilter filter
    def 'Accept'() {
        given:
        Integer n = 5
        Integer d = 2
        when:
        filter = new DivisibleByFilter(d)
        def res = filter.accept(n)
        then:
        res == false
    }
}
