package solanteq.test.task.data.processing.service.dispatcher;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import solanteq.test.task.data.processing.messaging.event.CalculationRequestedEvent;

@RequiredArgsConstructor
public abstract class AbstractCalculationDispatcher {

    public static final String PROCESSING_ID_HEADER = "processing-id";

    private final MessageChannel calculationChannel;

    public void requestCalculation(Pair<Integer, Integer> arguments, String processingId) {

        Message<CalculationRequestedEvent> message = MessageBuilder
                .withPayload(new CalculationRequestedEvent(arguments.getLeft(), arguments.getRight()))
                .setHeader(PROCESSING_ID_HEADER, processingId)
                .build();

        calculationChannel.send(message);
    }
}
