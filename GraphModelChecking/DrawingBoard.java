import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DrawingBoard extends JPanel
        implements ActionListener {

    private Timer timer;
    private CollectionNode loc_collection;
    List<DrawingPoint> points_list;
    DrawingPoint Factory;
    List<List<Integer>>edges_list = new ArrayList<>();
    Integer list_slicer = 0;

    public DrawingBoard(CollectionNode collection) {
        initBoard();
        loc_collection = collection;


        processPoints();
        process_edges();
    }

    private void initBoard(){
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(1200,800));
        timer = new Timer(25,this);
        timer.start();
    }

    private void processPoints(){

        points_list = new ArrayList<>();


        for (DataNode iterNode : loc_collection.local_data){
            DrawingPoint temp_point = new DrawingPoint(iterNode.index,
                            iterNode.x_coord, iterNode.y_coord);

            points_list.add(temp_point);

        }

        Float max_x = 0f;
        Float min_x = 100000f;
        Float max_y = 0f;
        Float min_y = 100000f;

        for (DrawingPoint iterPoint : points_list){
            if (iterPoint.x_coord > max_x){
                max_x = iterPoint.x_coord;
            }
            if (iterPoint.x_coord < min_x){
                min_x = iterPoint.x_coord;
            }
            if (iterPoint.y_coord > max_y){
                max_y = iterPoint.y_coord;
            }
            if (iterPoint.y_coord < min_y){
                min_y = iterPoint.y_coord;
            }
        }

        for (DrawingPoint iterPoint : points_list){
            iterPoint.x_coord = ((iterPoint.x_coord - min_x)/(max_x-min_x))*1200;
            iterPoint.y_coord = ((iterPoint.y_coord - min_y)/(max_y-min_y))*800;
        }
        points_list.add(new DrawingPoint(-1, 900f,650f));

    }

    public void process_edges(){
        for (int i = 0; i < loc_collection.state.passed_nodes.size()-1; i++) {
            List<Integer> edge = new ArrayList<>();
            edge.add(loc_collection.state.passed_nodes.get(i).index);
            edge.add(loc_collection.state.passed_nodes.get(i + 1).index);
            edges_list.add(edge);
        }
        System.out.println(edges_list.size());
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.BLUE);
        for(DrawingPoint iterPoint : points_list){
            g.drawOval(
                    Math.round(iterPoint.x_coord),
                    Math.round(iterPoint.y_coord),
                    3,
                    3);
        }

        g.setColor(Color.RED);
        g.drawOval(
                900,
                650,
                6,
                6
        );


        Integer x_coord_1 = 0;
        Integer y_coord_1 = 0;
        Integer x_coord_2 = 0;
        Integer y_coord_2 = 0;

        int counter = 0;
        int factory_flag = 0;
        for (List<Integer> i : edges_list){
            if (i.get(1) == -1)
                factory_flag = 1;
            else
                factory_flag = 0;
            for (DrawingPoint iterPoint : points_list){
                if (iterPoint.index.equals(i.get(0))){
                    x_coord_1 = Math.round(iterPoint.x_coord);
                    y_coord_1 = Math.round(iterPoint.y_coord);
                }

            }
            for (DrawingPoint iterPoint : points_list){
                if (iterPoint.index.equals(i.get(1))){
                    x_coord_2 = Math.round(iterPoint.x_coord);
                    y_coord_2 = Math.round(iterPoint.y_coord);
                }
            }

            if (factory_flag == 0) {
                g.setColor(Color.red);
                g.drawLine(x_coord_1, y_coord_1, x_coord_2, y_coord_2);
            }
            else {
                g.setColor(Color.green);
                g.drawLine(x_coord_1, y_coord_1, x_coord_2, y_coord_2);
            }
            if (counter == list_slicer){
                break;
            }
            counter++;
        }


    }



    @Override
    public void actionPerformed(ActionEvent e){
        list_slicer++;
        repaint();

    }
}
