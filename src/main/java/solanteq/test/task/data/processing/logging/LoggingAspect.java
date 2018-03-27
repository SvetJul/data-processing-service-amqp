package solanteq.test.task.data.processing.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@AllArgsConstructor
public class LoggingAspect {

    private final SimpleProfiler profiler;

    @Around("@annotation(loggable)")
    public Object loggable(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {

        return profiler.profile(joinPoint, loggable.logLevel());
    }
}