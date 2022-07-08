package uz.davron.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyUtils {

    @Bean
    public  ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
