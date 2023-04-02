package classes.util;

public class Properties {
    public static final String WORK_FOLDER = "WORK_FOLDER";
    public static final String REMOTE_URL = "REMOTE_URL";
    public static final String TOKEN = "TOKEN";
    public static final String USERNAME = "USERNAME";
    public static final String REPOSITORY_NAME = "REPOSITORY_NAME";

    public static String workFolder() {
        return System.getProperty(WORK_FOLDER, "");
    }

    public static String remoteUrl() {
        return System.getProperty(REMOTE_URL, "");
    }

    public static String token() {
        return System.getProperty(TOKEN, "");
    }

    public static String username() {
        return System.getProperty(USERNAME, "");
    }

    public static String repositoryName() {
        return System.getProperty(REPOSITORY_NAME, "");
    }

    public static String authenticatedRepoUrl() {
        return "https://<GITHUB_ACCESS_TOKEN>@github.com/<GITHUB_USERNAME>/<REPOSITORY_NAME>.git"
                .replace("<GITHUB_ACCESS_TOKEN>", token())
                .replace("<GITHUB_USERNAME>", username())
                .replace("<REPOSITORY_NAME>", repositoryName());
    }
}
