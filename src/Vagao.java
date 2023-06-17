import java.io.Serializable;

public class Vagao implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private char identificador;
	    private String carga;

	    public Vagao(char identificador, String carga) {
	        this.identificador = identificador;
	        this.carga = carga;
	    }

	    public char getIdentificador() {
	        return identificador;
	    }

	    public String getCarga() {
	        return carga;
	    }
	}

