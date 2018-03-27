package solanteq.test.task.data.processing.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@AllArgsConstructor
public class SimpleProfiler {

    private final LogManager logManager;

    Object profile(ProceedingJoinPoint joinPoint, LogLevel logLevel) throws Throwable {

        long startMillis = System.currentTimeMillis();

        try {
            logManager.log(
                    logLevel,
                    String.format("%s execution started", joinPoint.getSignature().toShortString()));
            return joinPoint.proceed();
        } finally {
            long endMillis = System.currentTimeMillis();
            logManager.log(
                    logLevel,
                    getFormattedDurationMsg(startMillis, endMillis, joinPoint));
        }
    }

    private static String getFormattedDurationMsg(long startTime, long endTime, ProceedingJoinPoint joinPoint) {

        Duration duration = Duration.of(endTime - startTime, ChronoUnit.MILLIS);

        return "\n-----------------------------------------\n" +
                String.format("%s execution duration is %s", joinPoint.getSignature().toShortString(), duration) +
                "\n-----------------------------------------\n";
    }
}