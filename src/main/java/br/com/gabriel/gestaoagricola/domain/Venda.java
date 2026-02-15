package br.com.gabriel.gestaoagricola.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
    private final int idVenda;
    private int idColheita;
    private LocalDate dataVenda;
    private int quantidadeVendida;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public Venda(int idVenda, int idColheita, LocalDate dataVenda, int quantidadeVendida, BigDecimal valorUnitario) {

        if (idVenda <= 0) {
            throw new IllegalArgumentException("O Id da venda deve ser positivo");
        }
        if (idColheita <= 0) {
            throw new IllegalArgumentException("O Id da Colheita deve ser positivo");
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
        this.idColheita = idColheita;
        this.dataVenda = dataVenda;
        this.quantidadeVendida = quantidadeVendida;
        this.valorUnitario = valorUnitario;
        valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidadeVendida));
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getIdColheita() {
        return idColheita;
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

    @Override
    public String toString() {
        return "[Id: " + getIdVenda() + " | Colheita Vendida: " + getIdColheita() + " | Data da Venda: " + getDataVenda() + " | Quantidade vendida: " +  getQuantidadeVendida() + " | Valor unitário: " + getValorUnitario() + " | Valor Total: " + getValorTotal() + "]";
    }
}
