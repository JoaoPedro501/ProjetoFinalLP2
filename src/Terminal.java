import java.io.Serializable;

public class Terminal implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

