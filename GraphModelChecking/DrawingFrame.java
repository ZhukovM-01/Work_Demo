import java.awt.EventQueue;
import javax.swing.JFrame;

public class DrawingFrame extends JFrame{
    CollectionNode graph;
    DrawingFrame(CollectionNode graph_param){
        graph = graph_param;
        initUI();
    }

    private void initUI(){

        add(new DrawingBoard(graph));
        setResizable(false);
        pack();
        setTitle("Graphics");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
