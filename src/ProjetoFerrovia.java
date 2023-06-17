import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ProjetoFerrovia {
    private static final String FILENAME = "ferrovia.bin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Ferrovia ferrovia;
        try {
            ferrovia = carregarDados();
        } catch (IOException | ClassNotFoundException e) {
            ferrovia = new Ferrovia();
            System.out.println("Não foi possível carregar os dados anteriores. Criando nova ferrovia.");
        }

        int opcao = 0;
        while (opcao != 5) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    realizarEmbarque(scanner, ferrovia);
                    break;
                case 2:
                    realizarDesembarque(scanner, ferrovia);
                    break;
                case 3:
                    transferirVagao(scanner, ferrovia);
                    break;
                case 4:
                    ferrovia.listarVagoes();
                    break;
                case 5:
                    try {
                        salvarDados(ferrovia);
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Realizar embarque");
        System.out.println("2. Realizar desembarque");
        System.out.println("3. Transferir vagão");
        System.out.println("4. Listar vagões");
        System.out.println("5. Sair");
        System.out.println("================");
    }

    private static Ferrovia carregarDados() throws IOException, ClassNotFoundException {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
                return (Ferrovia) ois.readObject();
            }
        } else {
            throw new FileNotFoundException("Arquivo de dados não encontrado.");
        }
    }

    private static void realizarEmbarque(Scanner scanner, Ferrovia ferrovia) {
        System.out.print("Terminal (R1 ou R2): ");
        String terminal = scanner.nextLine();

        Terminal terminalObj;
        if (terminal.equals("R1")) {
            terminalObj = ferrovia.getTerminal1();
        } else if (terminal.equals("R2")) {
            terminalObj = ferrovia.getTerminal2();
        } else {
            System.out.println("Terminal inválido.");
            return;
        }

        System.out.print("Identificador do vagão: ");
        char identificador = scanner.nextLine().charAt(0);

        System.out.print("Tipo de carga (minério ou grão): ");
        String carga = scanner.nextLine();

        try {
            ferrovia.realizarEmbarque(terminalObj, identificador, carga);
            System.out.println("Embarque realizado com sucesso no Terminal " + terminalObj.getNome() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void realizarDesembarque(Scanner scanner, Ferrovia ferrovia) {
        System.out.print("Terminal (R1 ou R2): ");
        String terminal = scanner.nextLine();

        Terminal terminalObj;
        if (terminal.equals("R1")) {
            terminalObj = ferrovia.getTerminal1();
        } else if (terminal.equals("R2")) {
            terminalObj = ferrovia.getTerminal2();
        } else {
            System.out.println("Terminal inválido.");
            return;
        }

        System.out.print("Identificador do vagão: ");
        char identificador = scanner.nextLine().charAt(0);

        try {
            ferrovia.realizarDesembarque(terminalObj, identificador);
            System.out.println("Desembarque realizado com sucesso no Terminal " + terminalObj.getNome() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void transferirVagao(Scanner scanner, Ferrovia ferrovia) {
        System.out.print("Terminal de origem (R1 ou R2): ");
        String origemStr = scanner.nextLine();

        Terminal origem;
        if (origemStr.equals("R1")) {
            origem = ferrovia.getTerminal1();
        } else if (origemStr.equals("R2")) {
            origem = ferrovia.getTerminal2();
        } else {
            System.out.println("Terminal de origem inválido.");
            return;
        }

        System.out.print("Terminal de destino (R1 ou R2): ");
        String destinoStr = scanner.nextLine();

        Terminal destino;
        if (destinoStr.equals("R1")) {
            destino = ferrovia.getTerminal1();
        } else if (destinoStr.equals("R2")) {
            destino = ferrovia.getTerminal2();
        } else {
            System.out.println("Terminal de destino inválido.");
            return;
        }

        System.out.print("Identificador do vagão: ");
        char identificador = scanner.nextLine().charAt(0);

        Vagao vagao = null;
        for (Vagao v : origem.getComposicao().getVagoes()) {
            if (v.getIdentificador() == identificador) {
                vagao = v;
                break;
            }
        }

        if (vagao != null) {
            ferrovia.transferirVagao(vagao, origem, destino);
            System.out.println("Vagão transferido com sucesso de " + origem.getNome() + " para " + destino.getNome() + ".");
        } else {
            System.out.println("Vagão não encontrado na composição do Terminal " + origem.getNome() + ".");
        }
    }

    private static void salvarDados(Ferrovia ferrovia) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(ferrovia);
            System.out.println("Dados salvos com sucesso.");
        }
    }
}