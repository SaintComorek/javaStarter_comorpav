package com.example;

import com.example.dto.GroupDto;
import com.example.dto.NoteDto;
import com.example.dto.TagDto;
import com.example.dto.UserDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaStarterComorpavApplicationTests {

    @Test
    void contextLoads() {
        TagDto tagDto = new TagDto(1  , "default");
        NoteDto noteDto = new NoteDto(1 , tagDto , "asdasdadadadsadasfsdfdgr5errgdfbvdberbebe");
        GroupDto groupDto = new GroupDto(1 ,noteDto , tagDto);
        UserDto userDto = new UserDto("sss" , "aaa" , 22, groupDto , noteDto , tagDto );
        Gson gson = new Gson();
        String value = gson.toJson(userDto);
        System.out.println();
    }

}
