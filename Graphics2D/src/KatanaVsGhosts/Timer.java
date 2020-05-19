package KatanaVsGhosts;

public class Timer {
    private int fps = 60;
    private float passedTime = 0;
    private float targetTime = 0;
    private boolean running = false;

    public Timer(int fps){
        this.fps = fps;
    }

    public Timer(){

    }

    public float getPassedTime(){
        return passedTime;
    }

    public void setTimer(float seconds){
        this.targetTime = seconds;
        running = false;
        passedTime = 0;
    }

    public void run(){
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void update(){
        if(running) {
            passedTime = passedTime +  1f/ (float)fps;
            if (passedTime >= targetTime) {
                running = false;

            }
        }
    }
    public void restart(){
        setTimer(targetTime);
    }


    public void stop(){
        running = false;
    }




}
