package Client.InitConnection; /**
*@author Karol Melańczuk, numer albumu 254612
*/
import java.awt.*;

/**
 * 
 * Przy pomocy tego obsługujemy rozkład GridBagLayout.
 *
 */
public class GBC extends GridBagConstraints {
	
        public GBC (int gridx, int dridy)
        {
                this.gridx=gridx;
                this.gridy=gridy;
        }
        public GBC(int gridx,int gridy,int gridwidth,int gridheight)
        {
                this.gridx=gridx;
                this.gridy=gridy;
                this.gridwidth=gridwidth;
                this.gridheight = gridheight;
        }
        /**
         * 
         * @param anchor Sposób w jaki komponent ma zająć dostępne miejsce.
         * @return Rozkład zmieniony o sposób układania się w dostępnym miejscu.
         */
        public GBC setAnchor(int anchor)
        {
        		this.anchor=anchor;
                return this;
        }
        /**
         * 
         * @param fill Sposób w jaki komponent ma wypełniać dostępne miejsce.
         * @return Rozkład zmieniony o sposób wypełniania dostępnego miejsca.
         */
        public GBC setFill(int fill)
        {
                this.fill=fill;
                return this;
        }
        
        /**
         * Ustanawia wartosci weightx oraz weighty dla komponentu.
         * @param weightx Określa w jaki sposób komponent ma dzielić się dostępną przestrzeń w wierszu.
         * @param weighty Określa w jaki sposób komponent ma dzielić się dostępną przestrzenią w kolumnie
         * @return Rozkład ze zmienionymi parametrami określajacymi sposób podziału dostępnej przestrzeni z innymi komponentami
         */
        public GBC setWeight(double weightx,double weighty)
        {
                this.weightx=weightx;
                this.weighty=weighty;
                return this;
        }
}       


