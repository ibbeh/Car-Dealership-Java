import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

 /**
 *
 * @author Ibraheem Refai
 */
public class CarGUI extends JFrame implements ActionListener {

    
    private JLabel labelTitle, labelHeader, labelMake, labelModel, labelYear, labelPrice, labelKM, labelSort, labelSale; //Jlabels for displaying Text or Images
    private JTextField textFieldMake, textFieldModel, textFieldYear, textFieldPrice, textFieldKM; //for user input
    private JButton addBtn, sortMakeBtn, sortYearBtn, sortPriceBtn, viewCarsBtn; //button to click after user input, to save info
    private Font f = new Font("SansSerif", Font.BOLD, 12);
		private Font l = new Font("SansSerif", Font.BOLD, 24);
    //private Book[] books = new Book[50]; //make an array of your objects for your database
		Car[] carArray = new Car[50];

    public CarGUI() {
        setSize(800, 800);//size in pixels of the window
        setLayout(null); //removes default layout manager (box)
        Container c = getContentPane(); //instead of a JPanel - easier to set coordinated of labels, textFields and buttons
        c.setBackground(new Color(255, 51, 51, 155));//set background colour in r,b,g, alpha where alpha is opacity (100 = solid colour)

        labelTitle = new JLabel("Ibraheem's Dealership");//initialized Jlabel
        labelTitle.setFont(l);
        labelTitle.setBounds(250, 0, 400, 40); //at point (10, 200) with width 200 and height 40
        c.add(labelTitle);

				labelHeader = new JLabel("Add a new car:");
        labelHeader.setFont(f);
        labelHeader.setBounds(100, 100, 400, 40); 
        c.add(labelHeader);

				labelMake = new JLabel("Make:");
        labelMake.setFont(f);
        labelMake.setBounds(15, 150, 400, 40);
        c.add(labelMake);

				labelModel = new JLabel("Model:");
        labelModel.setFont(f);
        labelModel.setBounds(15, 200, 400, 40);
        c.add(labelModel);

				labelYear = new JLabel("Year:");
        labelYear.setFont(f);
        labelYear.setBounds(15, 250, 400, 40); 
        c.add(labelYear);

				labelPrice = new JLabel("Price:");
        labelPrice.setFont(f);
        labelPrice.setBounds(15, 300, 400, 40);
        c.add(labelPrice);

				labelKM = new JLabel("KM:");
        labelKM.setFont(f);
        labelKM.setBounds(15, 350, 400, 40); 
        c.add(labelKM);

				labelSort = new JLabel("Sort By:");
        labelSort.setFont(f);
        labelSort.setBounds(600, 300, 400, 40); 
        c.add(labelSort);

        textFieldMake = new JTextField("");
        textFieldMake.setFont(f);
        textFieldMake.setBounds(100, 160, 80, 20);
        c.add(textFieldMake);

				textFieldModel = new JTextField("");
        textFieldModel.setFont(f);
        textFieldModel.setBounds(100, 210, 80, 20);
        c.add(textFieldModel);

				textFieldYear = new JTextField("");
        textFieldYear.setFont(f);
        textFieldYear.setBounds(100, 260, 80, 20);
        c.add(textFieldYear);

				textFieldPrice = new JTextField("");
        textFieldPrice.setFont(f);
        textFieldPrice.setBounds(100, 310, 80, 20);
        c.add(textFieldPrice);

				textFieldKM = new JTextField("");
        textFieldKM.setFont(f);
        textFieldKM.setBounds(100, 360, 80, 20);
        c.add(textFieldKM);

        addBtn = new JButton("ADD");
        addBtn.setBounds(100, 400, 120, 40);
        addBtn.setFont(f);
        addBtn.addActionListener(this);
        c.add(addBtn);

				viewCarsBtn = new JButton("View Cars Available for Sale");
        viewCarsBtn.setBounds(230, 100, 400, 40);
        viewCarsBtn.setFont(f);
        viewCarsBtn.addActionListener(this);
        c.add(viewCarsBtn);

				sortMakeBtn = new JButton("Make");
        sortMakeBtn.setBounds(600, 350, 120, 40);
        sortMakeBtn.setFont(f);
        sortMakeBtn.addActionListener(this);
        c.add(sortMakeBtn);

				sortYearBtn = new JButton("Year");
        sortYearBtn.setBounds(600, 400, 120, 40);
        sortYearBtn.setFont(f);
        sortYearBtn.addActionListener(this);
        c.add(sortYearBtn);

				sortPriceBtn = new JButton("Price");
        sortPriceBtn.setBounds(600, 450, 120, 40);
        sortPriceBtn.setFont(f);
        sortPriceBtn.addActionListener(this);
        c.add(sortPriceBtn);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

				readFromFile();
				fillArray();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

			if(e.getSource() == sortMakeBtn)
			{
				sortByMake();
				readFromArray();
			}

			if(e.getSource() == sortYearBtn)
			{
				sortByYear();
				readFromArray();
			}

      if(e.getSource() == sortPriceBtn)
			{
				sortByPrice();
				readFromArray();
			}



			if(e.getSource() == viewCarsBtn)
			{
				readFromFile();
			}

        if (e.getSource() == addBtn) {
            String ma = textFieldMake.getText();
						String mo = textFieldModel.getText();
						int ye = Integer.parseInt(textFieldYear.getText());
						int pr = Integer.parseInt(textFieldPrice.getText());
						int km = Integer.parseInt(textFieldKM.getText());

						textFieldMake.setText("");
						textFieldModel.setText("");
						textFieldYear.setText("");
						textFieldPrice.setText("");
						textFieldKM.setText("");

						Car car1 = new Car(ma, mo, ye, km, pr);

						carArray[Car.numCars-1] = car1;


            //System.out.println(t); temporary - to check if we got the info from the textField
            //int c = Integer.parseInt(textField3.getText()); //if you need to change String to integer, use parseInt(String)

						//textFieldMake, textFieldModel, textFieldYear, textFieldPrice, textFieldKM;

						try
						{
							FileWriter fw = new FileWriter("CarList.txt", true);
							PrintWriter pw = new PrintWriter(fw);
							pw.append("\n");
							pw.append(car1.toString());
							pw.close();

						}
						catch(IOException ex)
						{
							System.out.println("Error writing to text file.");
						}


        }
    }



		public void readFromFile()
		{
			String list = "";
			try
			{
				FileReader fr = new FileReader("CarList.txt");
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while(line != null)
				{
					list = list+line+"\n";
					line = br.readLine();
				}
			}
			catch(IOException ex)
			{
				System.out.println("Error reading from text file.");
			}
			JOptionPane.showMessageDialog(this, list, "Cars Available For Sale", JOptionPane.PLAIN_MESSAGE);
		}


		public void readFromArray()
		{
			String list = "";

				for(int i = 0; i < Car.numCars; i++)
				{
					list = list+carArray[i]+"\n";
				}
			JOptionPane.showMessageDialog(this, list, "Cars Available For Sale", JOptionPane.PLAIN_MESSAGE);
		}

		public void fillArray()
		{
			try
			{
				FileReader fr = new FileReader("CarList.txt");
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				
				while(line != null && !line.equals(""))
				{
					Car car1 = new Car(line);
					carArray[Car.numCars-1] = car1;
					line = br.readLine();
				}
			}
			catch(IOException ex)
			{
				System.out.println("Error reading from text file.");
			}
		
		}


		public void sortByMake() 
		{
        Car temp;
				System.out.println("numCars = "+Car.numCars);
				System.out.println("carArray = "+carArray.length);
        for (int i = Car.numCars; i >= 0; i--)
				 {
            for (int j = 0; j < i - 1; j++) 
						{
                if (carArray[j] != null && carArray[j + 1] != null) 
								{
                    if (carArray[j].getMake().compareTo(carArray[j + 1].getMake()) > 0) 
										{   
                        temp = carArray[j];
                        carArray[j] = carArray[j + 1];
                        carArray[j + 1] = temp;
                    }
                }
            }
        }
    }


		public void sortByYear() 
		{
        Car temp;
        for (int i = Car.numCars; i >= 0; i--)
				 {
            for (int j = 0; j < i - 1; j++) 
						{
                if (carArray[j] != null && carArray[j + 1] != null) 
								{
                    if (carArray[j].getYear() > carArray[j + 1].getYear()) 
										{   
                        temp = carArray[j];
                        carArray[j] = carArray[j + 1];
                        carArray[j + 1] = temp;
                    }
                }
            }
        }
    }


		public void sortByPrice() 
		{
        Car temp;
        for (int i = Car.numCars; i >= 0; i--)
				 {
            for (int j = 0; j < i - 1; j++) 
						{
                if (carArray[j] != null && carArray[j + 1] != null) 
								{
                    if (carArray[j].getPrice() > carArray[j + 1].getPrice()) 
										{   
                        temp = carArray[j];
                        carArray[j] = carArray[j + 1];
                        carArray[j + 1] = temp;
                    }
                }
            }
        }
    }


}