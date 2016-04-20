package Lemmings.error;

public class ContractError extends Error{

	private static final long serialVersionUID = 1591231993971883432L;

	public ContractError(String message) {
		super(message);
	}

	public ContractError(Throwable cause) {  
		super(cause); 
	}  

	public ContractError(String message, Throwable cause) {  
		super(message, cause); 
	} 
}
