package com.secil.mapper;

import com.secil.dto.request.AddCommentRequestDto;
import com.secil.repository.entity.Comment;
import com.secil.repository.entity.Point;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentMapper {
    ICommentMapper INSTANCE= Mappers.getMapper(ICommentMapper.class);
    Comment addCommentRequestDtoToComment(final AddCommentRequestDto dto);


}
