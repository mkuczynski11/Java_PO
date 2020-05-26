package life.game.my.solution.files.gui;

public class Log
{
    private String logs;

    public Log(){

    }

    public void addLog(String message){
        logs+=message;
    }

    public void clearLogs(){
        logs = "Wydarzenia na planszy podczas trwania tury\n";
    }

    public String getLogs() {
        return logs;
    }
}
