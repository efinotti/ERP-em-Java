package erpapp.Repository;

import erpapp.Entities.Produto;
import Util.ArquivoUtil;
import java.util.ArrayList;

public class ProdutoRepository {
    
    private ArrayList<Produto> listaProduto = new ArrayList<>();

    public ProdutoRepository() {
        this.listaProduto = ArquivoUtil.recuperarProdutos();
    }

    public void incluir(Produto p) {
        listaProduto.add(p);
        ArquivoUtil.salvarDados(listaProduto);
    }

    public ArrayList<Produto> listar() {
        return this.listaProduto;
    }

    public ArrayList<Produto> consultarPorNome(String termo) {
        ArrayList<Produto> filtrados = new ArrayList<>();
        
        if (termo == null || termo.trim().isEmpty()) {
            return listaProduto;
        }
        for (Produto p : listaProduto) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public Produto consultarPorId(int idBusca) {
        for (Produto p : listaProduto) {
            if (p.getId() == idBusca) {
                return p;
            }
        }
        return null;
    }

    public void alterar(Produto p) {
        Produto pOriginal = consultarPorId(p.getId());
        
        if (pOriginal != null) {
            pOriginal.setNome(p.getNome());
            pOriginal.setPreco(p.getPreco());
            pOriginal.setQuantidade(p.getQuantidade());
            
            ArquivoUtil.salvarDados(listaProduto);
            System.out.println("Produto alterado com sucesso e salvo no ficheiro.");
        } else {
            System.err.println("Erro: Produto com o ID " + p.getId() + " não foi encontrado.");
        }
    }

    public void remover(int idProdutoExcluir) {
        listaProduto.removeIf(produto -> produto.getId() == idProdutoExcluir);
        ArquivoUtil.salvarDados(listaProduto);
    }
}