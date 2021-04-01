/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplcassignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;


public class DashboardView extends javax.swing.JFrame {
    private List<Daerah> daerah;

    public DashboardView(List<Daerah> daerah) {
        this.daerah=daerah;
        initComponents();
    }
    
    public DashboardView() {
        initComponents();
    }
    
    
    public Object[][] populateData(String[] column,List<Daerah> daerah){
        Object data[][] = new Object[daerah.size()][column.length];
        
        try{
            
            IntStream.range(0, daerah.size()).forEach(index->{
            data[index][0]=daerah.get(index).getDaerah();
            data[index][1]= daerah.get(index).getDate().entrySet().stream().filter(map -> map.getKey().equals(2014)).map(Map.Entry::getValue).findFirst().get();
            data[index][2]= daerah.get(index).getDate().entrySet().stream().filter(map -> map.getKey().equals(2015)).map(Map.Entry::getValue).findFirst().get();
            data[index][3]= daerah.get(index).getDate().entrySet().stream().filter(map -> map.getKey().equals(2016)).map(Map.Entry::getValue).findFirst().get();
            data[index][4]= daerah.get(index).getDate().entrySet().stream().filter(map -> map.getKey().equals(2017)).map(Map.Entry::getValue).findFirst().get();
            data[index][5]= daerah.get(index).getDate().entrySet().stream().filter(map -> map.getKey().equals(2018)).map(Map.Entry::getValue).findFirst().get();
            data[index][6]= daerah.get(index).getDate().entrySet().stream().filter(map -> map.getKey().equals(2019)).map(Map.Entry::getValue).findFirst().get();
            });
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Index out of Bound");
        }
        
        return data;
    }
    
    
    public void iterate(List<Integer> totalCase, List<Daerah> daerah, BiConsumer<Integer,Daerah> consumer){
        IntStream.range(0, totalCase.size()).forEach(index ->{
            daerah.stream().forEach(d->{
                try{
                    consumer.accept(index,d);
                }
                catch(NumberFormatException e){
                    System.err.println(e);
                }
                
            });
            
        });
    }
            
    public List<Integer> totalCasePerYear(String[] column, List<Daerah> daerah){
        //initialize the list with zero
        List<Integer> totalCase =  new ArrayList<>(Collections.nCopies(column.length-1, 0));
        
        iterate(totalCase,daerah,(index,daerahItem) ->{
            ArrayList<Integer> cases = new ArrayList<>(daerahItem.getDate().values()); 
            totalCase.set(index, totalCase.get(index)+cases.get(index));
        });
          
        return totalCase;
    }
    
    BiFunction<String[],List<Daerah>,TreeMap<String,Integer>> gettotalCasePerArea = 
            (column,daerah) ->{
                TreeMap<String,Integer> totalCaseArea= new TreeMap();
                IntStream.range(0, daerah.size()).forEach(index->{
                    ArrayList<Integer> cases = new ArrayList<>(daerah.get(index).getDate().values()); 
                    totalCaseArea.put(daerah.get(index).getDaerah(),cases.stream().mapToInt(Integer::intValue).reduce(0,(x,y)->x+y));
                });
                return totalCaseArea;
    };
        

    public Entry<String, ? extends Number> getLowestCaseArea(TreeMap<String,Integer> totalCaseArea){
        Entry<String, ? extends Number> min = null;
        try{
            min = Collections.min(totalCaseArea.entrySet(),
                                   Comparator.comparing(Entry::getValue));
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
        return min;
    }
    
    public Entry<String, ? extends Number> getHighestCaseArea(TreeMap<String,Integer> totalCaseArea){
        Entry<String, ? extends Number> max = null;
        try{
        max = Collections.max(totalCaseArea.entrySet(),
                                   Comparator.comparing(Entry::getValue));
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
        return max;
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lblLowestArea = new javax.swing.JLabel();
        lblHighestArea = new javax.swing.JLabel();
        lblLowestNumber = new javax.swing.JLabel();
        lblHighestNumber = new javax.swing.JLabel();
        lblTotalCase = new javax.swing.JLabel();
        lblTotalCasePerYear = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnPrintAll = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblData.setBackground(new java.awt.Color(255, 255, 204));
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblData.setGridColor(new java.awt.Color(255, 255, 255));
        tblData.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(tblData);

        lblLowestArea.setText("Lowest Cases Area:");

        lblHighestArea.setText("Highest Cases Area:");

        lblLowestNumber.setText("Lowest Cases Amount:");

        lblHighestNumber.setText("Highest Cases Amount:");

        lblTotalCase.setText("Total Case:");

        lblTotalCasePerYear.setText("Total Case:");

        jPanel1.setBackground(new java.awt.Color(38, 50, 56));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(228, 234, 236));
        lblTitle.setText("Dengue Statistic");

        btnPrintAll.setBackground(new java.awt.Color(38, 50, 56));
        btnPrintAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPrintAll.setForeground(new java.awt.Color(228, 234, 236));
        btnPrintAll.setText("Summary");
        btnPrintAll.setBorder(null);
        btnPrintAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintAllActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(38, 50, 56));
        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(228, 234, 236));
        btnExit.setText("Exit");
        btnExit.setBorder(null);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTitle)
                .addContainerGap(74, Short.MAX_VALUE))
            .addComponent(btnPrintAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitle)
                .addGap(85, 85, 85)
                .addComponent(btnPrintAll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLowestNumber)
                            .addComponent(lblHighestNumber))
                        .addGap(235, 235, 235))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLowestArea)
                            .addComponent(lblHighestArea)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblTotalCase)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTotalCasePerYear)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(49, 49, 49))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLowestNumber)
                    .addComponent(lblLowestArea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHighestNumber)
                    .addComponent(lblHighestArea))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCasePerYear)
                    .addComponent(lblTotalCase))
                .addContainerGap(106, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //Criteria 1: Display/print all dengue cases from 2014-2018
        String column[]={"Daerah","2014","2015","2016","2017","2018","2019"};       
        Object[][] data = populateData(column,daerah);
        tblData.setModel(new javax.swing.table.DefaultTableModel(data,column));
        
        List<Integer> totalCase = totalCasePerYear(column,daerah);
        String result= totalCase.stream().map(e->String.valueOf(e)).reduce("",(x,y)->x+"                  "+y);
        lblTotalCasePerYear.setText(result);
        TreeMap<String,Integer> totalCaseArea = gettotalCasePerArea.apply(column,daerah);
        lblLowestArea.setText("Lowest Case Area: "+ getLowestCaseArea(totalCaseArea).getKey());
        lblHighestArea.setText("Highest Case Area: "+ getHighestCaseArea(totalCaseArea).getKey());
        lblLowestNumber.setText("Lowest Case Amount: "+ getLowestCaseArea(totalCaseArea).getValue());
        lblHighestNumber.setText("Highest Case Amount: "+ getHighestCaseArea(totalCaseArea).getValue());
        
    }//GEN-LAST:event_formWindowOpened

    private void btnPrintAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintAllActionPerformed
        // TODO add your handling code here:
        new SummaryView(daerah).setVisible(true);
    }//GEN-LAST:event_btnPrintAllActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrintAll;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHighestArea;
    private javax.swing.JLabel lblHighestNumber;
    private javax.swing.JLabel lblLowestArea;
    private javax.swing.JLabel lblLowestNumber;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalCase;
    private javax.swing.JLabel lblTotalCasePerYear;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}