package br.com.alura.loja.pedido;

import br.com.alura.loja.pedido.acao.AcaoAposGerarPedido;
import br.com.alura.loja.orcamento.ItemOrcamento;
import br.com.alura.loja.orcamento.Orcamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GeraPedidoHandler {
    // construtor com injecao de dependencias: repository, service, etc.


     private List<AcaoAposGerarPedido> acoesAposGerarPedido;

    public GeraPedidoHandler(List<AcaoAposGerarPedido> acoes) {
        this.acoesAposGerarPedido = acoes;
    }

    public void execute(GeraPedido dados){
        Orcamento orcamento = new Orcamento();
        orcamento.adicionarItem(new ItemOrcamento(new BigDecimal("200")));
        Pedido pedido = new Pedido(dados.getCliente(), LocalDateTime.now(), orcamento);

        this.acoesAposGerarPedido.forEach( a -> a.executarAcao(pedido));

    }
}
