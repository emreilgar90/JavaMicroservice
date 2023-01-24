package com.emreilgar.mapper;

import com.emreilgar.dto.request.RegisterRequestDto;
import com.emreilgar.dto.response.RegisterResponseDto;
import com.emreilgar.repository.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
/*
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring") //eşleşmeyeni görmezden gel
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth fromRegisterRequestDto(final RegisterRequestDto dto);

    @Mapping(source = "id" , target = "authid")      //source kaynak, target hedef, birbirine mappledik
    RegisterResponseDto fromAuth(final Auth auth); //birbirine mapledik !*/


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth fromRegisterRequestDto(final RegisterRequestDto dto);

    @Mappings({
            @Mapping(source = "id", target = "authid")
    })
    RegisterResponseDto fromAuth(final Auth auth);
}


