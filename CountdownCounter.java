class Counter{
    int count=0;
    Counter(int n)
    {
        this.count=n;
    }
    public void decrement()
    {
        count--;
    }
}

public class CountdownCounter 
{
    public static void main(String[] args)
	{
        Counter c = new Counter(100);
        for(int i=0;i<100;i++)
        {
            c.decrement();
            System.out.println(c.count);
        }
        
    }
    
}
