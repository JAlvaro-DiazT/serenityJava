package dto;

import java.util.Collection;

public record RegisterUserRecord(String usuario, String clave, Collection<String> roles) {
}
