import spock.lang.Specification
import com.aor.numbers.ListSorter

class ListSorterSpockTest extends Specification {
    private List<Integer> list,expected
    def setup() {
        list = Arrays.asList(3, 2, 6, 1, 4, 5, 7)
        expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7)
    }
    def 'sort'() {
        given:
        ListSorter sorter = new ListSorter()
        when:
        List<Integer> sorted = sorter.sort(list)
        then:
        sorted == expected
    }
}
