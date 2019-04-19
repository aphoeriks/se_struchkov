package sef.module8.activity;


/**
 * Thsi class represents a simple representation of an account encapsulating
 * a name 
 * 
 * @author John Doe
 *
 */
public class Account {

	protected String accountName;

	/**
	 * Creates an Account object with the specified name.  If the accout name
	 * given violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	public  Account(String accountName) throws AccountException{
			if (accountName.length()<4 ){
				throw new AccountException(AccountException.NAME_TOO_SHORT, accountName);
			} else {
				if (((accountName.matches("[0-9]+")) ||
						(accountName.matches("[a-zA-Z]+")))) {
					throw new AccountException(AccountException.NAME_TOO_SIMPLE, accountName);
				} else {
					this.accountName = accountName;
				}
			}
			
			
	}
	
	
	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName(){
		return this.accountName;
	}
}
