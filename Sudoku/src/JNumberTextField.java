import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JNumberTextField extends JTextField {
	
	public JNumberTextField(String s) {
		
	}
	
	//Only allows digits 1-9, backspace, and delete keys
	public void processKeyEvent(KeyEvent ev) {
		char c = ev.getKeyChar();
        if ((Character.isDigit(c) && c != KeyEvent.VK_0) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE  ) {
            super.processKeyEvent(ev);
        }
        ev.consume();
        return;
    }

}
