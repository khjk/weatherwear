package com.kitri.weatherwear.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MessageApiController {
    private MessageService service;

    MessageApiController(MessageService service){
        this.service = service;
    }

    @GetMapping("api/v1/messages")
    public List<Message> retrieveAllMessages() {
        return service.getAllMessages();
    }

    @GetMapping("api/v1/messages/{temperature_code}")
    public String retrieveUser(@PathVariable int temperature_code) {
        String randomMessage = service.getRandomMessageByCode(temperature_code);

        if (randomMessage == null) {
            //log.info("MessageAPI>>>>when tempcode:"+ temperature_code + " : randomMessage is null");
        }
        return randomMessage;
    }
}
