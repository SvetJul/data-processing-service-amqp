package solanteq.test.task.data.processing.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solanteq.test.task.data.processing.service.FileProcessingService;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/processing")
@AllArgsConstructor
public class ProcessingController {

    private static final String DEFAULT_FILE_NAME = "C:\\Users\\Yulia_Yo\\solanteq.txt";
    private final FileProcessingService processingService;

    @GetMapping
    public Integer getProcessingResult(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) {
        Path path = Paths.get(fileName);

        return processingService.process(path);
    }
}
