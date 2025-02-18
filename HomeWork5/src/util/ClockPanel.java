package util;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//小時鐘面板
public class ClockPanel extends JPanel {
 private JLabel timeLabel;
 private SimpleDateFormat timeFormat;

 public ClockPanel() {
     setLayout(new GridBagLayout()); // 使用 GridBagLayout 以讓時間居中
     timeLabel = new JLabel();
     timeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
     timeLabel.setForeground(new Color(255, 255, 255));
     timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

     updateTime(); // 初始化時間

     add(timeLabel);
 }

 public void updateTime() {
     // 更新時間
     String currentTime = timeFormat.format(new Date());
     timeLabel.setText(currentTime);
 }
}