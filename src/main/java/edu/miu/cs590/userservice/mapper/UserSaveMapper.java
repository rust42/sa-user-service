package edu.miu.cs590.userservice.mapper;

import edu.miu.cs590.userservice.dto.UserDto;
import edu.miu.cs590.userservice.dto.UserSaveDto;
import edu.miu.cs590.userservice.entity.User;
import edu.miu.cs590.userservice.util.PasswordEncoderUtil;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserSaveMapper {

    @Mappings({
            @Mapping(target ="password" ,source = "password",qualifiedByName = "encodePassword"),
            @Mapping(target ="address.address1" ,source = "address1"),
            @Mapping(target ="address.address2" ,source = "address2"),
            @Mapping(target ="address.city" ,source = "city"),
            @Mapping(target ="address.state" ,source = "state"),
            @Mapping(target ="address.country" ,source = "country"),
            @Mapping(target ="address.zipCode" ,source = "zipCode"),
    })
    User toEntity(UserSaveDto userSaveDto);

    @Mappings({
            @Mapping(source ="address.address1" ,target = "address1"),
            @Mapping(source ="address.address2" ,target = "address2"),
            @Mapping(source ="address.city" ,target = "city"),
            @Mapping(source ="address.state" ,target = "state"),
            @Mapping(source ="address.country" ,target = "country"),
            @Mapping(source ="address.zipCode" ,target = "zipCode"),
    })
    UserDto toDto(User user);

    @Named("encodePassword")
    static String encodePassword(String password) {
        return PasswordEncoderUtil.passwordEncoder().encode(password);
    }
}
