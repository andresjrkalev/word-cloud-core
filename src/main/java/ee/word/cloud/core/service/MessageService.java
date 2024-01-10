package ee.word.cloud.core.service;

import ee.word.cloud.core.model.Message;
import ee.word.cloud.core.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static ee.word.cloud.core.common.Constant.QUEUE;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;

    public MessageService(MessageRepository messageRepository, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Message> read() {
        return messageRepository.findAll();
    }

    public void create(MultipartFile file) {
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            rabbitTemplate.convertAndSend(QUEUE, content);
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File corrupted!");
        }
    }
}
