package Lemmings.error;

public class ErrorHandler {

	public static void printError(ContractError error){
		System.err.println(error.getMessage());
	}
	
}
