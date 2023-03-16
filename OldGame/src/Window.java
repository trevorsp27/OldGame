import javax.swing.JFrame;

public class Window{
    
    public Window(int width, int height, Main main){
    
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(main);
        
    }
    
}