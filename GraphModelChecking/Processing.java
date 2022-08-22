import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Processing {
    static List<DataNode> Data = new ArrayList<>();
    static FactoryNode FactoryData;
    static Float factory_cap;

    public static void process() throws FileNotFoundException {

        File file = new File("Points.txt");

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("]");

        int counter = 1;

        Float factory_capacity = 0f;

        while (scanner.hasNext()) {
            String container_string = scanner.next();
            container_string = container_string.replace("[[,","");
            container_string = container_string.replaceFirst(",","");

            //System.out.println(container_string);

            int temp_index = counter;

            String temp_name = container_string.split(",")[0];
            temp_name = temp_name.replace(" [,","");
            temp_name = temp_name.replace(" [", "");
            String temp_volume = container_string.split(",")[1];
            String temp_volume_whole = temp_volume.split("\\.")[0];
            String temp_volume_decimal = temp_volume.split("\\.")[1];
            Float float_volume = Float.parseFloat(temp_volume_whole) +
                                    0.01f*(Float.parseFloat(temp_volume_decimal));

            factory_capacity = factory_capacity + float_volume;

            Float float_x;
            Float float_y;
            if (!Objects.equals(container_string.split(",")[2], " ")) {
                String temp_x = container_string.split(",")[2];
                String temp_x_whole = temp_x.split("\\.")[0];
                String temp_x_decimal = temp_x.split("\\.")[1];
                temp_x_decimal = temp_x_decimal.replaceAll("9999999", "");


//                float_x = Float.parseFloat(temp_x_whole) +
//                        (0.0000001f * (Float.parseFloat(temp_x_decimal)));
                float_x = Float.parseFloat(temp_x);

                String temp_y = container_string.split(",")[3];
                String temp_y_whole = temp_y.split("\\.")[0];
                String temp_y_decimal = temp_y.split("\\.")[1];
                temp_y_decimal = temp_y_decimal.replaceAll("9999999", "");


//                float_y = Float.parseFloat(temp_y_whole) +
//                        (0.0000001f * (Float.parseFloat(temp_y_decimal)));
                float_y = Float.parseFloat(temp_y);
            }
            else {
                float_x = 0f;
                float_y = 0f;
            }
            DataNode iter = new DataNode(
                        temp_index, temp_name, float_volume, float_x,float_y);
            Data.add(iter);
            counter++;

        }
        factory_cap = factory_capacity;
    }
    public static void process_factory(){
        String factory_coord = "64.49787, 40.68435";
        String x_coord = factory_coord.split(",")[0];
        String y_coord = factory_coord.split(",")[1];

        Float float_x = Float.parseFloat(x_coord);
        Float float_y = Float.parseFloat(y_coord);

//        System.out.println(float_x);
//        System.out.println(float_y);
        List<Float> temp_transactions = new ArrayList<>();

        FactoryData = new FactoryNode(factory_cap,"Архангельск",
                                                    temp_transactions,float_x,float_y);
    }
    static List<DataNode> send_data(){
        return Data;
    }

    static FactoryNode send_factory_data(){
        return FactoryData;
    }
}
