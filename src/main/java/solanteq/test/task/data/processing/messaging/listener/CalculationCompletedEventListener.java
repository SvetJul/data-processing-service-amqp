package solanteq.test.task.data.processing.messaging.listener;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.ProcessingContext;
import solanteq.test.task.data.processing.messaging.channel.CalculationCompletedChannel;
import solanteq.test.task.data.processing.messaging.event.CalculationCompletedEvent;
import solanteq.test.task.data.processing.service.dispatcher.F2CalculationDispatcher;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static solanteq.test.task.data.processing.service.dispatcher.AbstractCalculationDispatcher.PROCESSING_ID_HEADER;

@Service
@AllArgsConstructor
//TODO: extract logic
public class CalculationCompletedEventListener {

    private final Map<String, ProcessingContext> processingStore;
    private final F2CalculationDispatcher f2CalculationDispatcher;

    @StreamListener(value = CalculationCompletedChannel.INPUT)
    public void acceptCalculationResult(CalculationCompletedEvent event,
                                        @Header(name = PROCESSING_ID_HEADER) String
                                                processingId) {
        ProcessingContext processingContext = processingStore.get(processingId);

        if (isNull(processingContext))
            return;

        Optional<Integer> argument = processingContext.getNextArgument();
        if (argument.isPresent()) {
            f2CalculationDispatcher.requestCalculation(
                    ImmutablePair.of(event.getResult(), argument.get()),
                    processingId);
        } else {
            processingContext.supplyArgument(event.getResult());
        }
    }
}
