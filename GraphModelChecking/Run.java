import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        try {
            exec();                               //   Parent function for all classes
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void exec() throws FileNotFoundException, InterruptedException {
        Processing.process();                    // preprocessing data
        Processing.process_factory();

        CollectionNode Graph =                  // Main Class
                new CollectionNode(Processing.send_data(), Processing.send_factory_data());


        UI UserInterface = new UI();


        /// event loop


        Integer flag = 1;
        do {
            if (flag == 1) {         // to run model again
                UserInterface.scan_init();

                Controller.set_capacity(Graph, Float.valueOf(UserInterface.capacity_input));
                Controller.set_node_number_control(Graph, UserInterface.node_number);

                Graph.build();        // main public method

                EventQueue.invokeLater(() -> {                      // drawing event queue
                    DrawingFrame ex = new DrawingFrame(Graph);
                    ex.setVisible(true);
                });


                UserInterface.send_output(Graph);


            }
            else if (flag ==2){                // to run model checking

                ModelChecking.init();

                List<Float> inputs = UserInterface.checking_input();


                List<List<Integer>> success_params = new ArrayList<>();
                List<List<Integer>> factory_success_params  = new ArrayList<>();
                List<List<Integer>> remainder_success_params = new ArrayList<>();
                List<List<Integer>> path_length_success_params = new ArrayList<>();

                Integer counter = 0;
                System.out.println("Loading bar:");
                for (Integer i : ModelChecking.capacity_array){
                    for (Integer j : ModelChecking.node_num_array){
                        Controller.set_capacity(Graph, Float.valueOf(i));
                        Controller.set_node_number_control(Graph, j);

                        Graph.build();

                        if (ModelChecking.check_satisfaction(Graph)){
                            List<Integer>temp_success = new ArrayList<>();
                            temp_success.add(i);
                            temp_success.add(j);
                            success_params.add(temp_success);
                            if (ModelChecking.check_factory(Graph, (int) Math.abs(inputs.get(0)))){
                                List<Integer>temp_factory= new ArrayList<>();
                                temp_factory.add(i);
                                temp_factory.add(j);
                                factory_success_params.add(temp_factory);

                            }
                            if (ModelChecking.check_remainder(Graph,inputs.get(1))){
                                List<Integer>temp_remainder= new ArrayList<>();
                                temp_remainder.add(i);
                                temp_remainder.add(j);
                                remainder_success_params.add(temp_remainder);

                            }
                            if (ModelChecking.check_path_length(Graph,inputs.get(2))){
                                List<Integer>temp_path= new ArrayList<>();
                                temp_path.add(i);
                                temp_path.add(j);
                                path_length_success_params.add(temp_path);

                            }

                        }
                        counter++;

                        /// loading bar for model checking




                        if (counter < 10){
                            System.out.print("|=               |\r");}
                        else if (counter < 20){
                            System.out.print("|==              |\r");}
                        else if (counter < 30){
                            System.out.print("|===             |\r");}
                        else if (counter < 40){
                            System.out.print("|====            |\r");}
                        else if (counter < 50){
                            System.out.print("|=====           |\r");}
                        else if (counter < 60){
                            System.out.print("|======          |\r");}
                        else if (counter < 70){
                            System.out.print("|=======         |\r");}
                        else if (counter < 80){
                            System.out.print("|========        |\r");}
                        else if (counter < 90){
                            System.out.print("|=========       |\r");}
                        else if (counter < 100){
                            System.out.print("|==========      |\r");}
                        else if (counter < 110){
                            System.out.print("|===========     |\r");}
                        else if (counter < 120){
                            System.out.print("|============    |\r");}
                        else if (counter < 130){
                            System.out.print("|=============   |\r");}
                        else if (counter < 230){
                            System.out.print("|==============  |\r");}
                        else if (counter < 330){
                            System.out.print("|=============== |\r");}
                        else if (counter < 390){
                            System.out.print("|================|\r");}
                        }
                        if (counter == 400)
                        System.out.println("done!\n");
                }

                ModelCheckingOutput OutputModelCheck =
                        new ModelCheckingOutput(success_params,factory_success_params,
                                                remainder_success_params,path_length_success_params,
                                                inputs);

                UserInterface.send_model_check_output(OutputModelCheck);
            }
            flag = UserInterface.scan();
        }

        while(flag != 0);





    }
}
