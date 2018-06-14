package com.attendanceservice.attendance.controller;

import com.attendanceservice.attendance.model.Word;
import com.attendanceservice.attendance.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.SOAPException;
import java.util.List;
import java.util.UUID;

@RestController
public class DictionaryController {

    private final DictService dictService;

    @Autowired
    public DictionaryController(DictService dictService) {
        this.dictService = dictService;
    }

    @RequestMapping(value = "/dictionary/{word}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Word> findWordInDictonary(@PathVariable("word") String word) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(word);
        return ResponseEntity.ok(dictService.getWordDefination(word));
    }
}