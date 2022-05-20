package com.springboot.greencommute.mapper;

import com.springboot.greencommute.dto.UserDto;
import com.springboot.greencommute.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-19T13:48:25+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User optionalUser) {
        if ( optionalUser == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( optionalUser.getUserId() );
        userDto.setUserName( optionalUser.getUserName() );

        return userDto;
    }

    @Override
    public List<UserDto> toUserDtoList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userList.size() );
        for ( User user : userList ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }
}
