package br.com.truvainfo.zoolyapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDTO {

    private String jwt;

}
