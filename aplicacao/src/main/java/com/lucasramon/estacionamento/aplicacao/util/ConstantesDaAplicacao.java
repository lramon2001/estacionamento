package com.lucasramon.estacionamento.aplicacao.util;

public class ConstantesDaAplicacao {

    // Descrição da API
    public static final String DESCRICAO_API = "API de Estacionamento - Lucas Ramon de Oliveira";
    // Constantes de configuração
    public static final String CONTEUDO_JSON = "application/json";
    public static final String DISPOSICAO_DO_CONTEUDO = "Content-Disposition";
    public static final String ANEXO_NOME_ARQUIVO = "attachment; filename=faturamento.pdf";

    // Rotas da API
    public static final String ROTA_ACESSOS = "/acessos";
    public static final String ROTA_VEICULOS = "/veiculos";
    public static final String ROTA_PROPRIETARIOS = "/proprietarios";
    public static final String ROTA_VEICULOS_MENSALISTAS = "/mensalistas";
    public static final String ROTA_VEICULOS_ROTATIVOS = "/rotativos";
    public static final String ROTA_VEICULOS_USUARIOS = "/usuarios";
    public static final String ROTA_AUTENTICACAO = "/autenticacao";
    public static final String ROTA_PAINEL = "/painel";
    public static final String ROTA_FATURAMENTO = "/faturamento";
    public static final String ROTA_GERAR_PDF = "/gerar-pdf";

    public static final String ID_CAMINHO = "/{id}";
    public static final String ID_PARAMETRO = "id";
    public static final String RECURSO_LISTAR = "listar";
}
