package com.attendanceservice.attendance.service;

import com.attendanceservice.attendance.model.Word;
import com.attendanceservice.attendance.repository.DictionaryRepository;
import com.attendanceservice.attendance.util.RandomString;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DictService {

    private final DictionaryRepository dictRepository;

    @Autowired
    public DictService(DictionaryRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    public Word getWordDefination(String searchWord){

        Word www = dictRepository.findByWord(searchWord);

        if (www == null) {
            www = new Word("Nothing found", "Nothing found");
        }

        return www;

    }

}
