import data.actions.RecommendationAction;
import data.entities.Notification;
import data.entities.Output;
import factories.InputDataFactory;
import io.input.InputData;
import io.output.serializers.OutputSerializer;
import io.output.serializers.Utils;
import session.Runner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        Runner.result.clear();
        String inputFilename = args[0];
        solve(inputFilename, Test.OUT_FILE);

        //String[] x =inputFilename.split("\\\\");
        //solve(inputFilename, "C:\\Users\\Admin\\Desktop\\Dubla-tema2\checker\\resources\\"
        //+ x[x.length-1]);
//        String inputFilename = //args[0];
//                "C:\\Users\\Admin\\Desktop\\Dubla-tema2\\checker\\resources\\in\\basic_8.json";
//        solve(inputFilename,
//                "C:\\Users\\Admin\\Desktop\\Dubla-tema2\\checker\\resources\\out.json");
    }

    /**
     *
     * @param inputFilename
     * @param outputFilename
     * @throws IOException
     */
    public static void solve(final String inputFilename, final String outputFilename)
            throws IOException {
        System.out.println(inputFilename);
        InputData input = InputDataFactory.fromFile(inputFilename);

        Runner runner = new Runner(input.getUsers(), input.getMovies());

        input.getActions().add(new RecommendationAction());

        var output = runner.execute(input.getActions());

        //input.getActions().forEach(System.out::println);

        var json = Utils.serializeList(output, Output.class, new OutputSerializer());
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));
        writer.write(json);
        writer.close();

        writer = new BufferedWriter(new FileWriter(outputFilename + ".txt"));
        writer.write(json);
        writer.close();

        Runner.result = new ArrayList<>();

    }
}
