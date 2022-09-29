import com.aor.numbers.PositiveFilter
import spock.lang.Specification
import com.aor.numbers.GenericListFilter
import com.aor.numbers.ListFilter

class ListFiltererSpockTest extends Specification{
    private def list,expected,result
    def setup() {
        list = Arrays.asList(1,2,-4)
        expected = Arrays.asList()
    }
    def 'filterer'() {
        given:
        GenericListFilter stubfillter = Mock(GenericListFilter)
        GenericListFilter filter = new PositiveFilter();
        ListFilter listfilter = new ListFilter(filter);
        when:
        result = listfilter.filter(list,stubfillter);
        then:
        result==expected;
    }
}