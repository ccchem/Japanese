package tt;

import java.awt.GraphicsEnvironment;

public class KanjiTest1
{

    public static void main(String[] args)
    {
        String str = "⺣";
        //System.out.println(str.codePointAt(0));

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String name: fonts)
        {
            System.out.println(name);
        }
    }

}
