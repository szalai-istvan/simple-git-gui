package classes.util;

import classes.commands.Command;
import classes.commands.ProcessResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static classes.commands.Command.inRepository;

public class Commit {
    public final String message;
    public final String id;
    public final String date;

    public Commit(String id, String date, String message) {
        this.message = message;
        this.id = id;
        this.date = date;
    }

    @Override
    public String toString() {
        if (message.length() < 69) return message;
        return message.substring(0, 69) + "...";
    }

    public static Commit[] getCommits() {
        ProcessResult processResult = inRepository().gitLog();
        if (!processResult.isSuccessful()) {
            return new Commit[]{};
        }
        List<Commit> commits = new ArrayList<>();
        Iterator<String> iterator = processResult.getOutput().iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.startsWith("commit")) {
                String id = line.split(" ")[1];

                String date = "";
                while (iterator.hasNext()) {
                    date = iterator.next();
                    if (date.startsWith("Date")) {
                        break;
                    }
                }
                date = date.substring(6).trim();
                while (iterator.hasNext() && !iterator.next().equals(""));
                String comment = iterator.next();
                commits.add(new Commit(id, date, comment));
            }
        }
        return commits.toArray(new Commit[commits.size()]);

    }
}
