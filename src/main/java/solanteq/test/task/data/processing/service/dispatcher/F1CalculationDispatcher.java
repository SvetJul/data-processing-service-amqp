package solanteq.test.task.data.processing.service.dispatcher;

import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.messaging.channel.F1CalculationChannel;

@Service
public class F1CalculationDispatcher extends AbstractCalculationDispatcher {

    public F1CalculationDispatcher(F1CalculationChannel f1CalculationChannel) {
        super(f1CalculationChannel.output());
    }
}
