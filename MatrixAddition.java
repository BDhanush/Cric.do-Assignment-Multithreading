import java.util.Random;

class MatrixAdditionMulti implements Runnable {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixC;
    int row;
    int col;
    int total;
    int threadId;
    int step;

    int getRow(int i)
    {
        return i/col;
    }
    int getCol(int i)
    {
        return i%col;
    }

    public MatrixAdditionMulti(int[][] matrixA, int[][] matrixB, int[][] matrixC, int row, int col,int i,int step) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.row = row;
        this.col = col;
        this.total=row*col;
        this.threadId=i;
        this.step=step;
    }

    @Override
    public void run() {
        if(threadId>=total)
        {
            return;
        }
        for(int i=threadId;i<total;i+=step)
        {
            int r=getRow(i),c=getCol(i);
            matrixC[r][c]=matrixA[r][c]+matrixB[r][c];
            // System.out.println("matrixC["+r+"]["+c+"] = "+matrixC[r][c]);
        }
    }
}

public class MatrixAddition {

    public static void main(String[] args) {
        int row=10000,col=10000;
        int[][] matrixA = new int[row][col];
        int[][] matrixB = new int[row][col];
        int[][] matrixC = new int[row][col];

        Random rand = new Random();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrixA[i][j] = rand.nextInt(5);
                matrixB[i][j] = rand.nextInt(5);
            }
        }
        // System.out.println("matrixA");

        // for (int i = 0; i < row; i++) 
        // {
        //     for (int j = 0; j < col; j++) 
        //     {
        //         System.out.print(matrixA[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("matrixB");

        // for (int i = 0; i < row; i++) 
        // {
        //     for (int j = 0; j < col; j++) 
        //     {
        //         System.out.print(matrixB[i][j]+" ");
        //     }
        //     System.out.println();
        // }

        int n = 6;
        Thread threadAr[]=new Thread[n];
        long startTime=System.currentTimeMillis();
        System.out.println("Start time optimal:" + startTime);
        for (int i = 0; i < n; i++) 
        {
            threadAr[i] = new Thread(new MatrixAdditionMulti(matrixA,matrixB,matrixC,row,col,i,n));
            threadAr[i].start();
        
        }
        
        for(int i=0;i<n;i++)
        {
            try{
                threadAr[i].join();
            }catch(Exception e)
            {
                System.out.println("Exception");
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("End time optimal:" + endTime);
        long delta=endTime-startTime;
        System.out.println("Delta:" + delta);

        // System.out.println("matrixC");

        // for (int i=0;i<row;i++) 
        // {
        //     for (int j=0;j<col;j++) 
        //     {
        //         System.out.print(matrixC[i][j]+" ");
        //     }
        //     System.out.println();
        // }

    }
}
