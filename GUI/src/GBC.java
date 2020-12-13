/**
*@author Karol Melańczuk, numer albumu 254612
*/
import java.awt.*;

public class GBC extends GridBagConstraints 
{
        public GBC (int gridx, int dridy)//ustalenie w ktorym wierszy i kolumnie maja znajdowac sie komponenty
        {
                this.gridx=gridx;
                this.gridy=gridy;
        }
        public GBC(int gridx,int gridy,int gridwidth,int gridheight)//ustala omawione w preambule parametry na okreslone wartosci
        {
                this.gridx=gridx;
                this.gridy=gridy;
                this.gridwidth=gridwidth;
                this.gridheight = gridheight;
        }
        public GBC setAnchor(int anchor)//Metoda ustanawiajaca wyrownanie komponentu wewnatrz komorki
        {

        		this.anchor=anchor;
                return this;
        }
        public GBC setFill(int fill)//Metoda okreslajaca w jaki sposob komponent ma zajmowac komorke w ktorej sie znajduje
        {
                this.fill=fill;
                return this;
        }
        public GBC setWeight(double weightx,double weighty)//okresla parametry weightx i weighty
        {
                this.weightx=weightx;
                this.weighty=weighty;
                return this;
        }
}       


