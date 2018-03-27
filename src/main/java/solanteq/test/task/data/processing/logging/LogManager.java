package solanteq.test.task.data.processing.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
@Service
@AllArgsConstructor
public class LogManager {

    private static final Map<LogLevel, BiConsumer<Logger, String>> loggingStrategies = new HashMap<>();
    private static final BiConsumer<Logger, String> doNothing = (logger, msg) -> {
    };

    static {
        loggingStrategies.put(LogLevel.TRACE, Logger::trace);
        loggingStrategies.put(LogLevel.DEBUG, Logger::debug);
        loggingStrategies.put(LogLevel.INFO, Logger::info);
        loggingStrategies.put(LogLevel.WARN, Logger::warn);
        loggingStrategies.put(LogLevel.ERROR, Logger::error);
        loggingStrategies.put(LogLevel.FATAL, Logger::error);
        loggingStrategies.put(LogLevel.OFF, doNothing);
    }

    void log(LogLevel logLevel, String msg) {
        loggingStrategies.get(logLevel).accept(log, msg);
    }
}