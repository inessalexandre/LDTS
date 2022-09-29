import com.aor.numbers.PositiveFilter
import spock.lang.Specification

class PositiveFilterSpockTest extends Specification {
    PositiveFilter f = new PositiveFilter();

    def 'Accept'() {
        given:
        Integer testnumber = 2
        when:
        def res = f.accept(testnumber)
        then:
        res == true
    }
}