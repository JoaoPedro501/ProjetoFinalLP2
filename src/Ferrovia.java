import java.io.Serializable;
import java.util.Stack;
 class Ferrovia implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Terminal terminal1;
    private Terminal terminal2;

    public Ferrovia() {
        terminal1 = new Terminal("R1");
        terminal2 = new Terminal("R2");
    }

    public Terminal getTerminal1() {
        return terminal1;
    }

    public Terminal getTerminal2() {
        return terminal2;
    }

    public void realizarEmbarque(Terminal terminal, char identificador, String carga) {
        if (terminal == terminal1 && (carga.equals("cobre") || carga.equals("ferro") || carga.equals("magnesita") || carga.equals("níquel"))) {
            throw new IllegalArgumentException("Não é permitido embarque de minérios no Terminal " + terminal1.getNome());
        }
        if (terminal == terminal2 && (carga.equals("amendoim") || carga.equals("feijão") || carga.equals("milho") || carga.equals("soja") || carga.equals("trigo"))) {
            throw new IllegalArgumentException("Não é permitido embarque de grãos no Terminal " + terminal2.getNome());
        }

        terminal.getComposicao().getVagoes().push(new Vagao(identificador, carga));
    }

    public void realizarDesembarque(Terminal terminal, char identificador) {
        Stack<Vagao> vagoes = terminal.getComposicao().getVagoes();
        Stack<Vagao> temp = new Stack<>();
        Vagao vagao = null;

        while (!vagoes.isEmpty()) {
            Vagao v = vagoes.pop();
            if (v.getIdentificador() == identificador) {
                vagao = v;
                break;
            }
            temp.push(v);
        }

        while (!temp.isEmpty()) {
            vagoes.push(temp.pop());
        }

        if (vagao == null) {
            throw new IllegalArgumentException("Vagão não encontrado na composição do Terminal " + terminal.getNome());
        }
    }

    public void transferirVagao(Vagao vagao, Terminal origem, Terminal destino) {
        origem.getComposicao().getVagoes().remove(vagao);
        destino.getComposicao().getVagoes().push(vagao);
    }

    public void listarVagoes() {
        Stack<Vagao> vagoesTerminal1 = terminal1.getComposicao().getVagoes();
        Stack<Vagao> vagoesTerminal2 = terminal2.getComposicao().getVagoes();

        System.out.println("Composição do Terminal " + terminal1.getNome() + ":");
        for (Vagao vagao : vagoesTerminal1) {
            System.out.println("Identificador: " + vagao.getIdentificador() + ", Carga: " + vagao.getCarga());
        }

        System.out.println("Composição do Terminal " + terminal2.getNome() + ":");
        for (Vagao vagao : vagoesTerminal2) {
            System.out.println("Identificador: " + vagao.getIdentificador() + ", Carga: " + vagao.getCarga());
        }
    }
}
