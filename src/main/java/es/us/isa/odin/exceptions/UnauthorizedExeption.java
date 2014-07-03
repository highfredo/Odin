package es.us.isa.odin.exceptions;

public class UnauthorizedExeption extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7296540605601589720L;

	public UnauthorizedExeption(String msg) {
        super(msg);
    }

    public UnauthorizedExeption(String msg, Throwable t) {
        super(msg, t);
    }
}
