package com.czeczotka.timer;

import static com.czeczotka.timer.Common.DEFAULT_INPUT_TEXT;
import static com.czeczotka.timer.Common.FINISHED;
import static com.czeczotka.timer.Common.FRAME_TITLE;
import static com.czeczotka.timer.Common.START;
import static com.czeczotka.timer.Common.STOP;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * Main frame of the application. 
 * 
 * @author Jakub Czeczotka
 */
public class TimerFrame extends JFrame {
	
    private static final long serialVersionUID = 1689987348707738229L;

    private final JTextField input;
    private final JButton button;
    private final Box mainBox;
    private final JPanel firstPanel;
    private final JPanel secondPanel;
    private final JProgressBar progressBar;
    private final StartTimerListener startListener;
    private final StopTimerListener stopListener;
    private WaitingThread thread;

    static {
        JFrame.setDefaultLookAndFeelDecorated (true);	
    }

    public TimerFrame () {
        super (FRAME_TITLE);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        startListener = new StartTimerListener ();
        stopListener = new StopTimerListener ();

        mainBox = Box.createVerticalBox ();
        firstPanel = new JPanel ();
        secondPanel = new JPanel ();
        button = new JButton ();
        input = new JTextField (7);
        input.setHorizontalAlignment (JTextField.CENTER);
        input.setText (DEFAULT_INPUT_TEXT);
        input.addActionListener (startListener);
        progressBar = new JProgressBar ();
        progressBar.setMinimum (0);
        progressBar.setStringPainted (true);
        progressBar.setString ("");

        ready ();
        firstPanel.add (input);
        firstPanel.add (button);
        secondPanel.add (progressBar);
        mainBox.add (firstPanel);
        mainBox.add (secondPanel);
        this.setContentPane (mainBox);
        this.pack ();
        this.setResizable (false);

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation ((screenResolution.width - this.getWidth ()) / 2, 
                (screenResolution.height - this.getHeight ()) / 2);
        this.setVisible (true);
    }

    public void setProgress (int progress) {
        this.progressBar.setValue (progress);
        int minutes = progress / 60;
        int seconds = progress % 60;
        StringBuilder s = new StringBuilder ();
        if (minutes > 0) {
            s.append (minutes).append (" min ");
        }
        s.append (seconds).append (" sec");
        this.progressBar.setString (s.toString ());
    }

    public void finished () {
        Toolkit.getDefaultToolkit().beep ();
        this.setAlwaysOnTop (true);
        this.setExtendedState (JFrame.NORMAL);
        this.toFront ();
        this.setVisible (false);
        JOptionPane.showMessageDialog (TimerFrame.this, FINISHED);
        System.exit (0);		
    }

    private void waiting () {
        input.setEnabled (false);
        input.setEditable (false);
        input.setSelectionStart (0);
        input.setSelectionEnd (0);
        button.setText (STOP);
        button.removeActionListener (startListener);
        button.addActionListener (stopListener);
    }

    private void ready () {
        input.setEnabled (true);
        input.setEditable (true);
        input.selectAll ();
        input.requestFocus ();
        button.setText (START);
        button.removeActionListener (stopListener);
        button.addActionListener (startListener);
    }

    private class StartTimerListener implements ActionListener {
        @Override public void actionPerformed (ActionEvent e) {
            int waitingTime;
            try {
                waitingTime = TimeParser.parse (input.getText ());
            } catch (IllegalArgumentException ex) {
                input.selectAll ();
                input.requestFocus ();
                return;
            }
            TimerFrame.this.progressBar.setMaximum (waitingTime);
            TimerFrame.this.setExtendedState (JFrame.ICONIFIED);

            thread = new WaitingThread (waitingTime, TimerFrame.this);
            thread.start ();
            TimerFrame.this.waiting ();
        }
    }

    private class StopTimerListener implements ActionListener {        
        @Override public void actionPerformed (ActionEvent e) {
            thread.interrupt ();
            TimerFrame.this.ready ();
        }
    }
}