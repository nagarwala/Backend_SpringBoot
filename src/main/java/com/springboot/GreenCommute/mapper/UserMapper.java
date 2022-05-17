package com.springboot.GreenCommute.mapper;

import com.springboot.GreenCommute.dto.UserDto;
import com.springboot.GreenCommute.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {User.class, UserDto.class})
public interface UserMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(source = "userId",target = "userId")
    @Mapping(source = "userName", target = "userName")
    UserDto toUserDto(User optionalUser);

    List<UserDto> toUserDtoList(List<User> userList);

}
