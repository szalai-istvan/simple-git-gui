package classes.commands;

import java.util.Collections;
import java.util.List;

public class ProcessResult {
    private final List<String> output;
    private final boolean success;

    ProcessResult(List<String> output, boolean success) {
        this.output = output;
        this.success = success;
    }
    public static ProcessResult ofSuccess(List<String> output) {
        return new ProcessResult(output, true);
    }

    public static ProcessResult ofFailure(List<String> output) {
        return new ProcessResult(output, false);
    }

    public boolean isSuccessful() {
        return success;
    }
    public List<String> getOutput() {
        return Collections.unmodifiableList(output);
    }
}
