package solanteq.test.task.data.processing.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalculationRequestedEvent {

    private Integer firstArgument;
    private Integer secondArgument;

    public Pair<Integer, Integer> getArguments() {
        return ImmutablePair.of(firstArgument, secondArgument);
    }
}
