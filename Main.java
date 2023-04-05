import controller.Controller;
import log.ILogger;
import log.impl.ConsoleLogger;
import model.Note;
import repository.GBRepository;
import repository.impl.NoteRepository;
import view.UserView;

import static util.DBConnector.createDB;


public class Main {
    public static void main(String[] args) {
        createDB();
        GBRepository<Note, Long> repository = new NoteRepository();
        ILogger logger = new ConsoleLogger();
        Controller controller = new Controller(repository, logger);
        UserView view = new UserView(controller);
        view.run();
    }
}
