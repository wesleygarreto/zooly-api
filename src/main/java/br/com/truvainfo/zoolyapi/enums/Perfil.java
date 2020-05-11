package br.com.truvainfo.zoolyapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Perfil {

    ADMIN(1),
    GESTOR(2),
    MEDICO(3),
    FUNCIONARIO(4);

    private final Integer valor;

    public static Perfil getPerfil(Boolean valor) {
        for (Perfil indicador : Perfil.values()) {
            if (indicador.getValor().equals(valor)) {
                return indicador;
            }
        }
        return null;
    }
}
