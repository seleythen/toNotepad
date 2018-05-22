import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Notepad {
    private List<State> states;
    private int currentState;
    private Logger logger;

    public Notepad(String loggerFilename){
        states = new LinkedList<>();
        states.add(new State(""));
        currentState = 0;
        logger = new Logger(loggerFilename);
    }

    public void save(String text) throws IOException{
        while(this.states.size() > currentState){
            this.states.remove(this.currentState);
        }
        states.add(currentState + 1, new State(text));
        currentState++;
        logger.log(states.get(currentState - 1).getDifferences(states.get(currentState)));
    }

    public void undo() {
        if(currentState > 0)
            currentState--;
    }

    public void redo() {
        if(currentState < states.size() - 1)
            currentState++;
    }

    public State getCurrentState(){
        return states.get(currentState);
    }
}
