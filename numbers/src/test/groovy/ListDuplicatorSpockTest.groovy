import spock.lang.Specification
import com.aor.numbers.GenericListSorter
import com.aor.numbers.ListDeduplicator

class ListDuplicatorSpockTest extends Specification {
    private def list,expected
    def setup() {
        list = Arrays.asList(1,2,4,2,5)
        expected = Arrays.asList(1,2,4,5)
    }
    def 'deduplicate'() {
        given:
        GenericListSorter sorted = Mock(GenericListSorter)
        sorted.sort() >> Arrays.asList(1,2,2,4,5)
        ListDeduplicator deduplicated =  new ListDeduplicator()
        when:
        List<Integer> r = deduplicated.deduplicate(list,sorted)
        then:
        r == expected
    }
}