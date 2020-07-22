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
    /*
<<<<<<< HEAD
     *  9번은 비올때, 10번은 눈올때
     *  28도이상 CODE1, 23도이상 CODE2, 20도이상 CODE3, 17도이상 CODE4, 12도이상 CODE5, 9도이상 CODE6, 5도이상 CODE7, 5도미만(영하포함) CODE8
     * */
=======
    *  9번은 비올때, 10번은 눈올때
    *  28도이상 CODE1, 23도이상 CODE2, 20도이상 CODE3, 17도이상 CODE4, 12도이상 CODE5, 9도이상 CODE6, 5도이상 CODE7, 5도미만(영하포함) CODE8
    * */
>>>>>>> origin/develop
    @GetMapping("api/v1/messages/{temperature_code}")
    public String retrieveRandomMessages(@PathVariable int temperature_code) {
        String randomMessage = service.getRandomMessageByCode(temperature_code);

        if (randomMessage == null) {
            log.info("MessageAPI>>>>when tempCode:"+ temperature_code + " : randomMessage is null");
        }
        return randomMessage;
    }
}
