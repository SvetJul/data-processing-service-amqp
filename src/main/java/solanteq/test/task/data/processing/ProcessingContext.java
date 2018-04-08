package solanteq.test.task.data.processing;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ProcessingContext {


    private long totalOperationsCount = 0;
    private long currentOperationsCount = 0;
    private boolean dataProvidingFinished = false;
    private final LinkedBlockingQueue<Integer> arguments;
    private BlockingQueue<Integer> result = new LinkedBlockingQueue<>(1);
    @Getter
    private final String processingId;

    public ProcessingContext(int capacity, String processingId) {
        arguments = new LinkedBlockingQueue<>(capacity);
        this.processingId = processingId;
    }

    @SneakyThrows
    public synchronized Optional<Integer> getNextArgument() {
        if (!isFullyProcessed() & hasNext()) {

            Integer value = arguments.take();

            currentOperationsCount++;

            return Optional.of(value);

        } else {
            return Optional.empty();
        }
    }

    private synchronized boolean isFullyProcessed() {
        return dataProvidingFinished && currentOperationsCount == totalOperationsCount;
    }

    private synchronized boolean hasNext() {
        return arguments.size() > 0;
    }

    @SneakyThrows
    public synchronized void supplyArgument(Integer candidate) {
        if (!isFullyProcessed()) {
            arguments.put(candidate);
        } else {
            result.put(candidate);
        }
    }

    public synchronized void finishDataProviding(long totalOperationsCount) {
        this.totalOperationsCount = totalOperationsCount;
        this.dataProvidingFinished = true;
    }

    @SneakyThrows
    public Optional<Integer> getResult(long timeout, TimeUnit timeUnit) {

        return Optional.ofNullable(result.poll(timeout, timeUnit));
    }
}
