/*
 * Created by JFormDesigner on Thu Jun 06 00:30:52 EEST 2013
 */

package scripts.metafisher;

import com.jgoodies.forms.layout.*;
import scripts.metafisher.enums.Banks;
import scripts.metafisher.enums.FishPools;
import scripts.metafisher.enums.FishTools;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;


public class GraphicalInterface extends JFrame {


    public DefaultListModel listModel;

    private Banks chosenBank;
    private FishPools chosenPool;
    private FishTools chosenTool;

    private final Banks[] alLBanks = {Banks.CATHERBY, Banks.AL_KHARID, Banks.DRAYNOR, Banks.EDGEVILLE, Banks.FISHING_GUILD};
    private final FishPools[] allPools = {FishPools.MACKEREL, FishPools.TUNA, FishPools.LOBSTER, FishPools.SHRIMPS, FishPools.HERRING, FishPools.SALMON, FishPools.SHARK};


    private HashMap<Integer, Integer> dropList = new HashMap<Integer, Integer>();

    public boolean powerfish = false;

    public Banks getBank() {
        return chosenBank;
    }

    public FishPools getPool() {
        return chosenPool;
    }

    public FishTools getTool() {
        return chosenTool;
    }

    public HashMap getDropList() {
        return dropList;
    }

    public boolean logout() {
        return checkBox2.isSelected();
    }

    public int getLogoutMS() {
        return Integer.parseInt(textField2.getText())*60000;
    }



    /*public boolean getAntiban() {
        return checkBox2.isSelected();
    }*/

    public GraphicalInterface() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {


        if (!listModel.isEmpty()) {

            for (int i = 0; i < listModel.size(); i++) {
                dropList.put(i, Integer.parseInt(listModel.get(i).toString()));

            }

        }

        if (checkBox1.isSelected()) powerfish = true;

        chosenBank = alLBanks[comboBox1.getSelectedIndex()];
        for (int i = 0; i < alLBanks.length; i++) {
            if (alLBanks[i].getName().equals(comboBox1.getItemAt(comboBox1.getSelectedIndex()).toString())) {
                chosenBank = alLBanks[i];
                break;
            }
        }

        for (int i = 0; i < allPools.length; i++) {
            if (allPools[i].getName().equals(comboBox2.getItemAt(comboBox2.getSelectedIndex()).toString())) {
                chosenPool = allPools[i];
                break;
            }
        }


        switch (chosenPool) {
            case TUNA:
                chosenTool = FishTools.HARPOON;
                break;
            case MACKEREL:
                chosenTool = FishTools.BIG_FISHING_NET;
                break;
            case LOBSTER:
                chosenTool = FishTools.LOBSTER_POT;
                break;
            case SHRIMPS:
                chosenTool = FishTools.SMALL_FISHING_NET;
                break;
            case HERRING:
                chosenTool = FishTools.FISHING_ROD;
                break;
            case SALMON:
                chosenTool = FishTools.FLY_FISHING_ROD;
                break;
            case SHARK:
                chosenTool = FishTools.HARPOON;
                break;
        }


        this.dispose();
        MetaFisher.guiDone = true;
    }

    private void powerfishActionPerformed(ActionEvent e) {
        if (checkBox1.isSelected()) {
            list1.setEnabled(false);
            textField1.setEnabled(false);
            button2.setEnabled(false);
        } else {
            list1.setEnabled(true);
            textField1.setEnabled(true);
            button2.setEnabled(true);
        }
    }

    private void dropListButtonActionPerformed(ActionEvent e) {

        if (textField1.getText() != null) {

            listModel.addElement(Integer.parseInt(textField1.getText()));


        }


    }

    private void list1MouseClicked(MouseEvent e) {
        if (list1 != null) {
            if (SwingUtilities.isRightMouseButton(e)) {
                listModel.remove(list1.getSelectedIndex());
            }
        }

    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        String loc = comboBox1.getItemAt(comboBox1.getSelectedIndex()).toString();

        if (loc.equals(Banks.CATHERBY.getName())) {
            comboBox2.setModel(new DefaultComboBoxModel(new String[]{
                    FishPools.MACKEREL.getName(),
                    FishPools.TUNA.getName(),
                    FishPools.LOBSTER.getName(),
                    FishPools.SHARK.getName()
            }));
        } else if (loc.equals(Banks.AL_KHARID.getName()) || loc.equals(Banks.DRAYNOR.getName())) {
            comboBox2.setModel(new DefaultComboBoxModel(new String[]{
                    FishPools.SHRIMPS.getName(),
                    FishPools.HERRING.getName()
            }));
        } else if (loc.equals(Banks.EDGEVILLE.getName())) {
            comboBox2.setModel(new DefaultComboBoxModel(new String[]{
                    FishPools.SALMON.getName()
            }));
        } else if (loc.equals(Banks.FISHING_GUILD.getName())) {
            comboBox2.setModel(new DefaultComboBoxModel(new String[]{
                    FishPools.MACKEREL.getName(),
                    FishPools.LOBSTER.getName(),
                    FishPools.TUNA.getName(),
                    FishPools.SHARK.getName()
            }));
        }

    }

    private void checkBox2ActionPerformed(ActionEvent e) {
        if (checkBox2.isEnabled()) {
            textField2.setEnabled(true);
        } else {
            textField2.setEnabled(false);
        }
    }


    private void initComponents() {
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - fasd sdfgsdf
        button1 = new JButton();
        tabbedPane1 = new JTabbedPane();
        optionsPanel = new JPanel();
        checkBox1 = new JCheckBox();
        panel1 = new JPanel();
        comboBox1 = new JComboBox();
        panel2 = new JPanel();
        comboBox2 = new JComboBox();
        panel4 = new JPanel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        button2 = new JButton();
        textField1 = new JTextField();
        panel3 = new JPanel();
        panel5 = new JPanel();
        checkBox2 = new JCheckBox();
        textField2 = new JTextField();
        label1 = new JLabel();

        //======== this ========
        setTitle("Setup");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("Start");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
            }
        });

        //======== tabbedPane1 ========
        {

            //======== optionsPanel ========
            {
                optionsPanel.setBorder(new TitledBorder("Options"));
                optionsPanel.setToolTipText("Options");
                optionsPanel.setForeground(new Color(51, 51, 51));

                // JFormDesigner evaluation mark
                optionsPanel.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), optionsPanel.getBorder())); optionsPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


                //---- checkBox1 ----
                checkBox1.setText("powerfish");
                checkBox1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        powerfishActionPerformed(e);
                    }
                });

                //======== panel1 ========
                {
                    panel1.setBorder(new TitledBorder("Location"));

                    //---- comboBox1 ----
                    comboBox1.setModel(new DefaultComboBoxModel(new String[] {
                        "Catherby",
                        "Al-kharid",
                        "Draynor",
                        "Edgeville",
                        "Fishing Guild"
                    }));
                    comboBox1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            comboBox1ActionPerformed(e);
                            comboBox1ActionPerformed(e);
                        }
                    });

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(comboBox1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                }

                //======== panel2 ========
                {
                    panel2.setBorder(new TitledBorder("Fish"));
                    panel2.setToolTipText("Select fish for script to fish");

                    //---- comboBox2 ----
                    comboBox2.setModel(new DefaultComboBoxModel(new String[] {
                        "Bass/Mackerel/Cod/Junk",
                        "Swordfish/Tuna",
                        "Lobster",
                        "Shark"
                    }));

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(comboBox2, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addGap(25, 25, 25))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(9, Short.MAX_VALUE))
                    );
                }

                //======== panel4 ========
                {
                    panel4.setBorder(new TitledBorder("Droplist - ItemIDs"));

                    //======== scrollPane1 ========
                    {

                        //---- list1 ----
                        list1.setToolTipText("Select item and right-click to remove it");
                        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        list1.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                list1MouseClicked(e);
                            }
                        });
                        scrollPane1.setViewportView(list1);
                    }

                    //---- button2 ----
                    button2.setText("Add");
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dropListButtonActionPerformed(e);
                        }
                    });

                    GroupLayout panel4Layout = new GroupLayout(panel4);
                    panel4.setLayout(panel4Layout);
                    panel4Layout.setHorizontalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel4Layout.createParallelGroup()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addGroup(panel4Layout.createSequentialGroup()
                                        .addComponent(button2)
                                        .addGap(18, 18, 18)
                                        .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2))
                                .addGap(0, 0, Short.MAX_VALUE))
                    );
                }

                GroupLayout optionsPanelLayout = new GroupLayout(optionsPanel);
                optionsPanel.setLayout(optionsPanelLayout);
                optionsPanelLayout.setHorizontalGroup(
                    optionsPanelLayout.createParallelGroup()
                        .addGroup(optionsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(optionsPanelLayout.createParallelGroup()
                                .addGroup(optionsPanelLayout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(97, 97, 97))
                                .addGroup(optionsPanelLayout.createSequentialGroup()
                                    .addGroup(optionsPanelLayout.createParallelGroup()
                                        .addComponent(checkBox1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)))
                            .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
                optionsPanelLayout.setVerticalGroup(
                    optionsPanelLayout.createParallelGroup()
                        .addGroup(optionsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(optionsPanelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, optionsPanelLayout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(checkBox1))
                                .addGroup(optionsPanelLayout.createSequentialGroup()
                                    .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())))
                );
            }
            tabbedPane1.addTab("Basic", optionsPanel);

            //======== panel3 ========
            {

                //======== panel5 ========
                {
                    panel5.setBorder(new TitledBorder("Logout"));

                    //---- checkBox2 ----
                    checkBox2.setText("Logout in ");
                    checkBox2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            checkBox2ActionPerformed(e);
                        }
                    });

                    //---- textField2 ----
                    textField2.setEnabled(false);

                    //---- label1 ----
                    label1.setText("min");

                    GroupLayout panel5Layout = new GroupLayout(panel5);
                    panel5.setLayout(panel5Layout);
                    panel5Layout.setHorizontalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(checkBox2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label1)
                                .addContainerGap(35, Short.MAX_VALUE))
                    );
                    panel5Layout.setVerticalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(checkBox2)
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1))
                                .addContainerGap(7, Short.MAX_VALUE))
                    );
                }

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 256, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                            .addContainerGap(122, Short.MAX_VALUE)
                            .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Extras", panel3);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(tabbedPane1)
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    .addGap(156, 156, 156))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(tabbedPane1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - fasd sdfgsdf
    private JButton button1;
    private JTabbedPane tabbedPane1;
    private JPanel optionsPanel;
    private JCheckBox checkBox1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JPanel panel2;
    private JComboBox comboBox2;
    private JPanel panel4;
    private JScrollPane scrollPane1;
    private JList list1;
    private JButton button2;
    private JTextField textField1;
    private JPanel panel3;
    private JPanel panel5;
    private JCheckBox checkBox2;
    private JTextField textField2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
