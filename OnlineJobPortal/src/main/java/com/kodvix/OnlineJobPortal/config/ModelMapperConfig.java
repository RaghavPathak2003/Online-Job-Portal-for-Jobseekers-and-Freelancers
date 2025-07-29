package com.kodvix.OnlineJobPortal.config;

import com.kodvix.OnlineJobPortal.dto.CertificationDto;
import com.kodvix.OnlineJobPortal.entity.Certification;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ✅ Skip mapping credentialId to id
        modelMapper.typeMap(CertificationDto.class, Certification.class)
                .addMappings(mapper -> mapper.skip(Certification::setId));

        return modelMapper;
    }
}
