package com.secil.mapper;

import com.secil.dto.request.AddPointRequestDto;
import com.secil.repository.entity.Point;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPointMapper {
    IPointMapper INSTANCE= Mappers.getMapper(IPointMapper.class);
    Point addPointDtoToPoint(final AddPointRequestDto dto);

}
