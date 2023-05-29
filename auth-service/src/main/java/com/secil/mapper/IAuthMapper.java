package com.secil.mapper;

import com.secil.dto.request.NewCreateUserRequestDto;
import com.secil.dto.request.RegisterRequestDto;
import com.secil.dto.request.ToAuthUserProfileUpdateRequestDto;
import com.secil.dto.response.RegisterResponseDto;
import com.secil.rabbitmq.model.RegisterMailModel;
import com.secil.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);
    Auth fromRequestDtoToUser(final RegisterRequestDto dto);
    RegisterResponseDto authToRegisterResponseDto(final Auth auth);
    NewCreateUserRequestDto fromAuthToNewCreateUserDto(final Auth auth);
    RegisterMailModel fromAuthToRegisterMailModel(final Auth auth);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth updateAuth(ToAuthUserProfileUpdateRequestDto dto, @MappingTarget Auth auth);

}
