package dao;

public class DaoFactory {
	
	private UserDao ud;
	
	private DaoFactory(){
		ud = new UserDao();
	}
	
	public UserDao getUserDao(){
		return ud;
	}		
}
