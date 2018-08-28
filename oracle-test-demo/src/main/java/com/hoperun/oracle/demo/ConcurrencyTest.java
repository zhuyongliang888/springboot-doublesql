package com.hoperun.oracle.demo;

public class ConcurrencyTest
{
    private static final long count = 100000000l;

    public static void main(String[] args) throws InterruptedException
    {
	concurrency();
	serial();

    }

    private static void concurrency() throws InterruptedException
    {
	long start = System.currentTimeMillis();
	Thread thread = new Thread(new Runnable()
	{
	    @Override
	    public void run()
	    {
		int a = 0;
		for (long i = 0; i < count; i++)
		{
		    a += 5;
		    if(i==count-1) {
			System.out.println("a="+a);
		    }
		}
	    }
	});
	thread.start();
	System.out.println("thread :" +thread.isAlive());
	int b = 0;
	for (long i = 0; i < count; i++)
	{
	    b--;
	}
	System.out.println("thread :" +thread.isAlive());
	if(thread.isAlive()) {
	    System.out.println("thread.join ");
		thread.join();
	}

	long time = System.currentTimeMillis() - start;
	System.out.println("thread :" +thread.isAlive());
	System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial()
    {
	long start = System.currentTimeMillis();
	int a = 0;
	for (long i = 0; i < count; i++)
	{
	    a += 5;
	}
	int b = 0;
	for (long i = 0; i < count; i++)
	{
	    b--;
	}
	long time = System.currentTimeMillis() - start;
	System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
