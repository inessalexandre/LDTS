import spock.lang.Specification
import com.aor.numbers.GenericListDeduplicator
import com.aor.numbers.ListAggregator

class ListAggregatorSpockTest extends Specification {
    private def list

    def setup() {
        list = Arrays.asList(1, 2, 4, 2)
    }

    def 'sum'() {
        given:
        ListAggregator aggregator = new ListAggregator()
        when:
        int sum = aggregator.sum(list)
        then:
        sum == 9
    }

    def 'Max'() {
        given:
        ListAggregator aggregator = new ListAggregator()
        when:
        int max = aggregator.max(list)
        then:
        max == 4
    }

    def 'Min'() {
        given:
        ListAggregator aggregator = new ListAggregator()
        when:
        int min = aggregator.min(list)
        then:
        min == 1
    }

    def "distinct"() {
        given:
        def deduplicator = Mock(GenericListDeduplicator)
        deduplicator.deduplicate(Arrays.asList(1, 2, 4, 2)) >> Arrays.asList(1, 2, 4)
        ListAggregator aggregator = new ListAggregator()
        when:
        int distinct = aggregator.distinct(list, deduplicator);
        then:
        distinct == 3
    }
}