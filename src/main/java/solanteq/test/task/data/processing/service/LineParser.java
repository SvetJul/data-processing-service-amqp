package solanteq.test.task.data.processing.service;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import solanteq.test.task.data.processing.exception.InvalidFormatException;

@Service
public class LineParser {

    private static final String SEPARATOR = " ";

    ImmutablePair<Integer, Integer> parse(String line) {
        try {
            String[] integers = line.split(SEPARATOR);
            return ImmutablePair.of(Integer.parseInt(integers[0]), Integer.parseInt(integers[1]));
        } catch (NumberFormatException e) {
            throw new InvalidFormatException(e);
        }
    }
}