class Counter{
    int count=0;
    public void increment()
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
            c.increment();
            System.out.println(c.count);
        }
        catch(Exception e)
        {
            System.out.println("exception");
        }
    }	
}

class ThreadNumbers {

	public static void main(String[] args)
	{
		int n = 8; 
        Counter c=new Counter();
        for (int i = 0; i < n; i++) {
                
            Thread thread = new Thread(new PrintNumbers(c));
            thread.start();
        }
	}
}

