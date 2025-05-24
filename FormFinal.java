/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.TableRowSorter;
import javax.xml.crypto.Data;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Lenovo
 */
public class FormFinal extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet res = null;
    ResultSet resStudents = null;
    ResultSet resMajors = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;
    Boolean exists = false;
    private TableRowSorter<DefaultTableModel> sorter;
    /**
     * Creates new form FormFinal
     */
    public FormFinal() {
        initComponents();
        conn = FinalProject.ConnectDB();
        comboBox();
        display();
        curDate();
        curTime();
        SpinnerNumberModel gpaModel = new SpinnerNumberModel(0.00, 0.00, 4.00, 0.01);
        spinnerGPA.setModel(gpaModel);
        DecimalFormat format = new DecimalFormat("0.00");
        format.setParseBigDecimal(true);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(0.00);
        formatter.setMaximum(4.00);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField tf = ((JSpinner.NumberEditor)spinnerGPA.getEditor()).getTextField();
        DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
        tf.setFormatterFactory(factory);
        spinnerGPA.setValue(0.00);
        DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
        sorter = new TableRowSorter<>(model);
        tableStudent.setRowSorter(sorter);
    }

    private void connect() {
    try {
        statement = conn.createStatement(resStudents.TYPE_SCROLL_SENSITIVE, resStudents.CONCUR_UPDATABLE);
        resStudents = statement.executeQuery("SELECT * FROM students");
        while (resStudents.next()) {
            String studentName = resStudents.getString("name");
        }
        resMajors = statement.executeQuery("SELECT * FROM majors");
        while (resMajors.next()) {
            String major = resMajors.getString("major");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        System.exit(0);
        }
    }
    
    private void display() {
        try {
            String sql = "SELECT id, name, gender, major, enroll_date, gpa FROM students";
            prepStatement = conn.prepareStatement(sql);
            resStudents = prepStatement.executeQuery();
            tableStudent.setModel(DbUtils.resultSetToTableModel(resStudents));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void comboBox() {
        try {
            connect();
            selectMajor.addItem("");
            String sql = "SELECT major FROM majors";
            prepStatement = conn.prepareStatement(sql);
            resMajors = prepStatement.executeQuery();

            while (resMajors.next()) {
                selectMajor.addItem(resMajors.getString("major"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
    
    public void curDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(currentDate.format(dateFormatter));
    }

    public void curTime() {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                time.setText(currentTime.format(timeFormatter));
            }
        }).start();
    }

    
    private void clear() {
       textID.setText(null);
       textName.setText(null);
       groupGender.clearSelection();
       selectMajor.setSelectedItem(null);
       spinnerGPA.setValue(0.00);
       textSearch.setText(null);
    }
    
    public void close(){
        WindowEvent closeWindow = new WindowEvent (this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupGender = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        selectMajor = new javax.swing.JComboBox<>();
        radioMale = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        radioFemale = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textID = new javax.swing.JTextField();
        spinnerGPA = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStudent = new javax.swing.JTable();
        btnNewMajor = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        radioOther = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name");

        textName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNameActionPerformed(evt);
            }
        });

        groupGender.add(radioMale);
        radioMale.setText("Male");

        jLabel2.setText("Gender");

        jLabel3.setText("Major");

        groupGender.add(radioFemale);
        radioFemale.setText("Female");

        jLabel4.setText("GPA");

        jLabel5.setText("ID");

        tableStudent = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableStudent.setModel(new javax.swing.table.DefaultTableModel(
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
        tableStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableStudent);

        btnNewMajor.setText("Add New Major");
        btnNewMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewMajorActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        groupGender.add(radioOther);
        radioOther.setText("Other");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Student Management System");

        date.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        date.setText("Date");

        time.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        time.setText("Time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textID)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(radioMale)
                                .addGap(18, 18, 18)
                                .addComponent(radioFemale)
                                .addGap(18, 18, 18)
                                .addComponent(radioOther))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(selectMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNewMajor))
                            .addComponent(textName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerGPA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(time)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(time))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Search)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioMale)
                            .addComponent(radioFemale)
                            .addComponent(jLabel2)
                            .addComponent(radioOther))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnNewMajor))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(spinnerGPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)
                            .addComponent(btnClear)
                            .addComponent(btnExit))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewMajorActionPerformed
        // TODO add your handling code here:
        close();
        AddMajor am = new AddMajor();
        am.setVisible(true);
    }//GEN-LAST:event_btnNewMajorActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            connect();
            int row = tableStudent.getSelectedRow();
            if (row != -1) {
                String id = tableStudent.getModel().getValueAt(row, 4).toString();
                String sql = "DELETE FROM students WHERE enroll_date = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
                    display();
                    clear();
                    JOptionPane.showMessageDialog(null, "Delete Success");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a student to delete.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void textNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            connect();
            String id = textID.getText().trim();
            String name = textName.getText().trim();
            String gender = null;
            if (radioMale.isSelected()) {
                gender = "Male";
            } else if (radioFemale.isSelected()) {
                gender = "Female";
            } else if (radioOther.isSelected()) {
                gender = "Other";
            }
            String major = (String) selectMajor.getSelectedItem();
            Double gpa = (Double) spinnerGPA.getValue();

            if (id.isEmpty() || name.isEmpty() || gender == null || major == null || major.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled.");
                return;
            }

            if (id.length() != 12 || !id.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "ID must be exactly 12 digits.");
                return;
            }

            if (!id.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "ID must be numeric.");
                return;
            }

            if (gpa < 0.00 || gpa > 4.00) {
                JOptionPane.showMessageDialog(null, "GPA must be between 0.00 and 4.00.");
                return;
            }

            String checkIdSql = "SELECT COUNT(*) FROM students WHERE id = ?";
            PreparedStatement checkIdStmt = conn.prepareStatement(checkIdSql);
            checkIdStmt.setString(1, id);
            ResultSet rsId = checkIdStmt.executeQuery();
            if (rsId.next() && rsId.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "ID already exists in the database.");
                rsId.close();
                checkIdStmt.close();
                return;
            }
            rsId.close();
            checkIdStmt.close();

            String checkNameSql = "SELECT COUNT(*) FROM students WHERE name = ?";
            PreparedStatement checkNameStmt = conn.prepareStatement(checkNameSql);
            checkNameStmt.setString(1, name);
            ResultSet rsName = checkNameStmt.executeQuery();
            if (rsName.next() && rsName.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Name already exists in the database.");
                rsName.close();
                checkNameStmt.close();
                return;
            }
            rsName.close();
            checkNameStmt.close();

            String sql = "INSERT INTO students (id, name, gender, major, gpa) VALUES (?, ?, ?, ?, ?)";
            prepStatement = conn.prepareStatement(sql);
            prepStatement.setString(1, id);
            prepStatement.setString(2, name);
            prepStatement.setString(3, gender);
            prepStatement.setString(4, major);
            prepStatement.setDouble(5, gpa);

            prepStatement.executeUpdate();
            display();
            clear();
            JOptionPane.showMessageDialog(null, "Save Success");

            prepStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        try {
            connect();
            int row = tableStudent.getSelectedRow();
            if (row != -1) {
                String enrollDate = tableStudent.getModel().getValueAt(row, 4).toString();
                String currentId = tableStudent.getModel().getValueAt(row, 0).toString();
                String currentName = tableStudent.getModel().getValueAt(row, 1).toString();
                String id = textID.getText().trim();
                String name = textName.getText().trim();
                String gender = null;
                if (radioMale.isSelected()) {
                    gender = "Male";
                } else if (radioFemale.isSelected()) {
                    gender = "Female";
                } else if (radioOther.isSelected()) {
                    gender = "Other";
                }
                String major = (String) selectMajor.getSelectedItem();
                Double gpa = (Double) spinnerGPA.getValue();

                if (id.isEmpty() || name.isEmpty() || gender == null || major == null || major.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled.");
                    return;
                }

                if (id.length() != 12 || !id.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "ID must be exactly 12 digits.");
                    return;
                }

                if (!id.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "ID must be numeric.");
                    return;
                }

                if (gpa < 0.00 || gpa > 4.00) {
                    JOptionPane.showMessageDialog(null, "GPA must be between 0.00 and 4.00.");
                    return;
                }

                if (!id.equals(currentId)) {
                    String checkIdSql = "SELECT COUNT(*) FROM students WHERE id = ?";
                    PreparedStatement checkIdStmt = conn.prepareStatement(checkIdSql);
                    checkIdStmt.setString(1, id);
                    ResultSet rsId = checkIdStmt.executeQuery();
                    if (rsId.next() && rsId.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "ID already exists in the database.");
                        rsId.close();
                        checkIdStmt.close();
                        return;
                    }
                    rsId.close();
                    checkIdStmt.close();
                }

                if (!name.equals(currentName)) {
                    String checkNameSql = "SELECT COUNT(*) FROM students WHERE name = ?";
                    PreparedStatement checkNameStmt = conn.prepareStatement(checkNameSql);
                    checkNameStmt.setString(1, name);
                    ResultSet rsName = checkNameStmt.executeQuery();
                    if (rsName.next() && rsName.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "Name already exists in the database.");
                        rsName.close();
                        checkNameStmt.close();
                        return;
                    }
                    rsName.close();
                    checkNameStmt.close();
                }

                String sql = "UPDATE students SET id = ?, name = ?, gender = ?, major = ?, gpa = ? WHERE enroll_date = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, gender);
                pstmt.setString(4, major);
                pstmt.setDouble(5, gpa);
                pstmt.setString(6, enrollDate);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    display();
                    clear();
                    JOptionPane.showMessageDialog(null, "Edit Success");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching student found to update.");
                }
                pstmt.close();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a student to edit.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void tableStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStudentMouseClicked
        // TODO add your handling code here:
        try {
            connect();
            int row = tableStudent.getSelectedRow();
            String clickedRow = tableStudent.getModel().getValueAt(row, 4).toString();
            Statement stm = conn.createStatement();
            String sql = "SELECT id, name, gender, major, gpa FROM students WHERE enroll_date = '" + clickedRow + "'";
            ResultSet result = stm.executeQuery(sql);
            if (result.next()) {
                String id = result.getString("id");
                textID.setText(id);
                String name = result.getString("name");
                textName.setText(name);
                String gender = result.getString("gender");
                if (gender.equals("Male")) {
                    radioMale.setSelected(true);
                } else if (gender.equals("Female")) {
                    radioFemale.setSelected(true);
                } else if (gender.equals("Other")) {
                    radioOther.setSelected(true);
                }
                String major = result.getString("major");
                selectMajor.setSelectedItem(major);
                Double gpa = result.getDouble("gpa");
                spinnerGPA.setValue(gpa);
                }
        } catch (Exception e) {}
    }//GEN-LAST:event_tableStudentMouseClicked

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
        String searchText = textSearch.getText().trim();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }//GEN-LAST:event_SearchActionPerformed

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
            java.util.logging.Logger.getLogger(FormFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNewMajor;
    private javax.swing.JLabel date;
    private javax.swing.ButtonGroup groupGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JRadioButton radioOther;
    private javax.swing.JComboBox<String> selectMajor;
    private javax.swing.JSpinner spinnerGPA;
    private javax.swing.JTable tableStudent;
    private javax.swing.JTextField textID;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textSearch;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
