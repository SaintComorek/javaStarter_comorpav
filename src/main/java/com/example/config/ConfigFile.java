package com.example.config;

import com.example.dto.DtoCollection;
import com.example.dto.UserDto;
import com.example.model.ModelCollection;
import com.example.model.User;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class ConfigFile {

    private ModelMapper modelMapper;
    private DtoCollection dtoCollection ;
    private UserDto userDto;
    private User user;
    private ModelCollection modelCollection ;
    @Bean
    public ModelMapper modelMapper() {
        modelMapper = new ModelMapper();
       modelMapper.getConfiguration()
                        .setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

}
