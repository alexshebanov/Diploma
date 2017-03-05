import java.awt.*;
import java.awt.event.*;
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
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void graphBuildButtonActionPerformed(ActionEvent e) {
        try {
            int vortexPointsCount = Integer.parseInt(nValue.getText());
            double deltaT = Double.parseDouble(deltaTValue.getText());
            int tCount = Integer.parseInt(stepsValue.getText());

            ChartPanel graph = new ChartPanel(new Application().chart(vortexPointsCount, deltaT, tCount));
            chartPanel.removeAll();
            chartPanel.add(graph);
            chartPanel.updateUI();

        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Некорректно введены параметры");
        }

    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Alex Shebanov
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        chartPanel = new JPanel();
        nValue = new JTextField();
        deltaTValue = new JTextField();
        stepsValue = new JTextField();
        graphBuildButton = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u0424\u0430\u0439\u043b");

                //---- menuItem1 ----
                menuItem1.setText("\u0412\u044b\u0445\u043e\u0434");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== chartPanel ========
        {
            chartPanel.setBorder(LineBorder.createBlackLineBorder());

//            // JFormDesigner evaluation mark
//            chartPanel.setBorder(new javax.swing.border.CompoundBorder(
//                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
//                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
//                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
//                    java.awt.Color.red), chartPanel.getBorder())); chartPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            chartPanel.setLayout(new BorderLayout());
        }

        //---- graphBuildButton ----
        graphBuildButton.setText("\u0420\u0430\u0441\u0447\u0438\u0442\u0430\u0442\u044c");
        graphBuildButton.addActionListener(e -> graphBuildButtonActionPerformed(e));

        //---- label1 ----
        label1.setText("\u041a\u043e\u043b-\u0432\u043e \u0432\u0438\u0445\u0440\u0435\u0432\u044b\u0445 \u0442\u043e\u0447\u0435\u043a");
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("deltaT");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("\u041a\u043e\u043b-\u0432\u043e \u0432\u0440\u0435\u043c\u0435\u043d\u043d\u044b\u0445 \u0448\u0430\u0433\u043e\u0432");
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(stepsValue, GroupLayout.Alignment.LEADING)
                        .addComponent(deltaTValue, GroupLayout.Alignment.LEADING)
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nValue, GroupLayout.Alignment.LEADING)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(graphBuildButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                    .addGap(64, 64, 64))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deltaTValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stepsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(graphBuildButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Alex Shebanov
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JPanel chartPanel;
    private JTextField nValue;
    private JTextField deltaTValue;
    private JTextField stepsValue;
    private JButton graphBuildButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
