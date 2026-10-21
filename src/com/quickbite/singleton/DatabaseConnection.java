package com.quickbite.singleton;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("  [SINGLETON] In-Memory Enterprise Database Driver initialized.");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("  [DB EXECUTE] " + query);
    }
}

/*class Test{
	public static void main(String[] args) {

		Test t=new Test();
		//DatabaseConnection connection= new DatabaseConnection();
		DatabaseConnection connection= DatabaseConnection.getInstance();
		System.out.println(connection);

		DatabaseConnection connection1= DatabaseConnection.getInstance();
		System.out.println(connection1);
	}
}*/