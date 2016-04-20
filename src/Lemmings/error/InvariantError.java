package Lemmings.error;

public class InvariantError extends ContractError {

	private static final long serialVersionUID = 1L;

	public InvariantError(String message) {  
		super("Invariant : " + message); 
	}  

	public InvariantError(Throwable cause) {  
		super(cause); 
	}  

	public InvariantError(String message, Throwable cause) {  
		super("Invariant : " + message, cause); 
	} 
}
