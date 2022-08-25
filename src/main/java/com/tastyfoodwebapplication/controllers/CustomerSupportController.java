package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.time.*;

@Controller
public class CustomerSupportController {
    private MessageRepository messageRepository;

    @Autowired
    public CustomerSupportController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(value = "/customer-support/send", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute @Valid Message message, BindingResult bindingResult, Model model) {
        if (message.getEmail().equals("") && message.getPhoneNumber().equals("")) {
            bindingResult .rejectValue("email", "error.message", "You should provide either an email or a phone number");
            bindingResult .rejectValue("phoneNumber", "error.message", "You should provide either an email or a phone number");
        }

        if (!bindingResult.hasErrors()) {
            message.setSentAt(LocalDateTime.now());
            messageRepository.save(message);
            return "redirect:/";
        }

//        model.addAttribute("message", new Message("", "", "", "", LocalDateTime.now()));
        return "/contact";
    }

}
