package solanteq.test.task.data.processing.service;

import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class CalculationService {

    private static final BiFunction<Integer, Integer, Integer> F1 = (x, y) -> x - y;
    private static final BiFunction<Integer, Integer, Integer> F2 = (x, y) -> x + y;

    @SneakyThrows
    public Integer calculateF1(Pair<Integer, Integer> arguments) {
        return F1.apply(arguments.getLeft(), arguments.getRight());
    }

    @SneakyThrows
    public Integer calculateF2(Pair<Integer, Integer> arguments) {
        return F2.apply(arguments.getLeft(), arguments.getRight());
    }
}
