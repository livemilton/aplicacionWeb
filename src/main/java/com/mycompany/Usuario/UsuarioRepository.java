package com.mycompany.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository <Usuario, Integer> {

    public Long countById (Integer id);
}
