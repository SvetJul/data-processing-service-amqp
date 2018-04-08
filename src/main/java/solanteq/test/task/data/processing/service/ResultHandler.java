package solanteq.test.task.data.processing.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.service.dispatcher.F2CalculationDispatcher;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ResultHandler {

    private final ProcessingContextStore processingStore;
    private final F2CalculationDispatcher f2CalculationDispatcher;

    public void handle(String processingId, Integer result) {
        processingStore.get(processingId).ifPresent(
                processingContext -> {
                    Optional<Integer> argument = processingContext.getNextArgument();
                    if (argument.isPresent()) {
                        f2CalculationDispatcher.requestCalculation(
                                ImmutablePair.of(result, argument.get()),
                                processingId);
                    } else {
                        processingContext.supplyArgument(result);
                    }
                }
        );
    }
}
