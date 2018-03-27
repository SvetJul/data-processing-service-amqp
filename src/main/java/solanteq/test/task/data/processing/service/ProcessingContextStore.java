package solanteq.test.task.data.processing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import solanteq.test.task.data.processing.ProcessingContext;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProcessingContextStore {

    private static final Map<String, ProcessingContext> store = new ConcurrentHashMap<>();

    @Value("${processing.queue.capacity}")
    private int processingQueueCapacity;

    public ProcessingContext create() {
        String processingId = generateProcessingId();
        ProcessingContext calculationContext = new ProcessingContext(processingQueueCapacity, processingId);
        store.put(processingId, calculationContext);
        return calculationContext;
    }

    private String generateProcessingId() {
        return UUID.randomUUID().toString();
    }

    public Optional<ProcessingContext> get(String processingId) {
        return Optional.ofNullable(store.get(processingId));
    }

    @Async
    public void delete(String processingId) {
        if (store.containsKey(processingId))
            store.remove(processingId);
    }
}
