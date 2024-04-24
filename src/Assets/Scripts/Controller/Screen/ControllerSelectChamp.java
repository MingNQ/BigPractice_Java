package Controller.Screen;

import View.SelectChamp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class ControllerSelectChamp implements ActionListener{
	private SelectChamp view;

	private ImageIcon character_1 = new ImageIcon("C:\\Users\\Acer\\eclipse-workspace\\Game\\src\\View\\down_1_1.png");
    private ImageIcon character_2 = new ImageIcon("C:\\Users\\Acer\\eclipse-workspace\\Game\\src\\View\\down_2.png");
    private ImageIcon character_3 = new ImageIcon("C:\\Users\\Acer\\eclipse-workspace\\Game\\src\\View\\down_3.png");
    private ImageIcon character_4 = new ImageIcon("C:\\Users\\Acer\\eclipse-workspace\\Game\\src\\View\\down_4.png");
    private ImageIcon character_5 = new ImageIcon("C:\\Users\\Acer\\eclipse-workspace\\Game\\src\\View\\down_5.png");
    
	public ControllerSelectChamp(SelectChamp view)
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if(button.equals(character_1))
		{
		//	this.view.setIconLabel();
		}
		else if(button.equals(character_2))
		{
			//this.view.setIconLabel();
		}
		else if(button.equals(character_3))
		{
			//this.view.setIconLabel();
		}
		else if(button.equals(character_4))
		{
			//this.view.setIconLabel();
		}
		else if(button.equals(character_5))
		{
			//this.view.setIconLabel();
		}
	}
	
}
