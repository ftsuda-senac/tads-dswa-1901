/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplospringsecurity.service;

import br.senac.tads.dsw.exemplospringsecurity.entidade.Papel;
import br.senac.tads.dsw.exemplospringsecurity.entidade.UsuarioSistema;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando.tsuda
 */
@Service
public class UsuarioSistemaService implements UserDetailsService {

    private static final Map<String, UsuarioSistema> USUARIOS_CADASTRADOS
            = new LinkedHashMap<>();

    static {
        USUARIOS_CADASTRADOS.put("denver", new UsuarioSistema("denver",
                "Ricardo Ramos", "abcd1234",
                Arrays.asList(new Papel("ROLE_PEAO"))));
        USUARIOS_CADASTRADOS.put("tokio", new UsuarioSistema("tokio",
                "Silene Oliveira", "abcd1234",
                Arrays.asList(new Papel("ROLE_PEAO"))));
        USUARIOS_CADASTRADOS.put("berlin", new UsuarioSistema("berlin",
                "Andres de Fonollosa", "abcd1234",
                Arrays.asList(new Papel("ROLE_PEAO"), new Papel("ROLE_FODON"))));
        USUARIOS_CADASTRADOS.put("professor", new UsuarioSistema("professor",
                "Sergio Marquina", "abcd1234",
                Arrays.asList(new Papel("ROLE_PEAO"), new Papel("ROLE_GOD"))));
    }

    @Override
    public UsuarioSistema loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (USUARIOS_CADASTRADOS.containsKey(username)) {
            return USUARIOS_CADASTRADOS.get(username);
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }

}
