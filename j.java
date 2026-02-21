import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class FancyChatBot extends JFrame {

    private JPanel chatPanel;
    private JTextField chatField;
    private JScrollPane scrollPane;

    public FancyChatBot() {
        setTitle("Fancy Chat Bot");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // চ্যাট এলাকা প্যানেল
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(new Color(30, 30, 30));

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // ইনপুট প্যানেল
        JPanel inputPanel = new JPanel(new BorderLayout());
        chatField = new JTextField();
        chatField.setFont(new Font("Arial", Font.PLAIN, 16));
        chatField.setBackground(new Color(50, 50, 50));
        chatField.setForeground(Color.WHITE);
        chatField.setCaretColor(Color.WHITE);

        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 16));
        sendButton.setBackground(new Color(0, 150, 255));
        sendButton.setForeground(Color.WHITE);

        inputPanel.add(chatField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Send action
        ActionListener sendAction = e -> sendMessage();
        sendButton.addActionListener(sendAction);
        chatField.addActionListener(sendAction); // Enter key

        // স্বাগতম মেসেজ
        addBotMessage("হ্যালো! আমি তোমার চ্যাটবট। কেমন আছো?");

        setVisible(true);
    }

    private void sendMessage() {
        String text = chatField.getText().trim();
        if (!text.isEmpty()) {
            addUserMessage(text);
            chatField.setText("");
            botResponse(text.toLowerCase());
        }
    }

    private void addUserMessage(String message) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.setBackground(new Color(30, 30, 30));

        JLabel label = new JLabel("<html><p style='width:200px'>" + message + "</p></html>");
        label.setOpaque(true);
        label.setBackground(new Color(0, 120, 215));
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);

        messagePanel.add(Box.createHorizontalGlue());
        messagePanel.add(label);
        messagePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        chatPanel.add(messagePanel);
        chatPanel.add(Box.createVerticalStrut(10));
        chatPanel.revalidate();
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    private void addBotMessage(String message) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.setBackground(new Color(30, 30, 30));

        JLabel label = new JLabel("<html><p style='width:200px'>" + message + "</p></html>");
        label.setOpaque(true);
        label.setBackground(new Color(80, 80, 80));
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        messagePanel.add(label);
        messagePanel.add(Box.createHorizontalGlue());

        chatPanel.add(messagePanel);
        chatPanel.add(Box.createVerticalStrut(10));
        chatPanel.revalidate();
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    private void botResponse(String input) {
        String response;
        if (input.contains("হ্যালো") || input.contains("hi") || input.contains("hello")) {
            response = "হ্যালো! কেমন আছো?";
        } else if (input.contains("কেমন আছো")) {
            response = "আমি ভালো আছি, ধন্যবাদ! তুমি কেমন আছো?";
        } else if (input.contains("ধন্যবাদ") || input.contains("thank you")) {
            response = "স্বাগতম! :)";
        } else if (input.contains("বিদায়") || input.contains("bye")) {
            response = "বিদায়! আবার কথা হবে!";
        } else {
            response = "দুঃখিত, আমি সেটা বুঝতে পারিনি।";
        }
        addBotMessage(response);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FancyChatBot::new);
    }
}