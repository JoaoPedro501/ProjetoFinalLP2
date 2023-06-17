
public class Terminal {
	
	    private String nome;
	    private Composicao composicao;

	    public Terminal(String nome) {
	        this.nome = nome;
	        composicao = new Composicao();
	    }

	    public String getNome() {
	        return nome;
	    }

	    public Composicao getComposicao() {
	        return composicao;
	    }
	}

