package br.com.gabriel.gestaoagricola.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
    private final int idVenda;
    private final Colheita colheita;
    private LocalDate dataVenda;
    private int quantidadeVendida;
    private BigDecimal valorUnitario;
    private final BigDecimal valorTotal;

    public Venda(int idVenda, Colheita colheita, LocalDate dataVenda, int quantidadeVendida, BigDecimal valorUnitario) {

        if (idVenda <= 0) {
            throw new IllegalArgumentException("O Id da venda deve ser positivo");
        }
        if (colheita == null) {
            throw new IllegalArgumentException("A Colheita deve existir");
        }
        if (dataVenda == null) {
            throw new IllegalArgumentException("A Data de Venda deve existir");
        } else if (dataVenda.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A Data de Venda não pode ser futura");
        }
        if (quantidadeVendida <= 0) {
            throw new IllegalArgumentException("A Quantidade vendida deve ser positiva");
        }
        if (valorUnitario == null) {
            throw new IllegalArgumentException("O Valor da venda deve existir");
        } else if (valorUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O Valor da venda deve ser maior que zero");
        }

        this.idVenda = idVenda;
        this.colheita = colheita;
        this.dataVenda = dataVenda;
        this.quantidadeVendida = quantidadeVendida;
        this.valorUnitario = valorUnitario;
        validarQuantidadeVendida();
        valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidadeVendida));
    }

    public int getIdVenda() {
        return idVenda;
    }

    public Colheita getColheita() {
        return colheita;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    private void validarQuantidadeVendida() {
        if (quantidadeVendida > colheita.getQuantidadeColhida() - colheita.getPerdas()) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser maior do que a quantidade disponível (quantidade colhida - perdas)");
        }
    }

    @Override
    public String toString() {
        return "[Id: " + getIdVenda() + " | Colheita Vendida: " + getColheita() + " | Data da Venda: " + getDataVenda() + " | Quantidade vendida: " +  getQuantidadeVendida() + " | Valor unitário: " + getValorUnitario() + " | Valor Total: " + getValorTotal() + "]";
    }
}
