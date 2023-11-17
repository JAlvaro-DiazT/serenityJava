package util;

import com.github.javafaker.Faker;
import dto.GenerateTokenRecord;
import dto.RegisterUserRecord;
import dto.UsuarioImcompletoRecord;

import java.util.List;

public class DataProvider {
    Faker faker= new Faker();

    public RegisterUserRecord getAdmin(){
        return new RegisterUserRecord("admin","admin", List.of("admin"));
    }

    public RegisterUserRecord getUser(){
        return new RegisterUserRecord(getUsername(),getPassword(),List.of("user"));
    }

    public RegisterUserRecord getUserWithOutUsername(){
        return new RegisterUserRecord(null,getPassword(),List.of("user"));
    }

    public UsuarioImcompletoRecord getUsuarioIncompleto(){
        return new UsuarioImcompletoRecord(getUsername());
    }

    public String getUsername(){
        return faker.name().username();
    }

    public String getPassword(){
        return faker.internet().password();
    }

    public GenerateTokenRecord getAdminCredential(){
        return new GenerateTokenRecord("admin","admin");
    }

    public GenerateTokenRecord getCredential(RegisterUserRecord usuario) {
        if( usuario != null ) {
            return new GenerateTokenRecord(usuario.usuario(), usuario.clave());
        }
        return getCredential(getUser());
    }
}
