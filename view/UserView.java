package view;

import controller.Controller;
import model.Note;
import util.Commands;
import java.util.Scanner;

public class UserView {
    public final Controller controller;

    public UserView(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Enter command: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT)
                return;
            switch (com) {
                case CREATE:
                Note n = createNote();
                controller.saveNote(n);
                    break;
                case READ:
                    String id = prompt("Enter note ID: ");
                    try {
                        Note note = controller.readNote(Long.parseLong(id));
                        System.out.println(note);
                        System.out.println();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String noteID = prompt("Enter note ID: ");
                    controller.updateNote(noteID, createNote());
                    break;
                case DELETE:
                    String noteId = prompt("Enter note ID: ");
                    controller.deleteNote(noteId);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Note createNote() {
        String head = prompt("Header: ");
        String text = prompt("Enter note: ");
        return new Note(head, text);
    }
}
