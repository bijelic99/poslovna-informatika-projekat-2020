package org.ftn.poslovnainformatika.podsistemprodajeprojekat.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {
    public ObjectMapper objectMapper(ObjectMapper objectMapper){
        objectMapper.registerModules(new JodaModule());
        return objectMapper;
    }
}
