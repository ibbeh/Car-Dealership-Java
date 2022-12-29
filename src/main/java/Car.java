 /**
 *
 * @author Ibraheem Refai
 */
public class Car{

	private String make;
	private String model;
	private int year;
	private int kilometres;
	private int price;
	public static int numCars = 0;


	public Car(String ma, String mo, int y, int p)
	{
		make = ma;
		model = mo;
		year = y;
		price = p;
		kilometres = 0;
		numCars++;
	}

	public Car(String ma, String mo, int y, int k, int p)
	{
		this(ma, mo, y, p);

		kilometres = k;
	}

	public Car(String line)
	{
		numCars++;
		String[] parts = line.split(", ");
		make = parts[0];
		model = parts[1];;
        try{
						year = Integer.parseInt(parts[2]);
		        price = Integer.parseInt(parts[3]);
	        	kilometres = Integer.parseInt(parts[4]);
           }
        catch (NumberFormatException ex)
				{
            System.out.print("Error parsing int: "+ex);
        }

	}

	public String getMake()
	{
		return make;
	}

	public String getModel()
	{
		return model;
	}

	public int getYear()
	{
		return year;
	}

	public int getKilometres()
	{
		return kilometres;
	}

	public int getPrice()
	{
		return price;
	}

	public void setKilometres(int k)
	{
		if (k > 0){
			kilometres = k;
		}
	}

	public void setPrice(int p)
	{
		if(p > 0)
		{
			price = p;
		}
	}

	public boolean isNew()
	{
		if(kilometres < 100){
			return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return make+", "+model+", "+year+", "+price+", "+kilometres;
	}
  
	@Override
public boolean equals(Object o){
        if(o instanceof Car){
            Car c = ((Car)o);
            if(make.equals(c.getMake()) && model.equals(c.getModel()) && year == (c.getYear())){
                return true;
            }
        }
        return false;
    }

	}