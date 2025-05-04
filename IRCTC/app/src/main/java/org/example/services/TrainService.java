package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {

    private Train train ;

    private List<Train> TrainList ;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRAIN_PATH = "app/src/main/java/org/example/localDB/trains.json";

    public TrainService(Train train1) throws IOException {
        this.train = train1;
        loadTrains();
    }

    public TrainService() throws IOException {
        loadTrains();
    }

    public List<Train> loadTrains() throws IOException{
        File trains = new File(TRAIN_PATH);
        return objectMapper.readValue(trains, new TypeReference<List<Train>>(){});
    }

    public List<Train>searchTrains(String source , String destination){
        return TrainList.stream().filter(train->validTrain(train , source , destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

       public void addTrain(Train newtrain) throws IOException {
        TrainList.add(newtrain);
           Optional<Train> existingTrain = TrainList.stream()
                   .filter(train -> train.getTrainId().equalsIgnoreCase(newtrain.getTrainId()))
                   .findFirst();

           if (existingTrain.isPresent()) {
               // If a train with the same trainId exists, update it instead of adding a new one
               updateTrains(newtrain);
           } else {
               // Otherwise, add the new train to the list
               TrainList.add(newtrain);
               saveTrainListToFile();
           }
    }

    public void updateTrains(Train updatedTrain) throws IOException {
        OptionalInt index = IntStream.range(0, TrainList.size())
                .filter(i -> TrainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                .findFirst();

        if (index.isPresent()) {
            // If found, replace the existing train with the updated one
            TrainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        } else {
            // If not found, treat it as adding a new train
            addTrain(updatedTrain);
        }
    }



    private void saveTrainListToFile() {
        try {
            objectMapper.writeValue(new File(TRAIN_PATH), TrainList);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your application's requirements
        }
    }
}



