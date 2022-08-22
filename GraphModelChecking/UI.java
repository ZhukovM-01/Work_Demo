import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class UI {
    Integer capacity_input;
    Integer node_number;
    Scanner scanner;
    long startTime;


    UI() throws InterruptedException {

        System.out.println("ModelChecking in DTMC!");
        Thread.sleep(1000);
        scanner = new Scanner(System.in);

    }
    void scan_init() {



        System.out.println("enter capacity of truck (recommended values : 1000-4000");
        capacity_input = Integer.parseInt(scanner.next());

        System.out.println("enter nearest node number (recommended values : 1-20");
        node_number = Integer.parseInt(scanner.next());

        startTime = System.currentTimeMillis();
    }


    Integer scan() {
        System.out.println("to run model again, enter 1");
        System.out.println("to model check, enter 2");
        System.out.println("to exit, enter 0");
        String temp_string = scanner.next();

        return Integer.parseInt(temp_string);
    }

    void send_output(CollectionNode graph_param) throws InterruptedException {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("The Model Finished its execution...");
        Thread.sleep(700);
        System.out.printf("total time taken : %d milliseconds",totalTime);
        System.out.println("\n");
        System.out.println("___________________");
        System.out.printf("total states : %d states", graph_param.STATE_COUNTER);
        System.out.println("\n");
        System.out.printf("total length : %f units", graph_param.path_length);
        System.out.println("\n");
        System.out.printf("total trips to factory: %d", graph_param.factory_counter);
        System.out.println("\n");
        System.out.printf("remainder: %f", graph_param.remainder);
        System.out.println("\n");
        System.out.println("___________________");


    }

    void send_model_check_output(ModelCheckingOutput temp_output) throws InterruptedException {
        System.out.println("Your inputs:");
        System.out.println("___________________");
        System.out.println("\n");
        System.out.printf("at least this many trips to factory: %d",Math.round(temp_output.inputs.get(0)));
        System.out.println("\n");
        System.out.printf("at most this remainder: %d", Math.round(temp_output.inputs.get(1)));
        System.out.println("\n");
        System.out.printf("at most this path length: %d", Math.round(temp_output.inputs.get(2)));
        System.out.println("\n");
        System.out.println("___________________");
        System.out.println("\n");

        System.out.println("successful models:");
        Thread.sleep(500);
        System.out.println("Success for:");

//        for (List<Integer> i : temp_output.success_params){
//            System.out.println();
//            System.out.printf("Capacity of: %d and node_num_array of: %d", i.get(0),i.get(1));;
//        }
        System.out.println(temp_output.success_params);
        System.out.println();
        System.out.println();

        System.out.printf("models satisfying %d trips to factory:", Math.round(temp_output.inputs.get(0)));
        Thread.sleep(500);
        System.out.println("\n");
        System.out.println("Success for:");
//        for (List<Integer> i : temp_output.factory_success_params){
//            System.out.println();
//            System.out.printf("Capacity of: %d and node_num_array of: %d", i.get(0),i.get(1));;
//        }
        System.out.println(temp_output.factory_success_params);

        System.out.println("\n");
        System.out.printf("models satisfying remainder less than %d:", Math.round(temp_output.inputs.get(1)));
        Thread.sleep(500);
        System.out.println("\n");
        System.out.println("Success for:");
//        for (List<Integer> i : temp_output.remainder_success_params){
//            System.out.println();
//            System.out.printf("Capacity of: %d and node_num_array of: %d", i.get(0),i.get(1));;
//        }
        System.out.println(temp_output.remainder_success_params);
        System.out.println("\n");
        System.out.printf("models satisfying path length less than %d:", Math.round(temp_output.inputs.get(2)));
        Thread.sleep(500);
        System.out.println();
        System.out.println();
        System.out.println("Success for:");
//        for (List<Integer> i : temp_output.path_length_success_params){
//            System.out.println();
//            System.out.printf("Capacity of: %d and node_num_array of: %d", i.get(0),i.get(1));;
//        }
        System.out.println(temp_output.path_length_success_params);
        System.out.println("\n");



    }



    List<Float> checking_input(){

        List<Float> temp_inputs = new ArrayList<>();

        System.out.println("enter at least how many trips you would like to check for:");
        temp_inputs.add(Float.parseFloat(scanner.next()));
        System.out.println("enter at least how little remainder you would like to check for:");
        temp_inputs.add(Float.parseFloat(scanner.next()));
        System.out.println("enter the maximum path length you would like to check for:");
        temp_inputs.add(Float.parseFloat(scanner.next()));

        return temp_inputs;

    }



}
