package solanteq.test.task.data.processing.messaging.listener;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.messaging.channel.CalculationCompletedChannel;
import solanteq.test.task.data.processing.messaging.channel.F1CalculationChannel;
import solanteq.test.task.data.processing.messaging.channel.F2CalculationChannel;
import solanteq.test.task.data.processing.messaging.event.CalculationCompletedEvent;
import solanteq.test.task.data.processing.messaging.event.CalculationRequestedEvent;
import solanteq.test.task.data.processing.service.CalculationService;

@Service
@AllArgsConstructor
public class CalculationRequestedEventListener {

    private final CalculationService calculationService;

    @StreamListener(F1CalculationChannel.INPUT)
    @SendTo(CalculationCompletedChannel.OUTPUT)
    public CalculationCompletedEvent acceptF1CalculationEvent(CalculationRequestedEvent event) {
        return new CalculationCompletedEvent(calculationService.calculateF1(event.getArguments()));
    }

    @StreamListener(F2CalculationChannel.INPUT)
    @SendTo(CalculationCompletedChannel.OUTPUT)
    public CalculationCompletedEvent acceptF2CalculationEvent(CalculationRequestedEvent event) {
        return new CalculationCompletedEvent(calculationService.calculateF2(event.getArguments()));
    }
}
