package solanteq.test.task.data.processing.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.ProcessingContext;
import solanteq.test.task.data.processing.exception.FileNotFoundException;
import solanteq.test.task.data.processing.exception.ProcessingFailedException;
import solanteq.test.task.data.processing.logging.Loggable;
import solanteq.test.task.data.processing.service.dispatcher.F1CalculationDispatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileProcessingService {

    private final LineParser lineParser;
    private final ProcessingContextStore processingStore;
    private final F1CalculationDispatcher f1CalculationDispatcher;

    @Value("${processing.timeout.minutes}")
    private long defaultTimeoutMinutes;

    @Loggable(logLevel = LogLevel.INFO)
    public Integer process(Path filePath) {
        File file = filePath.toFile();

        if (!file.exists())
            throw new FileNotFoundException();

        return process(file);
    }

    @SneakyThrows
    private Integer process(File file) {
        ProcessingContext processingContext = processingStore.create();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            long linesCount = bufferedReader.lines()
                    .map(lineParser::parse)
                    .peek(pair -> f1CalculationDispatcher.requestCalculation(pair, processingContext.getProcessingId()))
                    .count();
            processingContext.finishDataProviding(linesCount - 1);
        }

        Optional<Integer> result = processingContext.getResult(defaultTimeoutMinutes, TimeUnit.MINUTES);
        processingStore.delete(processingContext.getProcessingId());

        return result.orElseThrow(ProcessingFailedException::new);
    }
}
