import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.FornecedorDAO;
import DAO.ItemVendaDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import entidade.Categoria;
import entidade.Cliente;
import entidade.Fornecedor;
import entidade.ItemVenda;
import entidade.Produto;
import entidade.Venda;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            VendaDAO vendaDAO = new VendaDAO();
            ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

            char opcaoPrincipal;

            do {
                limparTela();
                System.out.println("===== MENU PRINCIPAL =====");
                System.out.println("1. Listar");
                System.out.println("2. Inserir");
                System.out.println("3. Outras");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcaoPrincipal = scanner.next().charAt(0);
                scanner.nextLine(); // Limpa o buffer após next()

                limparTela();

                switch (opcaoPrincipal) {
                    case '1' -> submenuListar(scanner, produtoDAO, clienteDAO, categoriaDAO, fornecedorDAO, vendaDAO, itemVendaDAO);
                    case '2' -> submenuInserir(scanner, produtoDAO, clienteDAO, categoriaDAO, fornecedorDAO);
                    case '3' -> submenuConsultas(scanner, produtoDAO, itemVendaDAO);
                    case '0' -> System.out.println("Saindo do sistema...");
                    default ->{
                        System.out.println("Opção inválida. Tente novamente.");
                        pausar();
                        break;
                    }
                }
            } while (opcaoPrincipal != '0');
        }
    }

    private static void submenuListar(Scanner scanner, ProdutoDAO produtoDAO, ClienteDAO clienteDAO, CategoriaDAO categoriaDAO, FornecedorDAO fornecedorDAO, VendaDAO vendaDAO, ItemVendaDAO itemVendaDAO) {
        char opcaoListar;
        do {
            System.out.println("===== LISTAR =====");
            System.out.println("1. Produtos");
            System.out.println("2. Clientes");
            System.out.println("3. Categorias");
            System.out.println("4. Fornecedores");
            System.out.println("5. Vendas");
            System.out.println("6. Itens de vendas");
            System.out.println("0. Voltar para o menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoListar = scanner.next().charAt(0);
            scanner.nextLine(); // Limpa o buffer após next()

            limparTela();

            switch (opcaoListar) {
                case '1' -> {
                    System.out.println("=== Produtos ===");
                    List<Produto> produtos = produtoDAO.listarProdutos();
                    System.out.println(String.format("%-3s | %-30s | %-40s | %-10s | %-10s", 
                                                     "ID", "Nome", "Descrição", "Preço", "Estoque\n"));
                    for (Produto p : produtos) {
                        System.out.println(String.format("%-3d | %-30s | %-40s | %-10.2f | %-10d", 
                                                         p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getEstoque()));
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '2' -> {
                    System.out.println("=== Clientes ===");
                    List<Cliente> clientes = clienteDAO.listarClientes();
                    System.out.println(String.format("%-3s | %-30s | %-30s | %-15s | %-40s", "ID", "Nome", "Email", "Telefone", "Endereço\n"));
                    for (Cliente c : clientes) {
                        System.out.println(String.format("%-3d | %-30s | %-30s | %-15s | %-40s",
                            c.getId(),
                            c.getNome(),
                            c.getEmail(),
                            c.getTelefone(),
                            c.getEndereco()
                        ));
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }                
                case '3' -> {
                    System.out.println("=== Categorias ===");
                    List<Categoria> categorias = categoriaDAO.listarCategorias();
                    System.out.println(String.format("%-3s | %-30s", "ID", "Nome\n"));
                    for (Categoria c : categorias) {
                        System.out.println(String.format("%-3d | %-30s", c.getId(), c.getNome()));
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }                
                case '4' -> {
                    System.out.println("=== Fornecedores ===");
                    List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedores();
                    System.out.println(String.format("%-3s | %-30s", "ID", "Nome\n"));
                    for (Fornecedor f : fornecedores) {
                        System.out.println(String.format("%-3d | %-30s", f.getId(), f.getNome()));
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }                
                case '5' -> {
                    System.out.println("=== Vendas ===");
                    List<Venda> vendas = vendaDAO.listarVendas();
                    System.out.println(String.format("%-3s | %-20s", "ID", "Data\n"));
                    for (Venda v : vendas) {
                        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(v.getData());
                        System.out.println(String.format("%-3d | %-20s", v.getId(), dataFormatada));
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '6' -> {
                    System.out.println("=== Itens de Venda ===");
                    List<ItemVenda> itensVenda = itemVendaDAO.listarItemVendas();
                    System.out.println(String.format("%-5s | %-12s | %-10s", "ID", "Produto ID", "Quantidade\n"));
                    for (ItemVenda iv : itensVenda) {
                        System.out.println(String.format("%-5d | %-12d | %-10d", iv.getVendaId(), iv.getProdutoId(), iv.getQuantidade()));
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '0' -> System.out.println("Voltando...");
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    pausar();
                    break;
                }
            }
            limparTela();
        } while (opcaoListar != '0');
    }

    private static void submenuInserir(Scanner scanner, ProdutoDAO produtoDAO, ClienteDAO clienteDAO, CategoriaDAO categoriaDAO, FornecedorDAO fornecedorDAO) {
        char opcaoInserir;
        do {
            System.out.println("===== INSERIR =====");
            System.out.println("1. Inserir Produto");
            System.out.println("2. Inserir Cliente");
            System.out.println("3. Inserir Categoria");
            System.out.println("4. Inserir Fornecedor");
            System.out.println("0. Voltar para o menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoInserir = scanner.next().charAt(0);
            scanner.nextLine(); // Limpa o buffer após next()

            limparTela();

            switch (opcaoInserir) {
                case '1' -> {
                    System.out.println("=== Inserir Produto ===");
                    Produto novoProduto = coletarDadosProduto(scanner);
                    if (produtoDAO.inserirProduto(novoProduto)) {
                        System.out.println("Produto inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir produto.");
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '2' -> {
                    System.out.println("=== Inserir Cliente ===");
                    Cliente novoCliente = coletarDadosCliente(scanner);
                    if (clienteDAO.inserirCliente(novoCliente)) {
                        System.out.println("Cliente inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir cliente.");
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '3' -> {
                    System.out.println("=== Inserir Categoria ===");
                    Categoria novaCategoria = coletarDadosCategoria(scanner);
                    if (categoriaDAO.inserirCategoria(novaCategoria)) {
                        System.out.println("Categoria inserida com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir categoria.");
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '4' -> {
                    System.out.println("=== Inserir Fornecedor ===");
                    Fornecedor novoFornecedor = coletarDadosFornecedor(scanner);
                    if (fornecedorDAO.inserirFornecedor(novoFornecedor)) {
                        System.out.println("Fornecedor inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir fornecedor.");
                    }
                    pausar(); // Pausa antes de voltar ao menu
                }
                case '0' -> System.out.println("Voltando...");
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    pausar();
                }
            }
            limparTela();
        } while (opcaoInserir != '0');
    }

    private static void submenuConsultas(Scanner scanner, ProdutoDAO produtoDAO, ItemVendaDAO itemVendaDAO) {
        char opcaoConsulta;
        do {
            System.out.println("===== EXTRAS =====");
            System.out.println("1. Deletar um produto da lista");
            System.out.println("2. Calcular preco dos produtos (meio, menor e amior)");
            System.out.println("3. Calcular soma total e quantidade de produtos");
            System.out.println("4. Calcular estatísticas de vendas (média, menor e maior valores)");
            System.out.println("5. Atualizar um produto");
            System.out.println("0. Voltar para o menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoConsulta = scanner.next().charAt(0);
            scanner.nextLine();
            limparTela();

            switch (opcaoConsulta) {
                case '1' -> {System.out.print("Informe o ID do produto a ser deletado: ");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine();
                    if (produtoDAO.deletarProduto(idProduto)) {
                        System.out.println("Produto deletado com sucesso.");
                    } else {
                        System.out.println("Erro ao deletar o produto. Verifique o ID e tente novamente.");
                    }
                }
                case '2' -> produtoDAO.calcularEstatisticasPreco();
                case '3' -> produtoDAO.calcularSomaEQuantidadeProdutos();
                case '4' -> itemVendaDAO.calcularEstatisticasVendas();
                case '5' -> {
                    System.out.print("Informe o ID do produto a ser atualizado: ");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Informe o novo nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Informe a nova descrição do produto: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Informe o novo preço do produto: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Informe a nova quantidade em estoque: ");
                    int estoque = scanner.nextInt();
                    System.out.print("Informe o novo ID da categoria: ");
                    int categoriaId = scanner.nextInt();
                    Produto produto = new Produto(idProduto, nome, descricao, preco, estoque, categoriaId);
                    if (produtoDAO.atualizarProduto(produto)) {
                        System.out.println("Produto atualizado com sucesso.");
                    } else {
                        System.out.println("Erro ao atualizar o produto. Verifique as informações e tente novamente.");
                    }
                }
                case '0' -> System.out.println("Voltando...");
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    pausar();
                    break;
                }
            }
            pausar();
            limparTela();
        } while (opcaoConsulta != '0');
    }

    // Métodos para coletar dados de cada entidade
    private static Produto coletarDadosProduto(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        double preco = 0;
        while (true) {
            System.out.print("Preço: ");
            try {
                preco = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida para o preço.");
                scanner.next();
            }
        }
        
        System.out.print("Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Categoria ID: ");
        int categoriaId = scanner.nextInt();
        scanner.nextLine();
        
        return new Produto(0, nome, descricao, preco, estoque, categoriaId);
    }

    private static Cliente coletarDadosCliente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        return new Cliente(0, nome, email, telefone, endereco);
    }

    private static Categoria coletarDadosCategoria(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        return new Categoria(0, nome);
    }

    private static Fornecedor coletarDadosFornecedor(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Contato (exemplo: telefone ou e-mail): ");
        String contato = scanner.nextLine();
        return new Fornecedor(0, nome, contato);
    }
    
    // Método para limpar a tela
    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método para pausar a execução
    private static void pausar() {
        try {
            System.out.println("Pressione Enter para continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
