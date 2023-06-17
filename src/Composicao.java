import java.io.Serializable;
import java.util.Stack;

public class Composicao implements Serializable {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Stack<Vagao> vagoes;

	    public Composicao() {
	        vagoes = new Stack<>();
	    }

	    public Stack<Vagao> getVagoes() {
	        return vagoes;
	    }
	}

