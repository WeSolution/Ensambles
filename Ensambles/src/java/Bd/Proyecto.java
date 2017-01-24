
package Bd;
import Interf.ABCM;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Proyecto implements ABCM {
    
    private String id;
    private String desc;
    private Conexion con;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        
    }

    public Proyecto(String id, String desc) {
        this.id = id;
        this.desc = desc;
        con = new Conexion("localhost", "root", "", "ensamble", "com.mysql.jdbc.Driver");
    }

    public Proyecto() {
        con = new Conexion("localhost", "root", "", "ensamble", "com.mysql.jdbc.Driver");
    }
    
    @Override
    public boolean alta() {
        boolean band = false;
        String id = "";
        String desc = "";
        Connection c = null;
        PreparedStatement ps = null;
        try {
            String sql = "insert into proyecto (id,descripcion) values (?,?)";
            this.con.conectar();
            c = this.con.getCon();
            
            ps = c.prepareStatement(sql);
            id = this.id;
            desc = this.desc;
            ps.setString(1, id);
            ps.setString(2, desc);
            System.out.println(ps);
            ps.executeUpdate();
            
            band = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return band;
    }

    @Override
    public boolean baja(String id) {
        boolean band = false;
        Connection c = null;
        PreparedStatement ps = null;
        try {
            String sql = "delete from proyecto where proyecto.id = ?";
            this.con.conectar();
            c = this.con.getCon();
            ps = c.prepareStatement(sql);
            ps.setString(1, id);
            System.out.println(ps);
            ps.executeUpdate();
            band = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return band;
    }

    @Override
    public List<Proyecto> consulta() {
        List<Proyecto> lp = null;        
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            String sql = "select * from proyecto";
            System.out.println(this.con);
            this.con.conectar();
            c = this.con.getCon();
            st = c.createStatement();
            System.out.println(st);
            rs = st.executeQuery(sql);
            lp = new ArrayList<Proyecto>();
            while(rs.next()){
                Proyecto p = new Proyecto();
                p.setId(rs.getString("id"));
                p.setDesc(rs.getString("descripcion"));
                lp.add(p);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lp;
    }

    @Override
    public List<Proyecto> ver(String id) {
        List<Proyecto> lp = null;  
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from proyecto where proyecto.id = ?";
            this.con.conectar();
            c = this.con.getCon();
            ps = c.prepareStatement(sql);
            ps.setString(1, id);
            System.out.println(ps);
            rs = ps.executeQuery();
            lp = new ArrayList<Proyecto>();
            while(rs.next()){
                Proyecto p = new Proyecto();
                p.setId(rs.getString("id"));
                p.setDesc(rs.getString("descripcion"));
                lp.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lp;
    }

    @Override
    public boolean modificar(String id) {
        boolean band = false;
        Connection c = null;
        PreparedStatement ps = null;
        try {
            String sql = "update proyecto set id = ?, descripcion = ? where proyecto.id = ?";
            this.con.conectar();
            c = this.con.getCon();
            
            ps = c.prepareStatement(sql);
            desc = this.desc;
            ps.setString(1, this.id);
            ps.setString(2, desc);
            ps.setString(3, id);
            System.out.println(ps);
            ps.executeUpdate();
            band = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return band;
    }    
}
