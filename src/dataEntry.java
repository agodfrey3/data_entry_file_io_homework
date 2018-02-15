import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class dataEntry extends JFrame
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JButton btnNewButton_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        dataEntry frame = new dataEntry();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public dataEntry()
    {
        setTitle("Data Entry Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 743, 486);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(10, 25, 74, 14);
        contentPane.add(lblFirstName);

        textField = new JTextField();
        textField.setBounds(94, 22, 185, 20);
        contentPane.add(textField);
        textField.setColumns(10);


        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(10, 70, 74, 14);
        contentPane.add(lblLastName);

        textField_1 = new JTextField();
        textField_1.setBounds(94, 67, 185, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(10, 115, 74, 14);
        contentPane.add(lblAddress);

        textField_2 = new JTextField();
        textField_2.setBounds(94, 112, 185, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(10, 160, 74, 14);
        contentPane.add(lblCity);

        textField_3 = new JTextField();
        textField_3.setBounds(94, 157, 185, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblState = new JLabel("State");
        lblState.setBounds(10, 205, 74, 14);
        contentPane.add(lblState);

        textField_4 = new JTextField();
        textField_4.setBounds(94, 202, 185, 20);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblZipCode = new JLabel("Zip Code");
        lblZipCode.setBounds(10, 250, 74, 14);
        contentPane.add(lblZipCode);

        textField_5 = new JTextField();
        textField_5.setBounds(94, 247, 185, 20);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 295, 74, 14);
        contentPane.add(lblEmail);

        textField_6 = new JTextField();
        textField_6.setBounds(94, 292, 185, 20);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        // SUBMIT Button
        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                fileIO fl = new fileIO();
                fl.wrTransactionData(textField.getText(),
                        textField_1.getText(),
                        textField_2.getText(),
                        textField_3.getText(),
                        textField_4.getText(),
                        textField_5.getText(),
                        textField_6.getText());

                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
                textField_5.setText("");
                textField_6.setText("");
            }
        });
        btnNewButton.setBounds(324, 401, 89, 23);
        contentPane.add(btnNewButton);

        // EXIT BUTTON
        btnNewButton_1 = new JButton("EXIT");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        btnNewButton_1.setBounds(628, 401, 89, 23);
        contentPane.add(btnNewButton_1);

        // Drop down box
        JComboBox<Object> comboBox = new JComboBox<Object>();
        comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Baseball", "Football", "Hockey", "Basketball"}));
        comboBox.setBounds(131, 351, 126, 20);
        contentPane.add(comboBox);

        JLabel lblNewLabel = new JLabel("Select Sport");
        lblNewLabel.setBounds(10, 354, 74, 14);
        contentPane.add(lblNewLabel);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Computer Science");
        rdbtnNewRadioButton.setBounds(448, 21, 142, 23);
        contentPane.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Information Science");
        rdbtnNewRadioButton_1.setBounds(448, 66, 162, 23);
        contentPane.add(rdbtnNewRadioButton_1);


        // Transaction Log Button
        JButton btnNewButton_2 = new JButton("Transaction Log");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                final JTextArea edit = new JTextArea(35, 150);
                edit.setEditable(false);
                edit.setBackground(new Color(176, 224, 230));
                edit.setFont(new Font("Courier New", Font.BOLD, 14));
                edit.setForeground(Color.black);

                JFrame frame = new JFrame("Transaction Log File");
                try
                {
                    File f = new File("transactionLog.txt");
                    if (f.exists())
                    {
                        FileReader reader = new FileReader("transactionLog.txt");
                        BufferedReader br = new BufferedReader(reader);
                        edit.read( br, null );
                        br.close();

                        edit.requestFocus();
                        frame.getContentPane().add( new JScrollPane(edit), BorderLayout.NORTH );

                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.pack();

                        frame.setLocationRelativeTo( null );
                        frame.setVisible(true);
                    }
                }
                catch(Exception e2)
                {

                }
            }
        });
        btnNewButton_2.setBounds(10, 401, 126, 23);
        contentPane.add(btnNewButton_2);

        // check boxes
        JCheckBox chckbxNewCheckBox = new JCheckBox("Undergraduate");
        chckbxNewCheckBox.setBounds(448, 148, 131, 23);
        contentPane.add(chckbxNewCheckBox);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Graduate");
        chckbxNewCheckBox_1.setBounds(448, 188, 97, 23);
        contentPane.add(chckbxNewCheckBox_1);

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("PhD");
        chckbxNewCheckBox_2.setBounds(448, 233, 97, 23);
        contentPane.add(chckbxNewCheckBox_2);

        this.setLocationRelativeTo(null);
    }
}