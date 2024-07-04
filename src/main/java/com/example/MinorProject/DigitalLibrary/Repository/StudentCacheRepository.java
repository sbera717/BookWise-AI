package com.example.MinorProject.DigitalLibrary.Repository;

import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RedisHash
public class StudentCacheRepository {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final String KEY_PREFIX = "std::";

    public void set(CreateStudentResponse student){
        if(student.getId() == 0){
            return;
        }
        String key = KEY_PREFIX + student.getId();
        redisTemplate.opsForValue().set(key,student,3600, TimeUnit.SECONDS);
    }
    public CreateStudentResponse get(int id){
        if(id == 0){
            return null;
        }
        String key = KEY_PREFIX + id;
        return  (CreateStudentResponse) redisTemplate.opsForValue().get(key);
    }


}
