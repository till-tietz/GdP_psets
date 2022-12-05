import gdp.stdlib.StdDraw;
import gdp.stdlib.StdIn;

public class GameOfLife{

    //method: draw grid + cells 
    private static void draw_grid(int[][] grid, int x_axis, int y_axis){
        double x_axis_d = x_axis;
        double y_axis_d = y_axis;
        //draw cells
        for(int y = 0; y < y_axis; y++){
            for(int x = 0; x < x_axis; x++){
                if(grid[y][x] == 1){
                    StdDraw.setPenColor(StdDraw.GREEN);
                    StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                } else {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.square(x + 0.5, y + 0.5, 0.5);
                }
            }
        }

        //draw grid cols
        for(int i = 0; i < x_axis + 1; i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line((double) i, 0, (double) i, y_axis_d);
        }
        //draw grid rows 
        for(int i = 0; i < y_axis + 1; i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(0, (double) i, x_axis_d, (double) i);
        }
    }

    //method: living neighbours
    private static int living_neighbours(int[][] grid,int x_axis, int y_axis, int x, int y){
        int n_alive = 0;

        for(int i = -1; i <= 1; i++){
            //adjust values when cell is on edge 
            int yn = y + i;
            if(yn >= y_axis){
                yn = 0;
            } else if(yn < 0){
                yn = y_axis - 1;
            } 

            for(int j = -1; j <= 1; j++){
                //adjust values when cell is on edge
                int xn = x + j;
                if(xn >= x_axis){
                    xn = 0;
                } else if(xn < 0){
                    xn = x_axis - 1;
                } 
                n_alive += grid[yn][xn];
            }
        }
        //subtract self count
        if(grid[y][x] == 1){
            n_alive -= 1;
        }
        return n_alive;
    }

    //method: next generation
    private static int[][] next_gen(int[][] grid, int x_axis, int y_axis){
        int[][] grid_next = new int[y_axis][x_axis];

        for(int i = 0; i < y_axis; i++){
            for(int j = 0; j < x_axis; j++){
                //number of living neighbours
                int n_alive_neighbours = living_neighbours(grid, x_axis, y_axis, j, i);
                //re-production rules 
                if((grid[i][j] == 0) && (n_alive_neighbours == 3)){
                    grid_next[i][j] = 1;
                } else if((grid[i][j]) == 1 && (n_alive_neighbours == 2)) {
                    grid_next[i][j] = 1;
                } else if((grid[i][j] == 1) && (n_alive_neighbours == 3)) {
                    grid_next[i][j] = 1;
                } else {
                    grid_next[i][j] = 0;
                }
            }
        }
        return grid_next;
    }

    //method: main
    public static void main(String[] args){
        //read grid data
        int x_axis = StdIn.readInt();
        int y_axis = StdIn.readInt();
        int n_cells = StdIn.readInt();

        //create empty grid
        int[][] grid = new int[y_axis][x_axis];
        //read cell positions
        for(int i = 0; i < n_cells; i++){
            int x_position = StdIn.readInt();
            int y_position = StdIn.readInt();
            grid[y_position][x_position] = 1;
        }

        //set scale
        StdDraw.setXscale(0, (double) x_axis + 1);
        StdDraw.setYscale(0, (double) y_axis + 1);

        //draw generations in infinite loop 
        while(true){
            StdDraw.clear();
            draw_grid(grid, x_axis, y_axis);
            StdDraw.show(200);
            grid = next_gen(grid,x_axis,y_axis);  
        }     
    } 

}