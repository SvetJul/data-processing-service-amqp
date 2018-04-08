package solanteq.test.task.data.processing.messaging.listener;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.messaging.channel.CalculationCompletedChannel;
import solanteq.test.task.data.processing.messaging.event.CalculationCompletedEvent;
import solanteq.test.task.data.processing.service.ResultHandler;

import static solanteq.test.task.data.processing.service.dispatcher.AbstractCalculationDispatcher.PROCESSING_ID_HEADER;

@Service
@AllArgsConstructor
public class CalculationCompletedEventListener {

    private final ResultHandler resultHandler;

    @StreamListener(value = CalculationCompletedChannel.INPUT)
    public void acceptCalculationResult(CalculationCompletedEvent event,
                                        @Header(name = PROCESSING_ID_HEADER) String processingId) {

        resultHandler.handle(processingId, event.getResult());
    }
}
