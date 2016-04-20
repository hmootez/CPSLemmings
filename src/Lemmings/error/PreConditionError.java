package Lemmings.error;

public class PreConditionError extends ContractError {

	private static final long serialVersionUID = 1L;

	public PreConditionError(String message) {  
		super("PreCondition : " + message); 
	}  

	public PreConditionError(Throwable cause) {  
		super(cause); 
	}  

	public PreConditionError(String message, Throwable cause) {  
		super("PreCondition : " + message, cause); 
	} 
}

