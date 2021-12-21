/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication1;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author MinasNet
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame1
     */
    private static final String url="jdbc:mysql://localhost:3305/vendas";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs=null;
    int q;
    //CLIENTE
    String coluna="idcliente",ordena="desc",id,pesquisa;
    int ordemid=0,ordemnome=0;
    boolean ativapesquisa=false;
    boolean atualiza=true;
    boolean salvarcli=false;
    
    
    //PRODUTO
    String colunapr="idproduto",ordenapr="asc",idpr,pesquisapr;
    int ordemidpr=0,ordemnomepr=0;
    boolean ativapesquisapr=false;
    boolean atualizapr=true;
    
    public Principal() {
        initComponents();
        pesquisa = jTPesquisa.getText();
        setLocationRelativeTo(null);
        updateDB("idcliente","desc");
        updateDBPR("idproduto","asc");
        
        jTid.setEditable(false);
     
        
        
        //SELECIONA O ULTIMO REGISTRO E PREENCHE ID E NOME
        try
        {
           // Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            
            ps = con.prepareStatement("SELECT * FROM clientes ORDER BY idcliente DESC LIMIT 1");
            rs = ps.executeQuery();
            rs.next();
            jTid.setText(rs.getString("idcliente"));
            jTnome.setText(rs.getString("nomecliente"));
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        
        //// listener PRODUTO-- espera o clique no titulo da tabela
        jTablepr.getTableHeader().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            
            int col = jTablepr.columnAtPoint(e.getPoint());
            String name = jTablepr.getColumnName(col);
            System.out.println("Column index selected " + col + " " + name);
            
            if(ativapesquisapr==true)
            {
            if(name.equalsIgnoreCase("Nome"))
            {
                ordemnomepr++;
                colunapr="nomeproduto";
                
                if(ordemnomepr%2==0)
                {
                    ordenapr="desc";
                    updateDBFilterPR(colunapr,ordenapr);
                }else
                {
                    ordenapr="asc";
                    updateDBFilterPR(colunapr,ordenapr);
                }
                
                
            }else if(name.equalsIgnoreCase("ID"))
            {
                ordemidpr++;
                colunapr="idproduto";
                if(ordemidpr%2==0)
                {
                    ordenapr="desc";
                    updateDBFilterPR(colunapr,ordenapr);
                }else
                {
                    ordenapr="asc";
                    updateDBFilterPR(colunapr,ordenapr);
                }
            } else if(name.equalsIgnoreCase("Valor"))
            {
                ordemidpr++;
                colunapr="valorunit";
                if(ordemidpr%2==0)
                {
                    ordenapr="desc";
                    updateDBFilterPR(colunapr,ordenapr);
                }else
                {
                    ordenapr="asc";
                    updateDBFilterPR(colunapr,ordenapr);
                }
            }
            }else
            
            if(name.equalsIgnoreCase("Nome"))
            {
                ordemnomepr++;
                colunapr="nomeproduto";
                
                if(ordemnomepr%2==0)
                {
                    ordenapr="desc";
                    updateDBPR(colunapr,ordenapr);
                }else
                {
                    ordenapr="asc";
                    updateDBPR(colunapr,ordenapr);
                }
                
                
            }else if (name.equalsIgnoreCase("ID"))
            {
                ordemidpr++;
                colunapr="idproduto";
                if(ordemidpr%2==0)
                {
                    ordenapr="desc";
                    updateDBPR(colunapr,ordenapr);
                }else
                {
                    ordenapr="asc";
                    updateDBPR(colunapr,ordenapr);
                }
            } else if(name.equalsIgnoreCase("Valor"))
            {
                ordemidpr++;
                colunapr="valorunit";
                if(ordemidpr%2==0)
                {
                    ordenapr="desc";
                    updateDBPR(colunapr,ordenapr);
                }else
                {
                    ordenapr="asc";
                    updateDBPR(colunapr,ordenapr);
                }
            }
        }
         });
        
        
        //// listener CLIENTE-- espera o clique no titulo da tabela
        jTable1.getTableHeader().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            
            int col = jTable1.columnAtPoint(e.getPoint());
            String name = jTable1.getColumnName(col);
            System.out.println("Column index selected " + col + " " + name);
            
            if(ativapesquisa==true)
            {
            if(name.equalsIgnoreCase("Nome"))
            {
                ordemnome++;
                coluna="nomecliente";
                
                if(ordemnome%2==0)
                {
                    ordena="desc";
                    updateDBFilter(coluna,ordena);
                }else
                {
                    ordena="asc";
                    updateDBFilter(coluna,ordena);
                }
                
                
            }else
            {
                ordemid++;
                coluna="idcliente";
                if(ordemid%2==0)
                {
                    ordena="desc";
                    updateDBFilter(coluna,ordena);
                }else
                {
                    ordena="asc";
                    updateDBFilter(coluna,ordena);
                }
            }
            }else
            
            if(name.equalsIgnoreCase("Nome"))
            {
                ordemnome++;
                coluna="nomecliente";
                
                if(ordemnome%2==0)
                {
                    ordena="desc";
                    updateDB(coluna,ordena);
                }else
                {
                    ordena="asc";
                    updateDB(coluna,ordena);
                }
                
                
            }else
            {
                ordemid++;
                coluna="idcliente";
                if(ordemid%2==0)
                {
                    ordena="desc";
                    updateDB(coluna,ordena);
                }else
                {
                    ordena="asc";
                    updateDB(coluna,ordena);
                }
            }
        }
         });
        
        //jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setMinWidth(40);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
        jTable1.getColumnModel().getColumn(1).setMinWidth(500);

        
}
        
   

    
class ColumnHeaderListener extends MouseAdapter {
  public void mouseClicked(MouseEvent evt) {
    jTable1 = ((JTableHeader)evt.getSource()).getTable();
    TableColumnModel colModel = jTable1.getColumnModel();
     
    // índice da coluna cujo titulo foi clicado
    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
    int mColIndex = jTable1.convertColumnIndexToModel(vColIndex);
     
    if(vColIndex == -1) {
      return;
    }
     
    System.out.println("O clique ocorreu no tituloda coluna com indice " + mColIndex);
  }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTnome = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTid = new javax.swing.JTextField();
        jTPesquisa = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablepr = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jTnomeProduto = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTidPR = new javax.swing.JTextField();
        jTPesquisapr = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jTvalor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Mostrar Todos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTnome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnomeKeyPressed(evt);
            }
        });

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Deletar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel2.setText("ID:");

        jTid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTidActionPerformed(evt);
            }
        });

        jTPesquisa.setText("Digite o nome do cliente aqui");
        jTPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTPesquisaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTPesquisaFocusLost(evt);
            }
        });
        jTPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTPesquisaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTPesquisaKeyReleased(evt);
            }
        });

        jButton4.setText("Pesquisar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setText("Novo");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTid, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton9)
                    .addComponent(jButton3))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addContainerGap(299, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(113, 113, 113)))
        );

        jTabbedPane1.addTab("CLIENTE", jPanel2);

        jTablepr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ));
        jTablepr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableprMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablepr);

        jButton5.setText("Mostrar Todos");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Salvar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Deletar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome:");

        jLabel4.setText("ID:");

        jTidPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTidPRActionPerformed(evt);
            }
        });

        jTPesquisapr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTPesquisaprKeyReleased(evt);
            }
        });

        jButton8.setText("Pesquisar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel5.setText("Valor:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTPesquisapr, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTidPR, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTnomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addContainerGap(644, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jLabel3)
                    .addComponent(jTnomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTidPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPesquisapr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addContainerGap(270, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("PRODUTO", jPanel3);

        jMenu1.setText("ARQUIVO");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("EDITAR");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateDB(coluna,ordena);        // TODO add your handling code here:
        ativapesquisa=false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            
            if(salvarcli==true)
            {
                   ps = con.prepareStatement("insert into clientes (nomecliente) values('"+jTnome.getText()+"')");
                   ps.execute();
                   updateDB(coluna,ordena);
                   JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
                   salvarcli=false;
                   
                    ps = con.prepareStatement("SELECT * FROM clientes ORDER BY idcliente DESC LIMIT 1");
                    rs = ps.executeQuery();
                    rs.next();
                    jTid.setText(rs.getString("idcliente"));
                    jTnome.setText(rs.getString("nomecliente"));
                   
               
            }else
            {
                 int r = JOptionPane.showConfirmDialog(null,"Deseja fazer a alteração?", "Alteração",1);
                 if(r==0)
                {
                     ps = con.prepareStatement("update clientes set nomecliente = '"+jTnome.getText()+"' where idcliente="+jTid.getText());
                     ps.execute();
                     if(ativapesquisa==true)
                     {
                         updateDBFilter(coluna,ordena);
                         ativapesquisa=true;
                     }else
                     {
                         updateDB(coluna, ordena);
                         ativapesquisa=false;
                     }
                     
                     //JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
            
                }
                    
            }

            
            
        }catch(Exception e){System.out.print("erro no insert "+e);}
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          //jTable1 = ((JTableHeader)evt.getSource()).getTable();
    TableColumnModel colModel = jTable1.getColumnModel();
     
    // índice da coluna cujo titulo foi clicado
    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
    int mColIndex = jTable1.convertColumnIndexToModel(vColIndex);
     
    if(vColIndex == -1) {
      return;
    }
     
    System.out.println("O clique ocorreu no tituloda coluna com indice " + mColIndex);
    
    
    id =(String) jTable1.getModel().getValueAt(  jTable1.getSelectedRow() ,0);

    jTnome.setText((String) jTable1.getModel().getValueAt(  jTable1.getSelectedRow() ,1));
    jTid.setText((String) jTable1.getModel().getValueAt(  jTable1.getSelectedRow() ,0));
    salvarcli=false;
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 try
        {
            
            con = DriverManager.getConnection(url,"root","1234");
            
            int res = JOptionPane.showConfirmDialog(null,"Deletar: "+(String) jTable1.getModel().getValueAt(  jTable1.getSelectedRow() ,1)+"?","Deletar",JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(res==0)
            {
                ps = con.prepareStatement("delete from clientes where idcliente="+id);
                ps.execute();
                updateDB(coluna,ordena);
                
                ps = con.prepareStatement("SELECT * FROM clientes ORDER BY idcliente DESC LIMIT 1");
                rs = ps.executeQuery();
                rs.next();
                jTid.setText(rs.getString("idcliente"));
                jTnome.setText(rs.getString("nomecliente"));
                
            }else{
                JOptionPane.showMessageDialog(null, "Cancelado");
            }
            ativapesquisa=false;
            
        }catch(SQLException e){{System.out.print("erro no delete "+e);} 
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTidActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ativapesquisa=true;
        updateDBFilter(coluna, ordena);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPesquisaKeyReleased
            ativapesquisa=true;
            pesquisa = pesquisa+jTPesquisa.getText();
            updateDBFilter(coluna, ordena);
    
    }//GEN-LAST:event_jTPesquisaKeyReleased

    private void jTableprMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableprMouseClicked
        TableColumnModel colModel = jTablepr.getColumnModel();
     
    // índice da coluna cujo titulo foi clicado
    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
    int mColIndex = jTablepr.convertColumnIndexToModel(vColIndex);
     
    if(vColIndex == -1) {
      return;
    }
     
    System.out.println("O clique ocorreu no titulo da coluna com indice " + mColIndex);
    
    
    idpr =(String) jTablepr.getModel().getValueAt(  jTablepr.getSelectedRow() ,0);

    jTnomeProduto.setText((String) jTablepr.getModel().getValueAt(  jTablepr.getSelectedRow() ,1));
    jTidPR.setText((String) jTablepr.getModel().getValueAt(  jTablepr.getSelectedRow() ,0));
    jTvalor.setText((String) jTablepr.getModel().getValueAt(  jTablepr.getSelectedRow() ,2));
    }//GEN-LAST:event_jTableprMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        updateDBPR(colunapr, ordenapr);
        ativapesquisapr=false;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
          try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            ps = con.prepareStatement("insert into produtos (nomeproduto,valorunit) values('"+jTnomeProduto.getText()+"',"
                    +Double.parseDouble(jTvalor.getText())+")");
            ps.execute();
            
            updateDBPR(colunapr,ordenapr);
            ativapesquisapr=false;
        }catch(Exception e){System.out.print("erro no insert "+e);}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            
            int res = JOptionPane.showConfirmDialog(null,"Deletar +"+(String)  jTablepr.getModel().getValueAt(  jTablepr.getSelectedRow() ,1)+"?","Deletar",JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(res==0)
            {
                ps = con.prepareStatement("delete from produtos where idproduto="+idpr);
                ps.execute();
                updateDBPR(colunapr,ordenapr);
                
            }else{
                JOptionPane.showMessageDialog(null, "Cancelado");
            }
            ativapesquisapr=false;
            
        }catch(Exception e){System.out.print("erro no delete "+e);}
                // TODO add your handling code here:
                // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTidPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTidPRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTidPRActionPerformed

    private void jTPesquisaprKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPesquisaprKeyReleased
            ativapesquisapr=true;
            pesquisapr = pesquisapr+jTPesquisapr.getText();
            updateDBFilterPR(colunapr, ordenapr);
    }//GEN-LAST:event_jTPesquisaprKeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ativapesquisapr=true;
        updateDBFilterPR(colunapr, ordenapr);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       atualiza=false;
       try
       {
           con = DriverManager.getConnection(url,"root","1234");
           
                   ps = con.prepareStatement("select max(idcliente) from clientes");
                   rs= ps.executeQuery();
                   rs.next();
                   
                   System.out.print(rs.getString("max(idcliente)"));
                   int ultimo = Integer.parseInt(rs.getString("max(idcliente)"));
                   int idfinal = ultimo+1;
                   jTid.setText(""+idfinal);
                   
   
       }catch(SQLException e){System.out.print(e+"");}
       salvarcli=true;
       jTnome.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTPesquisaFocusGained
        jTPesquisa.setText("");
    }//GEN-LAST:event_jTPesquisaFocusGained

    private void jTPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPesquisaKeyPressed
     if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
        System.out.print("enter");
   }
    }//GEN-LAST:event_jTPesquisaKeyPressed

    private void jTnomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnomeKeyPressed
 if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
        System.out.print("enter");
   
        
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            
            if(salvarcli==true)
            {
                   ps = con.prepareStatement("insert into clientes (nomecliente) values('"+jTnome.getText()+"')");
                   ps.execute();
                   updateDB(coluna,ordena);
                   JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
                   
               
            }else
            {
                 int r = JOptionPane.showConfirmDialog(null,"Deseja fazer a alteração?", "Alteração",1);
                 if(r==0)
                {
                     ps = con.prepareStatement("update clientes set nomecliente = '"+jTnome.getText()+"' where idcliente="+jTid.getText());
                     ps.execute();
                     if(ativapesquisa==true)
                     {
                         updateDBFilter(coluna,ordena);
                         ativapesquisa=true;
                     }else
                     {
                         updateDB(coluna, ordena);
                         ativapesquisa=false;
                     }
                     
                     //JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
            
                }
                    
            }

            
            
        }catch(Exception e){System.out.print("erro no insert "+e);}
        
 }
                // TODO add your handling code here:
    }//GEN-LAST:event_jTnomeKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTPesquisaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTPesquisaFocusLost
       if(jTPesquisa.getText().equalsIgnoreCase(""))
       {
           jTPesquisa.setText("Digite o nome do cliente aqui...");
       }
    }//GEN-LAST:event_jTPesquisaFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTPesquisa;
    private javax.swing.JTextField jTPesquisapr;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTablepr;
    private javax.swing.JTextField jTid;
    private javax.swing.JTextField jTidPR;
    private javax.swing.JTextField jTnome;
    private javax.swing.JTextField jTnomeProduto;
    private javax.swing.JTextField jTvalor;
    // End of variables declaration//GEN-END:variables
/****************CLIENTE*****************/
     public void updateDB(String coluna,String ordem )
    {
        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            ps = con.prepareStatement("select * from clientes order by("+coluna+") "+ordem);
            rs= ps.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel RecordTable = (DefaultTableModel) jTable1.getModel();
            RecordTable.setRowCount(0);
            
            while(rs.next())
            {
                Vector columData = new Vector();
                for(int i=1;i<=q;i++)
                {
                    columData.add(rs.getString("idcliente"));
                    columData.add(rs.getString("nomecliente"));
                }
                RecordTable.addRow(columData);
            }
        }catch(Exception e){System.out.print("erro no select "+e);}
        
    }
     
     public void updateDBFilter(String coluna,String ordem )
    {
        try
        {
            pesquisa = jTPesquisa.getText();
            
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            ps = con.prepareStatement("select * from clientes where nomecliente like'%"+pesquisa+"%' order by("+coluna+") "+ordem);
            rs= ps.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel RecordTable = (DefaultTableModel) jTable1.getModel();
            RecordTable.setRowCount(0);
            
            while(rs.next())
            {
                Vector columData = new Vector();
                for(int i=1;i<=q;i++)
                {
                    columData.add(rs.getString("idcliente"));
                    columData.add(rs.getString("nomecliente"));
                }
                RecordTable.addRow(columData);
            }
        }catch(Exception e){System.out.print("erro no select "+e);}
        
    }
/****************CLIENTE/FIM*****************|\*/
     
     
/****************PRODUTO*****************|\*/
     
      public void updateDBPR(String coluna,String ordem )
    {
        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            ps = con.prepareStatement("select * from produtos order by("+coluna+") "+ordem);
            rs= ps.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel RecordTable = (DefaultTableModel) jTablepr.getModel();
            RecordTable.setRowCount(0);
            
            while(rs.next())
            {
                Vector columData = new Vector();
                for(int i=1;i<=q;i++)
                {
                    columData.add(rs.getString("idproduto"));
                    columData.add(rs.getString("nomeproduto"));
                    columData.add(rs.getString("valorunit"));
                }
                RecordTable.addRow(columData);
            }
        }catch(Exception e){System.out.print("erro no select pro"+e);}
        
    }
     
     public void updateDBFilterPR(String coluna,String ordem )
    {
        try
        {
            pesquisapr = jTPesquisapr.getText();
            
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","1234");
            ps = con.prepareStatement("select * from produtos where nomeproduto like'%"+pesquisapr+"%' order by("+colunapr+") "+ordem);
            rs= ps.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel RecordTable = (DefaultTableModel) jTablepr.getModel();
            RecordTable.setRowCount(0);
            
            while(rs.next())
            {
                Vector columData = new Vector();
                for(int i=1;i<=q;i++)
                {
                     columData.add(rs.getString("idproduto"));
                    columData.add(rs.getString("nomeproduto"));
                    columData.add(rs.getString("valorunit"));
                }
                RecordTable.addRow(columData);
            }
        }catch(Exception e){System.out.print("erro no select pr filt "+e);}
        
    }
}
