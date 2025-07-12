package com.lucasramon.estacionamento.aplicacao.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.aplicacao.configuracoes.ConfiguracoesDeUsuarioRaiz;
import com.lucasramon.estacionamento.aplicacao.conversores.ConversorDeUsuario;
import com.lucasramon.estacionamento.aplicacao.requisicoes.UsuarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.UsuarioResposta;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.BuscarUsuarioPorIDCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.BuscarUsuarioPorNomeCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.CadastrarUsuarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.EditarUsuarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.ExcluirUsuarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.ListarUsuariosCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.usuario.VerificarSeExisteUsuarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;
import com.lucasramon.estacionamento.dominio.util.Paginacao;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
@Service
public class ServicoDeUsuario
        implements InterfaceDeServico<Long, UsuarioRequisicao, UsuarioResposta>, UserDetailsService {

    private final CadastrarUsuarioCasoDeUso cadastrarUsuarioCasoDeUso;
    private final EditarUsuarioCasoDeUso editarUsuarioCasoDeUso;
    private final ExcluirUsuarioCasoDeUso excluirUsuarioCasoDeUso;
    private final ListarUsuariosCasoDeUso listarUsuariosCasoDeUso;
    private final BuscarUsuarioPorIDCasoDeUso buscarUsuarioPorIDCasoDeUso;
    private final BuscarUsuarioPorNomeCasoDeUso buscarUsuarioPorNomeCasoDeUso;
    private final VerificarSeExisteUsuarioCasoDeUso verificarSeExisteUsuarioCasoDeUso;

    @Autowired
    private ConversorDeUsuario conversorDeUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfiguracoesDeUsuarioRaiz configuracoesDeUsuarioRaiz;

    public ServicoDeUsuario(
            RepositorioDeUsuarios repositorioDeUsuarios) {
        this.cadastrarUsuarioCasoDeUso = new CadastrarUsuarioCasoDeUso(repositorioDeUsuarios);
        this.editarUsuarioCasoDeUso = new EditarUsuarioCasoDeUso(repositorioDeUsuarios);
        this.excluirUsuarioCasoDeUso = new ExcluirUsuarioCasoDeUso(repositorioDeUsuarios);
        this.listarUsuariosCasoDeUso = new ListarUsuariosCasoDeUso(repositorioDeUsuarios);
        this.buscarUsuarioPorIDCasoDeUso = new BuscarUsuarioPorIDCasoDeUso(repositorioDeUsuarios);
        this.buscarUsuarioPorNomeCasoDeUso = new BuscarUsuarioPorNomeCasoDeUso(repositorioDeUsuarios);
        this.verificarSeExisteUsuarioCasoDeUso = new VerificarSeExisteUsuarioCasoDeUso(repositorioDeUsuarios);
    }

    @Override
    public void cadastrar(UsuarioRequisicao input) {
        try {
            input.setSenha(
                    this.passwordEncoder.encode(input.getSenha()));
            this.cadastrarUsuarioCasoDeUso.executar(
                    this.conversorDeUsuario.paraEntidade(input));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar usuário." + e.getMessage());
        }
    }

    @Override
    public void editar(UsuarioRequisicao input) {
        try {
            input.setSenha(
                    this.passwordEncoder.encode(input.getSenha()));
            this.editarUsuarioCasoDeUso.executar(
                    this.conversorDeUsuario.paraEntidade(input));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar usuário.");
        }
    }

    @Override
    public void excluir(Long id) {
        try {
            this.excluirUsuarioCasoDeUso.executar(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir usuário.");
        }
    }

    @Override
    public UsuarioResposta buscarPorId(Long id) {
        try {
            return this.conversorDeUsuario.paraOutput(
                    this.buscarUsuarioPorIDCasoDeUso.executar(id));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário por ID.");
        }
    }

    @Override
    public Page<UsuarioResposta> listar(int pagina, int tamanho) {
        try {
            Page<UsuarioResposta> usuarios = this.listarUsuariosCasoDeUso.executar(
                    Paginacao.builder().pagina(pagina).tamanho(tamanho).build())
                    .map(this.conversorDeUsuario::paraOutput);
            return usuarios;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar usuários.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String nomeDeUsuario) throws UsernameNotFoundException {
        try {
            return this.conversorDeUsuario.paraUserDetails(
                    this.buscarUsuarioPorNomeCasoDeUso.executar(nomeDeUsuario));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean verificarSeTabelaDeUsuariosEstaVazia(){
        try {
            return this.verificarSeExisteUsuarioCasoDeUso.executar(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cadastrarUsuarioRaiz(){
         if(this.verificarSeTabelaDeUsuariosEstaVazia()){
            try {
                Usuario usuario = configuracoesDeUsuarioRaiz.novoUsuarioRaiz();
                usuario.setNomeDeUsuario(usuario.getNomeDeUsuario().trim());
                usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha().trim()));
                this.cadastrarUsuarioCasoDeUso.executar(usuario);
            } catch (Exception e) {
            e.printStackTrace();
            }
         }
    }

}
