package solanteq.test.task.data.processing.messaging;

import org.springframework.cloud.stream.annotation.EnableBinding;
import solanteq.test.task.data.processing.messaging.channel.CalculationCompletedChannel;
import solanteq.test.task.data.processing.messaging.channel.F1CalculationChannel;
import solanteq.test.task.data.processing.messaging.channel.F2CalculationChannel;

@EnableBinding({
        F1CalculationChannel.class,
        F2CalculationChannel.class,
        CalculationCompletedChannel.class
})
public class BindingConfig {
}
