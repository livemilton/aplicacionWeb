package com.mycompany;

import com.mycompany.Usuario.Usuario;
import com.mycompany.Usuario.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UsuarioRepositoryTest {

    @Autowired private UsuarioRepository repo;

    @Test
    public void testAddNew(){
        Usuario user= new Usuario();
        user.setEmail("jhon@hotmail.com");
        user.setPassword("livemilton1234");
        user.setFirstName("jhon");
        user.setLastname("Muñoz");

        Usuario savedUser = repo.save(user);


        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<Usuario> users = repo.findAll();
        assertThat(users).hasSizeGreaterThan(0);

        for (Usuario user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId =1;
        Optional<Usuario> optionalUsuario = repo.findById(userId);
        Usuario user =optionalUsuario.get();
        user.setPassword("hola2022");
        repo.save(user);

        Usuario updateUser =repo.findById(userId).get();

        assertThat(updateUser.getPassword()).isEqualTo("hola2022");
    }
    @Test
    public void testGet(){
        Integer userId =2;
        Optional<Usuario> optionalUsuario = repo.findById(userId);
        assertThat(optionalUsuario).isPresent();
        System.out.println(optionalUsuario.get());
    }

    @Test
    public void testDelete(){
        Integer userId =2;
        repo.deleteById(userId);

        Optional<Usuario> optionalUsuario = repo.findById(userId);
        assertThat(optionalUsuario).isNotPresent();
    }


}
