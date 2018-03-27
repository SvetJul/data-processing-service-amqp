package solanteq.test.task.data.processing.messaging.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface F2CalculationChannel {

    String INPUT = "f2CalculationInputChannel";
    String OUTPUT = "f2CalculationOutputChannel";

    @Input(F2CalculationChannel.INPUT)
    SubscribableChannel input();

    @Output(F2CalculationChannel.OUTPUT)
    MessageChannel output();
}
