package GUI;
import Database.*;
import MainClasses.*;
import Controllers.*;
import java.util.*;


public class OrderHistory extends javax.swing.JFrame {
   Client client;
   ClientDAO clientDAO=new ClientDAO();
 private OrderHistoryController orderHistoryController = new OrderHistoryController();


   
    public OrderHistory() {
        initComponents();
    }

    
    public OrderHistory(Client client) {
        this.client=client;
        initComponents();
        jLabel21.setText(client.getName()); 
        loadOrdersIntoComboBox();           
     
    }
    
   private void loadOrdersIntoComboBox() {
    List<Order> clientOrders = orderHistoryController.getOrdersByClientId(client.getClientID());
    jComboBox1.removeAllItems();
    for (Order order : clientOrders) {
        jComboBox1.addItem("Order of " + order.getPhone().getModelName());
    }
}



    private void fillOrderDetails(Order selectedOrder) {
    orderidlabel.setText(String.valueOf(selectedOrder.getOrderID()));
    phonenamelabel.setText(selectedOrder.getPhone().getModelName());
    phonebrandlabel.setText(selectedOrder.getPhone().getBrand());
    pricelabel.setText(String.valueOf(selectedOrder.getPhone().getPrice()));
    quantitylabel.setText(String.valueOf(selectedOrder.getQuantity()));
    orderstatuslabel.setText(selectedOrder.getorderStatus());
    Paymenttypelabel.setText(selectedOrder.getPayment().getPaymentStrategy().getMethod());
    paymentstatuslabel.setText(selectedOrder.getPayment().getPaymentStatus());
}

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        quantitylabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        phonebrandlabel = new javax.swing.JLabel();
        pricelabel = new javax.swing.JLabel();
        phonenamelabel = new javax.swing.JLabel();
        paymentstatuslabel = new javax.swing.JLabel();
        orderidlabel = new javax.swing.JLabel();
        orderstatuslabel = new javax.swing.JLabel();
        Paymenttypelabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Your Order History");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 230, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("here’s your order history");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hi  ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 30, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Order Status:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 90, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 160, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Choose your Order you want to view");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 260, 20));

        quantitylabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        quantitylabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(quantitylabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 60, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Phone Name:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 100, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Price:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 40, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Order ID:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 70, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Phone Brand:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 100, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Payment type:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 100, -1));

        jButton9.setBackground(new java.awt.Color(255, 255, 250));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton9.setText("Select Order");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 130, 20));

        jButton10.setBackground(new java.awt.Color(255, 255, 250));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton10.setText("Home Page");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 120, -1));

        jButton5.setBackground(new java.awt.Color(255, 255, 250));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton5.setText("Cleint Dashboard");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 170, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Payment Status:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 110, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Quantity:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 70, -1));

        jButton6.setBackground(new java.awt.Color(255, 255, 250));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton6.setText("Log out");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 120, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 60, -1));

        phonebrandlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        phonebrandlabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(phonebrandlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 80, -1));

        pricelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pricelabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(pricelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 60, -1));

        phonenamelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        phonenamelabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(phonenamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 110, -1));

        paymentstatuslabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paymentstatuslabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(paymentstatuslabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 100, -1));

        orderidlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderidlabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(orderidlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 60, -1));

        orderstatuslabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderstatuslabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(orderstatuslabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 110, -1));

        Paymenttypelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Paymenttypelabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(Paymenttypelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 110, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/option2.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    List<Order> clientOrders = orderHistoryController.getOrdersByClientId(client.getClientID());
    int selectedIndex = jComboBox1.getSelectedIndex();
    if (selectedIndex >= 0 && selectedIndex < clientOrders.size()) {
        Order selectedOrder = clientOrders.get(selectedIndex);
        fillOrderDetails(selectedOrder);
    }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       new ClientDashboard(client).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Paymenttypelabel;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel orderidlabel;
    private javax.swing.JLabel orderstatuslabel;
    private javax.swing.JLabel paymentstatuslabel;
    private javax.swing.JLabel phonebrandlabel;
    private javax.swing.JLabel phonenamelabel;
    private javax.swing.JLabel pricelabel;
    private javax.swing.JLabel quantitylabel;
    // End of variables declaration//GEN-END:variables
}
