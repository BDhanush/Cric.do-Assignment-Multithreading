
class Counter{
    int count=0;
    public synchronized void increment()
    {
        count++;
    }
}

class MultiThreadCounter implements Runnable
{
    Counter c;
    MultiThreadCounter(Counter counter)
    {
        this.c=counter;
    }
    MultiThreadCounter()
    {
        this.c=new Counter();
    }
    public void run()
    {
        try  
        {
            for(int i=0;i<100000;i++)
            {
                c.increment();
            }
        }
        catch(Exception e)
        {
            System.out.println("exception");
        }
    }	
}


public class FunctionalCounter 
{
    public static void main(String[] args)
	{
        Counter c=new Counter();
        Thread thread1 = new Thread(new MultiThreadCounter(c));
        Thread thread2 = new Thread(new MultiThreadCounter(c));
        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch(Exception e)
        {
            System.out.println("exception");
        }
        System.out.println("Expected count:" + 2*100000);
        System.out.println("Actual count:" +c.count);

    }
}
