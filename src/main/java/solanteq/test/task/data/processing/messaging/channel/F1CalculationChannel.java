package solanteq.test.task.data.processing.messaging.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface F1CalculationChannel {

    String INPUT = "f1CalculationInputChannel";
    String OUTPUT = "f1CalculationOutputChannel";

    @Input(F1CalculationChannel.INPUT)
    SubscribableChannel input();

    @Output(F1CalculationChannel.OUTPUT)
    MessageChannel output();
}
