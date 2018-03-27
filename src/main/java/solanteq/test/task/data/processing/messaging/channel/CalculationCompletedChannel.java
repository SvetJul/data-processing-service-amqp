package solanteq.test.task.data.processing.messaging.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CalculationCompletedChannel {

    String INPUT = "calculationCompletedInputChannel";
    String OUTPUT = "calculationCompletedOutputChannel";

    @Input(CalculationCompletedChannel.INPUT)
    SubscribableChannel input();

    @Output(CalculationCompletedChannel.OUTPUT)
    MessageChannel output();
}
