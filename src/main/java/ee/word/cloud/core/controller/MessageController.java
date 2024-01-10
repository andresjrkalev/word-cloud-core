package ee.word.cloud.core.controller;

import ee.word.cloud.core.model.Message;
import ee.word.cloud.core.service.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static ee.word.cloud.core.common.Constant.*;

@CrossOrigin
@RestController
@RequestMapping(ENDPOINT_MESSAGE)
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> read() {
        return messageService.read();
    }

    @PostMapping(produces = {PRODUCES_TEXT})
    public void create(@RequestParam(PARAMETER_FILE) MultipartFile file) {
        messageService.create(file);
    }
}
