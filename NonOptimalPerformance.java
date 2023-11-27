
class Counter{
    int count=0;
    public synchronized void increment()
    {
        count++;
    }
}

class PrintNumbers extends Thread
{
    Counter c;
    PrintNumbers(Counter counter)
    {
        this.c=counter;
    }
    PrintNumbers()
    {
        this.c=new Counter();
    }
    public void run()
    {
        try  
        {
            for(int i=0;i<100;i++)
            {
                c.increment();
                
                Thread.sleep(1000);  //to simulate some computation
            }
        }
        catch(Exception e)
        {
            System.out.println("exception");
        }
    }	
}

public class NonOptimalPerformance 
{
    public static void main(String[] args)
	{
		int n = 16; 
        Counter c=new Counter();
        for (int i = 0; i < n; i++) {
                
            Thread thread = new Thread(new PrintNumbers(c));
            thread.start();
        }
	}
}
