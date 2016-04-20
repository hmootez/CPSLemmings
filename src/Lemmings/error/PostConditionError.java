package Lemmings.error;

public class PostConditionError extends ContractError {

	private static final long serialVersionUID = 1L;

	public PostConditionError(String message) {  
		super("PostCondition : " + message); 
	}  

	public PostConditionError(Throwable cause) {  
		super(cause); 
	}  

	public PostConditionError(String message, Throwable cause) {  
		super("PostCondition : " + message, cause); 
	} 
}
