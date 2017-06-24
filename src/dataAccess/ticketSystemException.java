package dataAccess;

/**
 * 這個class處理TicketSystem會丟出來的exceptions
 *
 */
public class ticketSystemException extends Exception{
	public ticketSystemException(String message){
		super(message);
	}
}
