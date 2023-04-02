package classes.commands;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static classes.util.Properties.*;

public class Command {
    private final ProcessBuilder processBuilder;
    private final Path path;

    private Command(String directory) {
        this.path = Path.of(directory);
        this.processBuilder = new ProcessBuilder().directory(new File(directory));
    }

    public static Command atWorkDirectory() {
        return new Command(workFolder());
    }

    public static Command inRepository() {
        return new Command(Path.of(workFolder(), repositoryName()).toString());
    }

    public static Command atAbsolutePath(Path path) {
        return new Command(path.toString());
    }

    public static Command relativeToWorkDirectory(String... folders) {
        Path path = Path.of(workFolder());
        for (String folder : folders) {
            path = path.resolve(folder);
        }
        return new Command(path.toString());
    }

    public ProcessResult gitClone() {
        return runProcess("git clone " + authenticatedRepoUrl());
    }

    public ProcessResult gitFetch() {
        return runProcess("git fetch " + authenticatedRepoUrl());
    }

    public ProcessResult commitAndPush(String commitMessage) {
        List<String> prompts = List.of(
                "git stage",
                "git add .",
                "git commit -m <MESSAGE>".replace("<MESSAGE>", commitMessage.replace(" ", "_")),
                "git push " + authenticatedRepoUrl()
        );
        List<String> output = new ArrayList<>();
        for (String prompt : prompts) {
            ProcessResult result = runProcess(prompt);
            if (!result.isSuccessful()) {
                return ProcessResult.ofFailure(List.of(prompt));
            }
            output.addAll(result.getOutput());
        }
        return ProcessResult.ofSuccess(output);
    }

    public ProcessResult gitLog() {
        return runProcess("git log");
    }

    public ProcessResult gitCheckout(String id) {
        return runProcess("git checkout " + id);
    }

    public ProcessResult gitStatus() {
        return runProcess("git status");
    }

    private ProcessResult runProcess(String prompt) {
        if (!validatePath()) {
            return ProcessResult.ofFailure(List.of());
        }
        try {
            Process process = this.processBuilder
                    .command(prompt.split(" "))
                    .start();

            List<String> fullOutput = process.inputReader().lines()
                    .collect(Collectors.toList());
            return ProcessResult.ofSuccess(fullOutput);
        } catch (Throwable e) {
            return ProcessResult.ofFailure(Collections.emptyList());
        }
    }

    private boolean validatePath() {
        return path.toFile().exists();
    }
}
