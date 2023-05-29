package com.secil.mapper;

import com.secil.dto.request.ToAuthUserProfileUpdateRequestDto;
import com.secil.repository.entity.Address;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAddressMapper {
    IAddressMapper INSTANCE= Mappers.getMapper(IAddressMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateAddress(ToAuthUserProfileUpdateRequestDto dto, @MappingTarget Address address);
    Address saveAddress(final ToAuthUserProfileUpdateRequestDto dto);
}
