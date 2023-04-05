package com.example;

import com.example.dto.*;
import com.example.model.Group;
import com.example.repository.GroupRepo;
import com.example.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaStarterComorpavApplicationTests {

    @Test
    void contextLoads() {

        UserService userService = new UserService();



        UserDto userDto = new UserDto();
        userDto.setName("Rocky");
        userDto.setLastName("marciano");
        userDto.setStatus("Admin");
        userDto.setAge(22);
        BaseUserDto baseUserDto = new BaseUserDto();
        baseUserDto.setUpBaseData(userDto);


        /*

        TagDto tagDto = new TagDto(1  , "default" , baseUserDto );
       // NoteDto noteDto = new NoteDto(1 ,  , "asdasdadadadsadasfsdfdgr5errgdfbvdberbebe" ,baseUserDto);
        NoteDto noteDto2 = new NoteDto(2 , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec vitae arcu. Sed convallis magna eu sem." +
                "Maecenas libero. Etiam dictum tincidunt diam. Curabitur sagittis hendrerit ante. Et harum quidem rerum facilis est et" +
                "expedita distinctio. Pellentesque ipsum. Fusce suscipit libero eget elit. Class aptent taciti sociosqu ad litora torquent " +
                "per conubia nostra, per inceptos hymenaeos. In rutrum. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, se" ,baseUserDto);

        noteDto2.addToTagsList(tagDto);
        GroupDto groupDto = new GroupDto(1 ,noteDto2 , tagDto , baseUserDto);


         */
        TagDto tagDto = new TagDto(1  , "default" , baseUserDto );
        NoteDto noteDto2 = new NoteDto();
        noteDto2.setName("first");
        noteDto2.setBaseUserDto(baseUserDto);
        noteDto2.setId(1);
        noteDto2.addToTagsList(tagDto);
        noteDto2.setValue("loremipsum");

       // userDto.setNoteDtoToList(noteDto);
        userDto.setNoteDtoToList(noteDto2);
       // userDto.setGroupDtoToList(groupDto);
        userDto.setTagDtoToList(tagDto);


        Gson gson = new Gson();
        String value = gson.toJson(userDto);
        String value2 = gson.toJson(userService.convertDtoToEntity(userDto));
        System.out.println("Dto Values: " + value);
        System.out.println("Model Values: " + value2);
        System.out.println("Base Data:  " + gson.toJson(baseUserDto));
        System.out.println("Tag Data:  " + gson.toJson(tagDto));
       // System.out.println("Group Data:  " + gson.toJson(groupDto));
        System.out.println("Note Data:  " + gson.toJson(noteDto2));
        

    }

}
