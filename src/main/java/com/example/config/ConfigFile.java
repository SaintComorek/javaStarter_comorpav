package com.example.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class ConfigFile {

    private ModelMapper modelMapper;
    @Bean
    public ModelMapper modelMapper() {
        modelMapper = new ModelMapper();
       modelMapper.getConfiguration()
                        .setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

}
