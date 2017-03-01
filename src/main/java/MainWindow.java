import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import org.jfree.chart.*;
/*
 * Created by JFormDesigner on Wed Mar 01 13:06:00 EET 2017
 */



/**
 * @author Alexander Shebanov
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        initComponents();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Alex Shebanov
        menuBar1 = new JMenuBar();
        chartPanel = new JPanel();
        nValue = new JTextField();
        deltaTValue = new JTextField();
        stepsValue = new JTextField();
        graphBuildButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        setJMenuBar(menuBar1);

        //======== chartPanel ========
        {
            chartPanel.setBorder(LineBorder.createBlackLineBorder());

            // JFormDesigner evaluation mark
            chartPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), chartPanel.getBorder())); chartPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


            GroupLayout chartPanelLayout = new GroupLayout(chartPanel);
            chartPanel.setLayout(chartPanelLayout);
            chartPanelLayout.setHorizontalGroup(
                chartPanelLayout.createParallelGroup()
                    .addGap(0, 544, Short.MAX_VALUE)
            );
            chartPanelLayout.setVerticalGroup(
                chartPanelLayout.createParallelGroup()
                    .addGap(0, 368, Short.MAX_VALUE)
            );
        }

        //---- graphBuildButton ----
        graphBuildButton.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(nValue, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addComponent(deltaTValue)
                        .addComponent(stepsValue, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addComponent(graphBuildButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                    .addGap(35, 35, 35))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(nValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(71, 71, 71)
                            .addComponent(deltaTValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73)
                            .addComponent(stepsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(graphBuildButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(34, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Alex Shebanov
    private JMenuBar menuBar1;
    private JPanel chartPanel;
    private JTextField nValue;
    private JTextField deltaTValue;
    private JTextField stepsValue;
    private JButton graphBuildButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
