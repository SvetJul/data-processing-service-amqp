package solanteq.test.task.data.processing.service.dispatcher;

import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.messaging.channel.F2CalculationChannel;

@Service
public class F2CalculationDispatcher extends AbstractCalculationDispatcher {

    public F2CalculationDispatcher(F2CalculationChannel f2CalculationChannel) {
        super(f2CalculationChannel.output());
    }
}
