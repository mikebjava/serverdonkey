package com.michaeljustin.servertest;

import java.util.ArrayList;

public final class Main
{

	private static ArrayList<ISubClass> subClasses = new ArrayList<ISubClass>();

	public static void main(String[] args)
	{
		if (subClasses.size() > 0)
		{
			for (int i = 0; i < subClasses.size(); i++)
			{
				if (subClasses instanceof ISubClass)
				{
					subClasses.get(i).preInit();
				}
			}

			for (int i = 0; i < subClasses.size(); i++)
			{
				if (subClasses instanceof ISubClass)
				{
					subClasses.get(i).init();
				}
			}

			for (int i = 0; i < subClasses.size(); i++)
			{
				if (subClasses instanceof ISubClass)
				{
					subClasses.get(i).postInit();
				}
			}
		} else
		{
			System.out.println("No sub classes found to call.");
		}
	}

	public static void addSubClass(ISubClass subclass)
	{
		subClasses.add(subclass);
	}

}
