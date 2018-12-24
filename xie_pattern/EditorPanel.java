package xie;

import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel {
    private JTextField content;
    private JButton okButton;
    private boolean ok;
    private JDialog dialog;
    private JComboBox fontStyle;		//定义下拉框
    private JComboBox fontSize;


    public EditorPanel()
    {
        setLayout(new BorderLayout());

        String[] stlye = {"PLAIN","BOLD","ITALIC","CENTER_BASELINE"};
        String[] size = {"10","15","20","25"};

        JPanel panel = new JPanel();
        fontStyle = new JComboBox(stlye);
        fontSize = new JComboBox(size);

        panel.setLayout(new GridLayout(3, 3));
        panel.add(new JLabel("内容:"));
        panel.add(content = new JTextField(""));
        panel.add(new JLabel("字体样式:"));
        panel.add(fontStyle);
        panel.add(new JLabel("字体大小:"));
        panel.add(fontSize);
        add(panel, BorderLayout.CENTER);

        // create Ok and Cancel buttons that terminate the dialog
        okButton = new JButton("Ok");
        okButton.addActionListener(event -> {
            ok = true;
            dialog.setVisible(false);
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> dialog.setVisible(false));

        // add buttons to southern border

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setEditor(String text) {
        content.setText(text);
    }

    public Editor getEditor()
    {
        return new Editor(content.getText(), fontStyle.getSelectedItem().toString(), Integer.parseInt(fontSize.getSelectedItem().toString()));
    }

    public boolean showDialog(Component parent, String title)
    {
        ok = false;

        // locate the owner frame

        Frame owner = null;
        if (parent instanceof Frame)
            owner = (Frame) parent;
        else
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

        // if first time, or if owner has changed, make new dialog

        if (dialog == null || dialog.getOwner() != owner)
        {
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }

        // set title and show dialog

        dialog.setTitle(title);
        dialog.setLocation(owner.getWidth(), owner.getHeight());
        dialog.setVisible(true);
        return ok;
    }


}
