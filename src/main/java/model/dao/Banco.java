package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

	//private static final String DRIVER = "com.mysql.jdbc.Driver;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String BANCODADOS = "dbsocorrodesk";
	private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCODADOS;
	private static final String USER = "root";
	private static final String PASSWORD = "12345";
	
	public static Connection getConnection(){
		try {
			Connection conn = null;
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONEXAO, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do Driver não foi encontrada.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Erro ao obter a Connection.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
	
	public static void closeConnection(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento da conexão.");
			System.out.println("Erro: " + e.getMessage());
		}	
	}
	
	public static Statement getStatement(Connection conn){
		try {
			Statement stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			System.out.println("Erro ao obter o Statement.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
		
	public static void closeStatement(Statement stmt){
		try {
			if(stmt != null){
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do Statement.");
			System.out.println("Erro: " + e.getMessage());
		}	
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn, String sql){
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
	
	public static PreparedStatement getPreparedStatementWithPk(Connection conn, String sql){
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public static void closePreparedStatement(Statement stmt){
		try {
			if(stmt != null){
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
		}	
	}
	
	public static void closeResultSet(ResultSet result){
		try {
			if(result != null){
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public boolean deleteChamado(int idchamado) {
		try {
			String sql = "DELETE FROM CHAMADOS where IDCHAMADO=?";
			Connection c = getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, idchamado);
			int resultado = ps.executeUpdate();
			return resultado > 0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	public boolean atualizaChamado(String dataabertura, String titulo) {
		try {
			String sql = "UPDATE CHAMADOS SET DATAABERTURA =?, TITULO =?";
			Connection c = getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, dataabertura);
			ps.setString(2, titulo);
			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}





