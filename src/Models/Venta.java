/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.Connector;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juanba
 */
public class Venta {
    
    Connector connector = new Connector();
    Connection postgresConnection = connector.getPostgresConnection();
    Connection mysqlConnection = connector.getMysqlConnection();
    PreparedStatement postgresBegin;
    PreparedStatement postgresCommit;
    PreparedStatement postgresRollback;
    PreparedStatement mysqlBegin;
    PreparedStatement mysqlCommit;
    PreparedStatement mysqlRollback;
    PreparedStatement preparedStatementPostgres = null;
    PreparedStatement preparedStatementMysql = null;
    ResultSet resultSet = null;
    Statement statement = null;
    boolean mysql = false;
    boolean postgres = false;
    int mysqlCount = 0;
    int postgresCount = 0;
    
    //Instance Variables
    private int pk_ventaID;
    private int fk_usuarioID;
    private int fk_corteID;
    private int fk_clienteID;
    private int numeroTicket;
    private int dia;
    private int mes;
    private int ano;
    private String hora;
    private int cantidadArticulos;
    private double total;

    public Venta() throws SQLException {
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }

        this.fk_clienteID = 0;
    }
    
    public Venta(int numeroTicket) throws SQLException {
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
        
        
        this.numeroTicket = numeroTicket;
        this.fk_clienteID = 0;
    }
    
    public Venta(int pk_ventaID, int fk_usuarioID, int fk_corteID, int fk_clienteID, int numeroTicket, int dia, int mes, int ano, String hora, int cantidadArticulos, double total) throws SQLException {
        this.pk_ventaID = pk_ventaID;
        this.fk_usuarioID = fk_usuarioID;
        this.fk_corteID = fk_corteID;
        this.fk_clienteID = fk_clienteID;
        this.numeroTicket = numeroTicket;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.cantidadArticulos = cantidadArticulos;
        this.total = total;
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
        
    }


    public int getPk_ventaID() {
        return pk_ventaID;
    }

    public void setPk_ventaID(int pk_ventaID) {
        this.pk_ventaID = pk_ventaID;
    }

    public int getFk_usuarioID() {
        return fk_usuarioID;
    }

    public void setFk_usuarioID(int fk_usuarioID) {
        this.fk_usuarioID = fk_usuarioID;
    }

    public int getFk_corteID() {
        return fk_corteID;
    }

    public void setFk_corteID(int fk_corteID) {
        this.fk_corteID = fk_corteID;
    }

    public int getFk_clienteID() {
        return fk_clienteID;
    }

    public void setFk_clienteID(int fk_clienteID) {
        this.fk_clienteID = fk_clienteID;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void saveToDatabase() {
        try {
            if(this.fk_clienteID == 0) {
                
                preparedStatementPostgres = postgresConnection.prepareStatement("insert into ventas (fk_usuarioid,fk_corteid,numeroticket,dia,mes,ano,hora,cantarticulos,total) values (?,?,?,?,?,?,?,?,?)");
                preparedStatementPostgres.setInt(1, this.fk_usuarioID);
                preparedStatementPostgres.setInt(2, this.fk_corteID);
                preparedStatementPostgres.setInt(3, this.numeroTicket);
                preparedStatementPostgres.setInt(4, this.dia);
                preparedStatementPostgres.setInt(5, this.mes);
                preparedStatementPostgres.setInt(6, this.ano);
                preparedStatementPostgres.setString(7, this.hora);
                preparedStatementPostgres.setInt(8, this.cantidadArticulos);
                preparedStatementPostgres.setDouble(9,this.total);

                preparedStatementMysql = mysqlConnection.prepareStatement("insert into ventas (fk_usuarioid,fk_corteid,numeroticket,dia,mes,ano,hora,cantarticulos,total) values (?,?,?,?,?,?,?,?,?)");
                preparedStatementMysql.setInt(1, this.fk_usuarioID);
                preparedStatementMysql.setInt(2, this.fk_corteID);
                preparedStatementMysql.setInt(3, this.numeroTicket);
                preparedStatementMysql.setInt(4, this.dia);
                preparedStatementMysql.setInt(5, this.mes);
                preparedStatementMysql.setInt(6, this.ano);
                preparedStatementMysql.setString(7, this.hora);
                preparedStatementMysql.setInt(8, this.cantidadArticulos);
                preparedStatementMysql.setDouble(9,this.total);

            } else {
                
                preparedStatementPostgres = postgresConnection.prepareStatement("insert into ventas (fk_usuarioid,fk_corteid,numeroticket,dia,mes,ano,hora,cantarticulos,total) values (?,?,?,?,?,?,?,?,?)");
                preparedStatementPostgres.setInt(1, this.fk_usuarioID);
                preparedStatementPostgres.setInt(2, this.fk_corteID);
                preparedStatementPostgres.setInt(3, this.fk_clienteID);
                preparedStatementPostgres.setInt(4, this.numeroTicket);
                preparedStatementPostgres.setInt(5, this.dia);
                preparedStatementPostgres.setInt(6, this.mes);
                preparedStatementPostgres.setInt(7, this.ano);
                preparedStatementPostgres.setString(7, this.hora);
                preparedStatementPostgres.setInt(8, this.cantidadArticulos);
                preparedStatementPostgres.setDouble(9, this.total);
                
                preparedStatementMysql = mysqlConnection.prepareStatement("insert into ventas (fk_usuarioid,fk_corteid,numeroticket,dia,mes,ano,hora,cantarticulos,total) values (?,?,?,?,?,?,?,?,?)");
                preparedStatementMysql.setInt(1, this.fk_usuarioID);
                preparedStatementMysql.setInt(2, this.fk_corteID);
                preparedStatementMysql.setInt(3, this.fk_clienteID);
                preparedStatementMysql.setInt(4, this.numeroTicket);
                preparedStatementMysql.setInt(5, this.dia);
                preparedStatementMysql.setInt(6, this.mes);
                preparedStatementMysql.setInt(7, this.ano);
                preparedStatementMysql.setString(7, this.hora);
                preparedStatementMysql.setInt(8, this.cantidadArticulos);
                preparedStatementMysql.setDouble(9, this.total);

            }
            //Insert product into database after validation           
            postgresBegin.executeUpdate();
            postgresCount = preparedStatementPostgres.executeUpdate();
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatementMysql.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                JOptionPane.showMessageDialog(null, "Transaccion fallo. Error en bases de datos.");
                    postgresRollback.executeUpdate();
                    mysqlRollback.executeUpdate();
                } else {
                    postgresCommit.executeUpdate();
                    mysqlCommit.executeUpdate();
                    System.out.println("Transaction was successful.");
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}    

