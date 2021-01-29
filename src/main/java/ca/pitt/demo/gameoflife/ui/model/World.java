package ca.pitt.demo.gameoflife.ui.model;

import com.google.gson.Gson;

public class World {
	
	private static final int OFFSET_BEFORE= -1;
	private static final int OFFSET_AFTER = 1;

	private int[][] state;
	
	private int width = 60;
	
	private int height = 40;
	
	private int generation = 0;
	
	public World() {
		state = new int[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				state[i][j] = 0;
			}
		}
	}
	
	public int[][] getState() {
		return state;
	}
	
	public void setState(int[][] aState) {
		this.state = aState;
	}
	
	public void setCell(int x, int y, int value) {
		state[x][y] = value;
	}
	
	public void setLifeform(Lifeform aLifeform) {
		int newState = aLifeform.isAlive() ? 1 : 0;
		state[aLifeform.getX()][aLifeform.getY()] = newState;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public boolean isInBounds(int x, int y) {
		return (x >= 0) && (x < width) && (y >= 0) && (y < height);
	}
	
	public String print() {
		Gson gson = new Gson();
		return gson.toJson(state);
	}
	
	public Lifeform getLifeForm(int x, int y) {
		Lifeform aLifeform = new Lifeform();
		aLifeform.setAlive(1 == state[x][y]);
		aLifeform.setX(x);
		aLifeform.setY(y);
		int count = 0;
	    for (int i = OFFSET_BEFORE; i <= OFFSET_AFTER; i++) {
	    	// nx = neighbour x position
    		int nx = x + i;
	    	for (int j = OFFSET_BEFORE; j <= OFFSET_AFTER; j++) {
	    		// ny = neighbosur y position
	    		int ny = y + j;
	    		if (!((i == 0) && (j == 0))) {
	    			boolean isInBounds = isInBounds(nx, ny);
	    			if (isInBounds) {
	    				if (1 == getState()[nx][ny]) {
	    					count++;
	    				}
	    			}
	    		}
	    	}
	    }
	    aLifeform.setNeighbours(count);
	    return aLifeform;
	}
	
	@Override
	public String toString() {
		return print();
	}
}
