/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.products_manager;

/**
 *
 * @author ASUS
 */
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;//**
import javax.swing.BorderFactory;//**
import javax.swing.ImageIcon;//**
import javax.swing.JLabel;//**
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Products_Frame extends javax.swing.JFrame {
    
    int positionX,positionY;
    int position_in_table;// for navigating buttons

    /**
     * Creates new form Products_Frame
     */
    //creating border here
    Border border_of_panel= BorderFactory.createMatteBorder(20, 20, 20, 20, Color.DARK_GRAY);//**
    Border border_of_textField= BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
    ArrayList<Product> productsArray= new ArrayList<>();
    
    public Products_Frame() {
        
        initComponents();
        //to make the form at centre
        this.setLocationRelativeTo(null);//**
        
        displayImage("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Products_Manager\\src\\main\\java\\Images\\close-button-png-30241.png", jLabel_Close);
        displayImage("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Products_Manager\\src\\main\\java\\Images\\maximize-button.png", jLabel_Close1);
        displayImage("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Products_Manager\\src\\main\\java\\Images\\minimize-icon-23776.png", jLabel_Close2);
        
        //setting the border
        jPanel_Container.setBorder(border_of_panel);//**
        jTextField_name.setBorder(border_of_textField);
        jTextField_quantity.setBorder(border_of_textField);
        jTextField_price.setBorder(border_of_textField);
        jTextField_image_path.setBorder(border_of_textField);
        
        jTable_products_table.setRowHeight(30);
        
        showProductsinTable();//** here it is called so that when app appears the contents are already present there.
    }
    //a function to check empty fields
   
    public boolean checkEmptyFields()
    {
        String name= jTextField_name.getText().trim();
        String quantity= jTextField_quantity.getText().trim();
        String price= jTextField_price.getText().trim();
        String image_path= jTextField_image_path.getText().trim();
        
        if(name.equals("")|| quantity.equals("")|| price.equals("")|| image_path.equals(""))
        {
            return false;
        }
        else
        return true;
    }
    public void displayImage(String ImagePath, JLabel Label)//**
    {
        ImageIcon ImgIco= new ImageIcon(ImagePath);//**
        Image img= ImgIco.getImage().getScaledInstance(Label.getWidth(), Label.getHeight(), Image.SCALE_SMOOTH);//**
        Label.setIcon(new ImageIcon(img));
    }
    //to clear the fields
    public void clearFields()
    {
        jSpinner_id.setValue(0);
        jTextField_name.setText("");
        jComboBox_category.setSelectedIndex(0);
        jTextField_quantity.setText("");
        jTextField_price.setText("");
        jTextField_image_path.setText("");
        jLabel_image.setIcon(null);
    }
    public ArrayList<Product> getProductsList()// returns arraylist of products
    {
        ArrayList<Product> list=new ArrayList<>();
        String selectQuery="select * from products";
        
        Statement st;
        ResultSet rs;
        
        try {
            st=Data_Base.getConnection().createStatement();
            rs=st.executeQuery(selectQuery);
            
            Product product;
            
            while(rs.next())
            {
                product =new Product(rs.getInt("ID"), rs.getString("Name"),rs.getString("Category"),rs.getInt("Quantity"),rs.getDouble("Price"),
                        rs.getString("Image_path"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Products_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        productsArray=list;
        return list;
    } 
    public void showProductsinTable()// to show products in the table.// this function actually fills the row for a particular product
    {
       ArrayList<Product> productsList= getProductsList();
       DefaultTableModel model= (DefaultTableModel) jTable_products_table.getModel();
       model.setRowCount(0);//first row to start from 0.
       Object row[]=new Object[6];//6 is the number of columns//this is created becuase addRow takes and array of objects of rowData
       
       for(int i=0;i<productsList.size();i++)
       {
           row[0]=productsList.get(i).getID();
           row[1]=productsList.get(i).getName();
           row[2]=productsList.get(i).getCategory();
           row[3]=productsList.get(i).getQuantity();
           row[4]=productsList.get(i).getPrice();
           row[5]=productsList.get(i).getImage_path();
           
           model.addRow(row);
       }
    }
    //function to dispaly products by index
    public void show_products_by_index(int index)
    {
        jSpinner_id.setValue(getProductsList().get(index).getID());
        jTextField_name.setText(getProductsList().get(index).getName());
        jComboBox_category.setSelectedItem(getProductsList().get(index).getCategory());
        jTextField_quantity.setText(getProductsList().get(index).getQuantity().toString());
        jTextField_price.setText(getProductsList().get(index).getPrice().toString());
        jTextField_image_path.setText(getProductsList().get(index).getImage_path());
        displayImage(getProductsList().get(index).getImage_path(),jLabel_image);
        
    }
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Container = new javax.swing.JPanel();
        jLabel_Close = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_products_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSpinner_id = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jComboBox_category = new javax.swing.JComboBox<>();
        jTextField_quantity = new javax.swing.JTextField();
        jTextField_price = new javax.swing.JTextField();
        jLabel_image = new javax.swing.JLabel();
        jButton_edit = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jButton_remove = new javax.swing.JButton();
        jButton_search = new javax.swing.JButton();
        jButton_browse = new javax.swing.JButton();
        jTextField_image_path = new javax.swing.JTextField();
        jButton_previous = new javax.swing.JButton();
        jButton_last = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_first = new javax.swing.JButton();
        jLabel_Close1 = new javax.swing.JLabel();
        jLabel_Close2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel_Container.setBackground(new java.awt.Color(204, 204, 204));
        jPanel_Container.setPreferredSize(new java.awt.Dimension(1300, 770));
        jPanel_Container.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jPanel_ContainerComponentAdded(evt);
            }
        });
        jPanel_Container.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel_ContainerMouseDragged(evt);
            }
        });
        jPanel_Container.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_ContainerMousePressed(evt);
            }
        });

        jLabel_Close.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_Close.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel_Close.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Close.setOpaque(true);
        jLabel_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CloseMouseClicked(evt);
            }
        });

        jTable_products_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Category", "Quantity", "Price", "Image"
            }
        ));
        jTable_products_table.setMaximumSize(new java.awt.Dimension(450, 100));
        jTable_products_table.setSelectionBackground(new java.awt.Color(255, 102, 51));
        jTable_products_table.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable_products_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_products_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_products_table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID:");

        jSpinner_id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jSpinner_id.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner_id.setMaximumSize(new java.awt.Dimension(80, 33));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Category:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Quantity:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Price:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Image:");

        jTextField_name.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField_name.setForeground(new java.awt.Color(0, 0, 0));

        jComboBox_category.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cars", "Motor Bikes", "Bicycles", "Military Vehicles", " " }));
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });

        jTextField_quantity.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_quantity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField_quantity.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_quantityActionPerformed(evt);
            }
        });
        jTextField_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_quantityKeyTyped(evt);
            }
        });

        jTextField_price.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_price.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField_price.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_priceKeyReleased(evt);
            }
        });

        jLabel_image.setBackground(new java.awt.Color(153, 153, 153));
        jLabel_image.setOpaque(true);

        jButton_edit.setBackground(new java.awt.Color(0, 0, 0));
        jButton_edit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_edit.setText("EDIT");
        jButton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editActionPerformed(evt);
            }
        });

        jButton_add.setBackground(new java.awt.Color(0, 0, 0));
        jButton_add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_add.setText("ADD");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_remove.setBackground(new java.awt.Color(0, 0, 0));
        jButton_remove.setForeground(new java.awt.Color(255, 255, 255));
        jButton_remove.setText("REMOVE");
        jButton_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeActionPerformed(evt);
            }
        });

        jButton_search.setBackground(new java.awt.Color(255, 102, 0));
        jButton_search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_search.setForeground(new java.awt.Color(0, 0, 0));
        jButton_search.setText("Search");
        jButton_search.setOpaque(true);
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jButton_browse.setBackground(new java.awt.Color(255, 102, 0));
        jButton_browse.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_browse.setForeground(new java.awt.Color(0, 0, 0));
        jButton_browse.setText("Browse");
        jButton_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browseActionPerformed(evt);
            }
        });

        jTextField_image_path.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_image_path.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_image_path.setEnabled(false);
        jTextField_image_path.setMaximumSize(new java.awt.Dimension(6, 19));
        jTextField_image_path.setMinimumSize(new java.awt.Dimension(6, 19));
        jTextField_image_path.setPreferredSize(new java.awt.Dimension(10, 20));

        jButton_previous.setBackground(new java.awt.Color(0, 0, 0));
        jButton_previous.setForeground(new java.awt.Color(255, 255, 255));
        jButton_previous.setText("<");
        jButton_previous.setMaximumSize(new java.awt.Dimension(96, 29));
        jButton_previous.setMinimumSize(new java.awt.Dimension(96, 29));
        jButton_previous.setPreferredSize(new java.awt.Dimension(96, 29));
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });

        jButton_last.setBackground(new java.awt.Color(0, 0, 0));
        jButton_last.setForeground(new java.awt.Color(255, 255, 255));
        jButton_last.setText(">>");
        jButton_last.setMaximumSize(new java.awt.Dimension(96, 29));
        jButton_last.setMinimumSize(new java.awt.Dimension(96, 29));
        jButton_last.setPreferredSize(new java.awt.Dimension(96, 29));
        jButton_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lastActionPerformed(evt);
            }
        });

        jButton_next.setBackground(new java.awt.Color(0, 0, 0));
        jButton_next.setForeground(new java.awt.Color(255, 255, 255));
        jButton_next.setText(">");
        jButton_next.setMaximumSize(new java.awt.Dimension(96, 29));
        jButton_next.setMinimumSize(new java.awt.Dimension(96, 29));
        jButton_next.setPreferredSize(new java.awt.Dimension(96, 29));
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jButton_first.setBackground(new java.awt.Color(0, 0, 0));
        jButton_first.setForeground(new java.awt.Color(255, 255, 255));
        jButton_first.setText("<<");
        jButton_first.setMaximumSize(new java.awt.Dimension(96, 29));
        jButton_first.setMinimumSize(new java.awt.Dimension(96, 29));
        jButton_first.setPreferredSize(new java.awt.Dimension(96, 29));
        jButton_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_firstActionPerformed(evt);
            }
        });

        jLabel_Close1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_Close1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel_Close1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Close1.setOpaque(true);
        jLabel_Close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_Close1MouseClicked(evt);
            }
        });

        jLabel_Close2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_Close2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel_Close2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Close2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Close2.setOpaque(true);
        jLabel_Close2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_Close2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ContainerLayout = new javax.swing.GroupLayout(jPanel_Container);
        jPanel_Container.setLayout(jPanel_ContainerLayout);
        jPanel_ContainerLayout.setHorizontalGroup(
            jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_browse, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jTextField_image_path, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField_name)
                        .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                            .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addComponent(jButton_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jTextField_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addComponent(jTextField_price, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addComponent(jComboBox_category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jButton_add)
                .addGap(18, 18, 18)
                .addComponent(jButton_edit)
                .addGap(18, 18, 18)
                .addComponent(jButton_remove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_first, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_previous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_last, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel_Close2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_Close1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_ContainerLayout.setVerticalGroup(
            jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Close1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_Close2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_search))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                                .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jTextField_image_path, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))))
                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_first, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_next, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_last, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CloseMouseClicked
        // Closes Form
        this.dispose();//**
        
    }//GEN-LAST:event_jLabel_CloseMouseClicked

    private void jComboBox_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_categoryActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        //Adds a new product//
        //INSERT INTO `products`('ID',`Name`, `Category`, `Quantity`, `Price`, `Image_path`) VALUES (?,?,?,?,?,?)
        if(checkEmptyFields())
        {
        Integer ID = Integer.valueOf(jSpinner_id.getValue().toString());
        String Name = jTextField_name.getText(); 
        String Category = jComboBox_category.getSelectedItem().toString();
        Integer Quantity = Integer.valueOf(jTextField_quantity.getText());
        Double Price = Double.valueOf(jTextField_price.getText());
        String Image_path = jTextField_image_path.getText();
        
        
String insertQuery = "INSERT INTO `products`(`ID`, `Name`, `Category`, `Quantity`, `Price`, `Image_path`) VALUES (?,?,?,?,?,?)";
                
        try {
            PreparedStatement ps = Data_Base.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, ID);
            ps.setString(2, Name);
            ps.setString(3, Category);
            ps.setInt(4, Quantity);
            ps.setDouble(5, Price);
            ps.setString(6, Image_path);
            
            if(ps.executeUpdate()>0)
            {
                showProductsinTable();
                JOptionPane.showMessageDialog(null, "Product added successfully", "Add Product", JOptionPane.INFORMATION_MESSAGE);
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product not added,check if ID is valid", "Add Product", JOptionPane.ERROR_MESSAGE);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Products_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Product not added,check if all fields are filled", "Add Product", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editActionPerformed
        // edits the selected product//**
        //Integer ID = jSpinner_id.getComponentCount();
        if(checkEmptyFields())
        {    
        Integer ID= Integer.valueOf(jSpinner_id.getValue().toString());
        String Name = jTextField_name.getText(); 
        String Category = jComboBox_category.getSelectedItem().toString();
        Integer Quantity = Integer.valueOf(jTextField_quantity.getText());
        Double Price = Double.valueOf(jTextField_price.getText());
        String Image_path = jTextField_image_path.getText();
        
        String updateQuery = "UPDATE `products` SET `Name`=?,`Category`=?,`Quantity`=?,`Price`=?,`Image_path`=? WHERE ID=?";
        try {
            PreparedStatement ps = Data_Base.getConnection().prepareStatement(updateQuery);
            
            ps.setString(1, Name);
            ps.setString(2, Category);
            ps.setInt(3, Quantity);
            ps.setDouble(4, Price);
            ps.setString(5, Image_path);
            ps.setInt(6, ID);
            
            if(ps.executeUpdate()>0)
            {
                showProductsinTable();
                JOptionPane.showMessageDialog(null, "Product updated successfully", "Edit Product", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Product Updated");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product not updated,check if ID is valid", "Edit Product", JOptionPane.ERROR_MESSAGE);
                //System.out.println("Some Error Ocurred Here");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Products_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Product not updated,check if all fields are filled", "Edit Product", JOptionPane.ERROR_MESSAGE);
            //System.out.println("One or more fields are empty");
        }
    }//GEN-LAST:event_jButton_editActionPerformed

    private void jButton_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeActionPerformed
        // removes the selected product//**
        
        if(Integer.valueOf(jSpinner_id.getValue().toString())>0)
        {
        Integer ID= Integer.valueOf(jSpinner_id.getValue().toString());
        
        
        String removeQuery = "DELETE FROM `products` WHERE `ID`=?";
        try {
            PreparedStatement ps = Data_Base.getConnection().prepareStatement(removeQuery);
            ps.setInt(1, ID);
            
            int confirm=JOptionPane.showConfirmDialog(null, "Are you sure,you want to delete product", "Remove Product", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION)
            {
            if(ps.executeUpdate()>0)
            {
                clearFields();
                showProductsinTable();
                JOptionPane.showMessageDialog(null, "Product deleted successfully", "Remove Product", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Product Removed");
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product not deleted,check if ID is valid", "Remove Product", JOptionPane.ERROR_MESSAGE);
                
                //System.out.println("Some Error Ocurred Here");
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Products_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Product not deleted,check if ID is valid", "Remove Product", JOptionPane.ERROR_MESSAGE);
                
            //System.out.println("Inavlid ID");
        }
    }//GEN-LAST:event_jButton_removeActionPerformed

    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        // search a product based on id//**
  
        
        
        int ID= Integer.valueOf(jSpinner_id.getValue().toString());
        String selectQuery = "SELECT * FROM `products` WHERE `ID`="+ID;
        
        try {
            Statement st= Data_Base.getConnection().createStatement();
            ResultSet rs=st.executeQuery(selectQuery);
            
            if(rs.next())
            {
                jTextField_name.setText(rs.getString("Name"));
                jComboBox_category.setSelectedItem(rs.getString("Category"));
                jTextField_quantity.setText(rs.getString("Quantity"));
                jTextField_price.setText(String.valueOf(rs.getDouble("Price")));
                jTextField_image_path.setText(rs.getString("Image_path"));
                displayImage(rs.getString("Image_path"),jLabel_image);
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product not found, ID not available", "Search Product", JOptionPane.ERROR_MESSAGE);
                System.out.println("No product found");   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Products_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browseActionPerformed
        // browse the image and display it//**
        
        JFileChooser filechooser= new JFileChooser();//** 
        filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));//**
        
        FileNameExtensionFilter filter= new FileNameExtensionFilter("*images","*jpg","jpeg","png");//**
        filechooser.addChoosableFileFilter(filter);//**
        
        if(filechooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)//**
        {
            File selected_image = filechooser.getSelectedFile();//**
            String image_path= selected_image.getAbsolutePath();//**
            displayImage(image_path,jLabel_image);//**
            jTextField_image_path.setText(image_path);
            System.out.println(image_path);  //**
            
        } else {
            System.out.println("No File Selected");//**
        }
        
         
    }//GEN-LAST:event_jButton_browseActionPerformed

    private void jTable_products_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_products_tableMouseClicked
        // displays the selected product information
        int index=jTable_products_table.getSelectedRow();
        show_products_by_index(index);
        position_in_table=index;
    }//GEN-LAST:event_jTable_products_tableMouseClicked

    private void jTextField_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_quantityActionPerformed

    private void jPanel_ContainerComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel_ContainerComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel_ContainerComponentAdded

    private void jPanel_ContainerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_ContainerMousePressed
        // get the coordinates
        
        positionX=evt.getX();
        positionY=evt.getY();
    }//GEN-LAST:event_jPanel_ContainerMousePressed

    private void jPanel_ContainerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_ContainerMouseDragged
        // making the JFrame movable
        setLocation(evt.getXOnScreen()-positionX,evt.getYOnScreen()-positionY);
    }//GEN-LAST:event_jPanel_ContainerMouseDragged

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        // TODO add your handling code here:
        
        position_in_table--;
        if(position_in_table<0)
        {
            position_in_table= 0;
        }
        show_products_by_index(position_in_table);
        jTable_products_table.setRowSelectionInterval(position_in_table, position_in_table);
        Rectangle prevRowRectangle = jTable_products_table.getCellRect(position_in_table, 0, true);
        jTable_products_table.scrollRectToVisible(prevRowRectangle);
       
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lastActionPerformed
        // TODO add your handling code here:
        position_in_table=getProductsList().size()-1;
        show_products_by_index(position_in_table);
        jTable_products_table.setRowSelectionInterval(position_in_table, position_in_table);
        Rectangle lastRowRectangle = jTable_products_table.getCellRect(position_in_table, 0, true);
        jTable_products_table.scrollRectToVisible(lastRowRectangle);
    }//GEN-LAST:event_jButton_lastActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        // TODO add your handling code here:
        position_in_table++;
        if(position_in_table>getProductsList().size()-1)
        {
            position_in_table=getProductsList().size()-1;
        }
        show_products_by_index(position_in_table);
        jTable_products_table.setRowSelectionInterval(position_in_table, position_in_table);
        Rectangle nextRowRectangle = jTable_products_table.getCellRect(position_in_table, 0, true);
        jTable_products_table.scrollRectToVisible(nextRowRectangle);
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_firstActionPerformed
        // TODO add your handling code here:
        position_in_table=0;
        show_products_by_index(position_in_table);
        jTable_products_table.setRowSelectionInterval(position_in_table, position_in_table);
        //select row when the navigating buttons are clicked
        Rectangle firstRowRectangle = jTable_products_table.getCellRect(position_in_table, 0, true);
        jTable_products_table.scrollRectToVisible(firstRowRectangle);
    }//GEN-LAST:event_jButton_firstActionPerformed

    private void jTextField_quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_quantityKeyTyped
        // TODO add your handling code here:
        //allows only numbers to be typed in the space given
        if(!Character.isDigit(evt.getKeyChar()))
        {
            evt.consume();// hence anything other than the digits will be consumed and not be shown
        }
    }//GEN-LAST:event_jTextField_quantityKeyTyped

    private void jTextField_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_priceKeyReleased
        // TODO add your handling code here:
        //allows double 
        try
        {
            Double.valueOf(jTextField_price.getText());
        }
        catch(NumberFormatException e)
        {
            jTextField_price.setText("");
        }
    }//GEN-LAST:event_jTextField_priceKeyReleased

    private void jLabel_Close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Close1MouseClicked
        // TODO add your handling code here:
        setExtendedState(getExtendedState() == Frame.MAXIMIZED_BOTH ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jLabel_Close1MouseClicked

    private void jLabel_Close2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Close2MouseClicked
        // TODO add your handling code here:
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel_Close2MouseClicked

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
            java.util.logging.Logger.getLogger(Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Products_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Products_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JButton jButton_edit;
    private javax.swing.JButton jButton_first;
    private javax.swing.JButton jButton_last;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_remove;
    private javax.swing.JButton jButton_search;
    private javax.swing.JComboBox<String> jComboBox_category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_Close;
    private javax.swing.JLabel jLabel_Close1;
    private javax.swing.JLabel jLabel_Close2;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JPanel jPanel_Container;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_products_table;
    private javax.swing.JTextField jTextField_image_path;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_price;
    private javax.swing.JTextField jTextField_quantity;
    // End of variables declaration//GEN-END:variables
}
