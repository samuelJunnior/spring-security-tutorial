package br.com.samueljunnior.modules.user.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.modules.user.dto.UserDto;
import br.com.samueljunnior.modules.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserEntity, UserDto> {
}
