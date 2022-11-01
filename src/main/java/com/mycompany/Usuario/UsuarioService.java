package com.mycompany.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository repo;

    public List<Usuario> listALL(){
        return (List<Usuario>) repo.findAll();
    }

    public void save(Usuario user) {
        repo.save(user);
    }
    public Usuario get(Integer id) throws UsuarioNotFoundException {
        Optional<Usuario> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UsuarioNotFoundException("could not find any users with ID" + id);
    }

    public void delete(Integer id) throws UsuarioNotFoundException {
        Long count = repo.countById(id);
        if (count ==null|| count == 0){
            throw new UsuarioNotFoundException("could not find any users with ID" + id);

        }
        repo.deleteById(id);
    }



}
