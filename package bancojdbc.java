package bancojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BancoJDBC {

    private Connection con;
    private Statement stmt;
    
    public BancoJDBC(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver encontrado!");
            }catch(ClassNotFoundException e){
                System.out.println("Driver não encontrado!" +
            e);
                System.out.println("Error: "+ e.getMessage());
            } 
        
                String url = "jdbc:mysql://localhost:3306/Banco";
                String user = "root";
                String password = "";
                
            try{
            con=DriverManager.getConnection(url,user,password);
            stmt = con.createStatement();
            }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        //inserirRegistros();
        //listarRegistros();
    }
    
        private void inserirRegistros(){
            
            try{
                stmt.executeUpdate("INSERT INTO Empregado VALUES(5,'Fellipe', '62945521', '6780.00')");
            }catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
            
    }
        
         private void inserirRegistros2(int mat, String n, String tel, String sal){
            try{
                stmt.executeUpdate("INSERT INTO Empregado VALUES ("+mat+", '"+n+"', '"+tel+"', '"+sal+"')");
            }catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
            
    }
        
        private void listarRegistros() {
            
            try {
                ResultSet rs;
                rs = stmt.executeQuery("Select * from empregado");
                while (rs.next()){
                    int matricula = rs.getInt("matricula");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    float salario = rs.getFloat("salario");
                    System.out.println(matricula + "\t" + nome + "\t" + telefone + "\t" + salario);
                }
            }catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        
        private void alterarRegistros (String sal, int mat) {
            try{
                stmt.executeUpdate("UPDATE Empregado SET salario = '"+sal+"' WHERE matricula = "+mat+"");
            }catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        
        private void apagarRegistros (int mat) {
            try{
                stmt.executeUpdate("DELETE FROM Empregado WHERE matricula="+mat+"");
            }catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        
        
    public static void main(String[] args) {
        BancoJDBC bancoJDBC = new BancoJDBC();
        Scanner leia = new Scanner(System.in);
        int matricula, matriculaExcluida, matriculaAlterar, opcao;
        String nome, telefone, salario, alterarSal;
        
        do{
            System.out.println("1 - INSERIR");
            System.out.println("2 - ALTERAR");
            System.out.println("3 - LISTAR");
            System.out.println("4 - EXCLUIR");
            System.out.println("5 - SAIR");
       
            System.out.println("DIGITE SUA OPCAO: ");
            opcao = leia.nextInt();
            
        switch(opcao){
            case 1:
                System.out.println("DIGITE A MATRICULA PARA INSERIR: ");
                matricula = leia.nextInt();
                System.out.println("DIGITE O NOME PARA INSERIR: ");
                nome = leia.next();
                leia.nextLine();
                System.out.println("DIGITE O TELEFONE PARA INSERIR: ");
                telefone = leia.next();
                System.out.println("DIGITE O SALARIO PARA INSERIR: ");
                salario = leia.next();
                bancoJDBC.inserirRegistros2(matricula, nome, telefone, salario);
                break;
            
            case 2:
                System.out.println("DIGITE A MATRICULA PARA ALTERAÇÃO DO SALARIO: ");
                matriculaAlterar = leia.nextInt();
                System.out.println("DIGITE O NOVO VALOR DO SALARIO: ");
                alterarSal = leia.next();
                bancoJDBC.alterarRegistros(alterarSal, matriculaAlterar);
                break;
                
            case 3:
                bancoJDBC.listarRegistros();
                break;
                
            case 4:
                System.out.println("DIGITE A MATRICULA PARA EXCLUIR: ");
                matriculaExcluida = leia.nextInt();
                bancoJDBC.apagarRegistros(matriculaExcluida);
            
            case 5:
                System.out.println("SAINDO...");
        }
    }while(opcao !=5);
 }
}
    
