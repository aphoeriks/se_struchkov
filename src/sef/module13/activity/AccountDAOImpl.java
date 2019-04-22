package sef.module13.activity;

import java.sql.*;
import java.util.*;

public class AccountDAOImpl implements AccountDAO {


	@SuppressWarnings("unused")
	private Connection conn;

	public AccountDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public List<Account> findAccount(String firstName, String lastName)
			throws AccountDAOException {
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement pStmt
					= conn.prepareStatement("select * from ACCOUNT where  FIRST_NAME LIKE (?) AND " +
																			"LAST_NAME LIKE (?)");
			pStmt.setString(1,'%'+firstName+'%');
			pStmt.setString(2,lastName);
			ResultSet rs = pStmt.executeQuery();
			accounts = RsToList(rs);
			rs.close();
		}catch (Exception e){
			throw new AccountDAOException(AccountDAOException.ERROR_FIND_NAME, e);
		}finally {
			return accounts;
		}
	}
	private List<Account> RsToList (ResultSet rs) throws Exception{
		List<Account> accounts = new ArrayList<Account>();
		try {
			while (rs.next()) {
				accounts.add(new AccountImpl(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4))
				);
			}
		}catch (Exception e){
			throw e;
		}finally {
			return accounts;
		}
	}

	public Account findAccount(int id) throws AccountDAOException {
		Account account = null;
		try {
			PreparedStatement pStmt
					= conn.prepareStatement("select * from ACCOUNT where  ID = ? ");
			pStmt.setInt(1,id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()){
				 account = new AccountImpl(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4));
			}
			rs.close();
		}catch (Exception e){
			throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, e);
		}finally {
			return account;
		}
	}


	public boolean insertAccount(String firstName, String lastName, String email)
			throws AccountDAOException {
		try {
			PreparedStatement pStmt
					= conn.prepareStatement("insert into Account (ID,FIRST_NAME, LAST_NAME, E_MAIL) VALUES (ACCOUNT_SEQ.NEXTVAL,?,?,?)");
			pStmt.setString(1,firstName);
			pStmt.setString(2,lastName);
			pStmt.setString(3,email);
			int out = pStmt.executeUpdate();
			conn.commit();
			return out!=0;
		}catch (Exception e){
			throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, e);
		}
	}

}
