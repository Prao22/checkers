/**
*@author Karol Melańczuk, numer albumu 254612
*/
import java.awt.*;
/**Pomocnicza klasa pomagająca rozstawić komponenty na ekranie**/
public class GBC extends GridBagConstraints 
{
        public GBC (int gridx, int dridy)//ustalenie w którym wierszy i kolumnie mają znajdować się komponenty
        {
                this.gridx=gridx;
                this.gridy=gridy;
        }
	
        public GBC(int gridx,int gridy,int gridwidth,int gridheight)//ustala omówione w preambule parametry na określoną wartość
        {
                this.gridx=gridx;
                this.gridy=gridy;
                this.gridwidth=gridwidth;
                this.gridheight = gridheight;
        }
        public GBC setAnchor(int anchor)//Metoda ustanawiająca wyrównanie komponentu wewnątrz komórki
        {

		this.anchor=anchor;
                return this;
       }

        public GBC setFill(int fill)//Metoda określajaca w jaki sposób komponent ma zajmować komórkę w której się znajduje
        {
                this.fill=fill;
                return this;
        }
        public GBC setWeight(double weightx,double weighty)//określa parametry weightx i weighty
        {
                this.weightx=weightx;
                this.weighty=weighty;
                return this;
        }
}       


