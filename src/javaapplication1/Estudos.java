import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
 
public class Estudos extends JFrame{
  public Estudos(){
    super("Exemplo de uma tabela simples");
         
    // colunas da tabela
    String[] colunas = {"Cidade", "Estado", "Habitantes"};
         
    // conteúdo da tabela   
    Object[][] conteudo = {
        {"Goiânia", "GO", "43.023.432"},
        {"São Paulo", "SP", "5.343.234"},
        {"Rio de Janeiro", "RJ", "6.434.212"},
        {"Jussara", "GO", "87.454"},
        {"Barra do Garças", "MT", "64.344"}
    };
         
    // constrói a tabela
    final JTable tabela = new JTable(conteudo, colunas);
    tabela.setPreferredScrollableViewportSize(new Dimension(350, 50));
     
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
     
    JTableHeader header = tabela.getTableHeader();
    header.addMouseListener(new ColumnHeaderListener());
             
    JScrollPane scrollPane = new JScrollPane(tabela);
    c.add(scrollPane);
         
    setSize(400, 300);
    setVisible(true);
  }
     
  public static void main(String args[]){
    Estudos app = new Estudos();
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
 
class ColumnHeaderListener extends MouseAdapter {
  public void mouseClicked(MouseEvent evt) {
    JTable table = ((JTableHeader)evt.getSource()).getTable();
    TableColumnModel colModel = table.getColumnModel();
     
    // índice da coluna cujo titulo foi clicado
    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
    int mColIndex = table.convertColumnIndexToModel(vColIndex);
     
    if(vColIndex == -1) {
      return;
    }
     
    System.out.println("O clique ocorreu no titulo        da coluna com indice " + mColIndex);
  }
}