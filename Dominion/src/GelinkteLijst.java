/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Dirk.Vandycke
 */
public class GelinkteLijst
{
    private Knoop eerste;
    private Knoop laatste;
    
    public boolean isLeeg()
    {
        return eerste == null;
    }
    
    public void voegToe(int data)
    {
        Knoop k = new Knoop(null, data, eerste);
        if(isLeeg()) laatste = k;
        else eerste.zetVorige(k);
        eerste =  k;
    }
    
    public void voegAchteraanToe(int data)
    {
        if(isLeeg())
            voegToe(data);
        else
        {
            Knoop k = new Knoop(laatste, data, null);
            laatste.zetVolgende(k);
            laatste = k;
        }
    }
    
    public String geefUzelfAlsString(boolean omgekeerd)
    {
        String res = "";
        GelinkteLijstFiets fiets;
        if (omgekeerd)
            fiets = new GelinkteLijstFiets(eerste, omgekeerd);
        else
            fiets = new GelinkteLijstFiets(laatste, omgekeerd);
        while(fiets.heeftEenVolgende())
        {
            res += fiets.geefData() + " ";
            fiets.gaNaarVolgende();
        }
        res += fiets.geefData() + " ";
        return res;
    }
  
    public int geefNdeWaarde(int n)
    {
        GelinkteLijstFiets fiets = new GelinkteLijstFiets(eerste, true);
        for (int i = 1; i <= n ; i++)
            fiets.gaNaarVolgende();
        return fiets.geefData();
    }
}
