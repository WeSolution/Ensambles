
package Bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    private String servidor;
    private String usuario;
    private String password;
    private String bd;
    private String driver;
    private Connection con;

    public Conexion(String servidor, String usuario, String password, String bd, String driver) {
        this.servidor = servidor;
        this.usuario = usuario;
        this.password = password;
        this.bd = bd;
        this.driver = driver;
    }
    
    public void conectar() {
        String url = "jdbc:mysql://" + this.getServidor() + "/" + this.getBd() 
                + "?user=" + this.getUsuario() + "&password=" + this.getPassword();
        Connection con = null;
        try {
            
            Class.forName(this.getDriver());
            con = DriverManager.getConnection(url);
            this.setCon(con);
            System.out.println(url);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void desconectar() {
        try {
            this.getCon().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Conexion() {
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
}
