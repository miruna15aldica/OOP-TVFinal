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

    }

    /**
     *
     * @param inputFilename
     * @param outputFilename
     * @throws IOException
     */
    public static void solve(final String inputFilename, final String outputFilename)
            throws IOException {
        InputData input = InputDataFactory.fromFile(inputFilename);

        Runner runner = new Runner(input.getUsers(), input.getMovies());

        input.getActions().add(new RecommendationAction());

        var output = runner.execute(input.getActions());

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
