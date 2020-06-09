package br.com.truvainfo.zoolyapi.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserChangeDTO {

    private String email;
    private String password;
    private String hash;

}
