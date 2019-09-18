package b3yond.common;

/**
 * Clock.java
 * @author Aaron Ng
 * @date Dec 11, 2017
 * Clock class keeps track of elapsed time; used to regulate movement and display fps
 */
public class Clock {

	long timeElapsed; // Keeps track of elapsed time, for movement
	long timeLastCheck; // Remembers previous instance of time check, for movement
	String frameRate;
	long fpsDeltaTime; // Keeps track of elapsed time, for fps
	long fpsLastTimeCheck; // Remembers previous instance of time check, for fps
	int fpsFrameCount; // Keeps track of number of frames

	/**
	 * Clock
	 * Constructor for clock
	 * @param none
	 * @return none
	 */
	public Clock(){
		timeElapsed = 0;
		timeLastCheck = System.nanoTime();
		fpsFrameCount = 0;
		frameRate = "0 fps";
	} // End clock constructor

	/**
	 * update
	 * Refreshes a new value of elapsed time, depending on time passed since last method call
	 * Used for movement calculations
	 * @param none
	 * @return none
	 */
	public void update() {
		long timeCurrent = System.nanoTime();
		timeElapsed = timeCurrent - timeLastCheck;
		timeLastCheck = timeCurrent;
	} // End update method

	/**
	 * getElapsedTime
	 * Returns elapsed time since last call, in seconds
	 * Used for movement calculations
	 * @param none
	 * @return double timeElapsed
	 */
	public double getElapsedTime() {
		return timeElapsed/1.0E9;
	} // End getElapsedTime method
	
	/**
	 * getFrameRate
	 * Returns a string indicating JPanel update speed (fps)
	 * @param none
	 * @return String fps
	 */
	public String getFrameRate() {
		long fpsCurrentTime = System.currentTimeMillis();
		fpsDeltaTime += fpsCurrentTime - fpsLastTimeCheck;
		fpsLastTimeCheck = fpsCurrentTime;
		fpsFrameCount++;
		if (fpsDeltaTime > 1000) {
			frameRate = fpsFrameCount + " fps";
			fpsFrameCount = 0;
			fpsDeltaTime = 0;
		} // End if
		return frameRate;
	} // End getFrameRate method

} // End Clock class
