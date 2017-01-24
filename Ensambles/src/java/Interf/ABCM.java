
package Interf;
import Bd.Conexion;
import java.util.List;

public interface ABCM {
    public boolean alta();
    public boolean baja(String id);
    public List<?> consulta();
    public List<?> ver(String id);
    public boolean modificar(String id);
}
