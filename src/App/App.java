package App;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;
import Produtos.Produtos;
import Vendas.Vendas;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        List<Produtos> p = new ArrayList<>();
        List<Vendas> vendas = new ArrayList<>();
        Vendas v = new Vendas();
        String pontilhado = "------------------------------------------------------------------------------";

        do {
            System.out.println("------\nMENU\n------");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Listagem de produtos");
            System.out.println("3 - Consultar produto");
            System.out.println("4 - Vendas por período");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");

            int opçao = sc.nextInt();
            sc.nextLine();
            
            
            if(opçao == 1){
                Produtos pp = new Produtos();
                System.out.println("----------------------Cadastrar Produtos-----------------------");
                System.out.println("\nDigite o código do produto:");
                pp.setCodigo(sc.nextInt());
                sc.nextLine();
                System.out.println("\nDigite o nome do produto:");
                pp.setNome(sc.nextLine());
                System.out.println("\nDigite o valor do produto:");
                pp.setValor(sc.nextDouble());
                System.out.println("\nDigite a quantidade de estoque do produto:");
                try {
                    pp.setQtdEstoque(sc.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sc.nextLine();
                    voltarMenu(sc);
                    continue;
                }
                p.add(pp);
                sc.nextLine();

                System.out.println("Produto cadastrado com sucesso!");
                voltarMenu(sc);
              
            }
            else if(opçao == 2){

                System.out.println("----------------------Listagem dos produtos cadastrados-----------------------\n");
                if(p.isEmpty()){
                    System.out.println("Não há produtos cadastrado!");
                    voltarMenu(sc);
                    continue;
                }
                System.out.println("Código                    Nome          Valor(R$)         Estoque");
                System.out.println(pontilhado);
                p.forEach(System.out::print);
                System.out.println(pontilhado);
                
                //Média, maior e menor valores
                OptionalDouble media = p.stream().mapToDouble(Produtos::getValor).average();
                if (media.isPresent()) {
                }
                OptionalDouble max = p.stream().mapToDouble(Produtos::getValor).max();
                if (max.isPresent()) {
                }
                OptionalDouble min = p.stream().mapToDouble(Produtos::getValor).min();
                if (min.isPresent()) {
                }
                System.out.println("Valor máximo: R$ "+max.getAsDouble()+ "   Valor mínimo: R$ "+ min.getAsDouble()+"   Valor médio: R$ "+media.getAsDouble());
                voltarMenu(sc);
                
            }
            else if(opçao == 3){
                
                System.out.println("----------------------Consulta de produtos-----------------------\n");
                if(p.isEmpty()){
                    System.out.println("Não há produtos cadastrados!");
                    voltarMenu(sc);
                    continue;
                }
                System.out.println("Digite o código do produto que deseja encontrar:");
                int codigo = sc.nextInt();
                sc.nextLine();
                boolean achei = false;

                for (int index = 0; index < p.size(); index++) {
                    Produtos prod = p.get(index);
                    if (codigo == (prod.getCodigo())) {
                        System.out.println("Produto encontrado!!");
                        System.out.println();
                        System.out.println("Nome                    Código          Valor(R$)         Estoque");
                        System.out.println(pontilhado);
                        System.out.println(prod.getNome()+"                     "+prod.getCodigo()+"             "+prod.getValor()+"               "+prod.getQtdEstoque());
                        System.out.println(pontilhado);
                        achei= true;
                        voltarMenu(sc); 
                    } 
                }
                if( !achei){
                        System.out.println("Produto não encontrado!!");
                        voltarMenu(sc);   
                }

            }
            else if(opçao == 4){
                System.out.println("----------------------Relatório de Vendas Realizadas-----------------------");
                System.out.println();

                if (vendas.isEmpty()) {
                    System.out.println("Não há nenhuma venda realizada");
                    voltarMenu(sc);
                    continue;
                }
                System.out.println("Digite a data de inicio:");
                String inicio = sc.nextLine();
            
                System.out.println("Digite a data de fim");
                String fim = sc.nextLine();

                Map<String, List<Vendas>> dataVendas = vendas.stream().
                filter(data -> data.compareTo(inicio) > -1 && data.compareTo( fim) < 1).
                collect(Collectors.groupingBy(Vendas::getDataVenda));

                dataVendas.entrySet().forEach(item -> 
                System.out.printf("Data de Venda: %s \n %s",item.getKey(), item.getValue()));
                

                voltarMenu(sc);

            }
            else if(opçao == 5){
                System.out.println("----------------------Realizar Venda-----------------------");
    
                System.out.println("Digite o código do produto:");
                int codigo = sc.nextInt();
                sc.nextLine();
                boolean encontrado = false;
            
                for (int index = 0; index < p.size(); index++) {
                    Produtos prod = p.get(index);
                    
                    if(codigo==(prod.getCodigo())){
                        System.out.println("Produto encontrado!!");
                        sc.nextLine();
                        System.out.println("Produto: "+prod.getNome());
                        v.setProdutoVendido(prod.getNome());
                        System.out.println();
                        System.out.println("Aperte ENTER para continuar!");
                        encontrado = true;
                        sc.nextLine();
                    }
                }
                if(!encontrado){
                    System.out.println("Produto não encontrado!!");
                    voltarMenu(sc);
                    continue;
                }
                DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();

                System.out.println("Data dd/MM/yyyy [Pressione enter para pegar a data de agora]:");
                v.setDataVenda(sc.nextLine());
                if (v.getDataVenda().equals("")) {
                    v.setDataVenda(data.format(now));
                }
                System.out.println(v.getDataVenda());
                
                System.out.println("Quantidade:");
                v.setQtdVendida(sc.nextInt());
                
                for (int index = 0; index < p.size() ; index++) {
                    Produtos produtos = p.get(index);
                    produtos.setQtdEstoque(produtos.getQtdEstoque()-v.getQtdVendida());
                    v.setValorTotal(produtos.getValor()*v.getQtdVendida());

                    /*Lembrete:
                    Essa parte eu não consegui fazer direito,
                    eu não consegui dar baixa no estoque só em um produto,
                    então quando cadastra mais de um produto ele pega o ultimo valor e
                    abaixa a quantidade só que em todos os produtos.
                    Enfim, só da certo quando se tem só um produto cadastrado.
                    */
                }
                sc.nextLine();
                vendas.add(v);

                System.out.println("Finalizado!!");
                voltarMenu(sc);

            }
            else if (opçao != 0){
                System.out.println("\nOpção Inválida");
                break;
            }
            
        } while (opcao == 0);
         System.out.println("Fim do programa!");
         sc.close();
    }

    

        private static void voltarMenu(Scanner sc) throws InterruptedException, IOException {
            System.out.println("\nPressione ENTER para voltar ao menu.");
            sc.nextLine();
        
    
            // Limpa toda a tela, deixando novamente apenas o menu
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
            
            System.out.flush();
        }
        

    }

