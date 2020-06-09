package br.com.truvainfo.zoolyapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageEmailDTO {

    private String from;

    private List<String> receivers;

    private String subject;

    private String body;

}
