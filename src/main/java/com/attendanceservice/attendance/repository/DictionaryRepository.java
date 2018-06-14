package com.attendanceservice.attendance.repository;

import com.attendanceservice.attendance.model.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DictionaryRepository extends CrudRepository<Word, UUID> {

    @Query(value = "SELECT * FROM dictionary WHERE word ILIKE %?1%", nativeQuery = true)
    Word findByWord(String word);

}
