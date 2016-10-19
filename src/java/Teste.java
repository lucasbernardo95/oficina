
import com.mysql.fabric.xmlrpc.base.Data;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.UsuarioDAO;
import dao.VendaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.scene.input.DataFormat;
import model.Cliente;
import model.Produto;
import model.TipoUsuario;
import model.Usuario;
import model.Venda;
import org.apache.tomcat.jni.Time;
import util.ErroSistema;

/**
 *
 * @author lber
 */
public class Teste {
    public static void main(String[] args) throws ErroSistema, ParseException {
//        Usuario usuario = new Usuario( "Eduardo Bernardo", "eduardo", "123", TipoUsuario.administrador);
//        
//        UsuarioDAO dao = new UsuarioDAO();
////        //Usuario u2 = dao.buscar(2);
////        
//        dao.salvar(usuario);
            Cliente c = new Cliente(null, "Jonatas Melo", "Não sei", 
                    "void123", "Macaíba", "Ferreiro Torto", "melo@gmail.com", "(84)99452-7602", 0);
            ClienteDAO cdao = new ClienteDAO();
            cdao.salvar(c);

        //Usuario u1 = dao.buscar(2);
        //System.out.println(u1.getNome());
        //Cliente cliente = new Cliente(null, "Lucas Bernardo", "José Salustiano", 
        //        "mzb3329", "Macaíba", "Ferreiro Torto", "lucasbernardo@gmail.com", "994527602", 0);
        //ClienteDAO cdao = new ClienteDAO();
        //cdao.salvar(cliente);

//        Produto p = new Produto("xp1", "Produto 1" , 100.00, 200.99 + 5, 100, u1, 0);
//        Produto p2 = new Produto("xp2", "Produto 2" , 100.00, 200.99 + 5, 100, u1, 0);
//        Produto p3 = new Produto("xp3", "Produto 3" , 100.00, 200.99 + 5, 100, u1, 0);
//        ProdutoDAO pdao = new ProdutoDAO();
//        pdao.salvar(p);
//        pdao.salvar(p2);
//        pdao.salvar(p3);
        
        //venda
//        UsuarioDAO dao = new UsuarioDAO();
//        Usuario u1 = dao.buscar(1);
//
//        ProdutoDAO pdao = new ProdutoDAO();
//        Produto p = pdao.buscarProduto("xp3");
//        Produto p2 = pdao.buscarProduto("xp2");
//        

//        List<Produto> lista = new ArrayList<>();
//        lista.add(p);
//        lista.add(p2);
//
//        Venda v = new Venda();
//
//        Venda venda = new Venda(Long.MIN_VALUE, u1, c, lista, (p.getValorvenda() + p2.getValorvenda() * 3), 3, false);
//        VendaDAO vdao = new VendaDAO();
//        System.out.println(venda.getDatavenda());
//        vdao.salvar(venda);

    }
}
