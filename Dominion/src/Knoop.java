/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Dirk.Vandycke
 */
public class Knoop
{
    private int data;
    private Knoop volgende;
    private Knoop vorige;
    
    public Knoop(Knoop vorige, int data, Knoop volgende)
    {
        this.data = data;
        this.volgende = volgende;
        this.vorige = vorige;
    }
    
    public void zetVolgende(Knoop k)
    {
        volgende = k; 
    }
    
    public void zetVorige(Knoop k)
    {
        vorige = k; 
    }
    
    public Knoop geefVolgende()
    {
        return volgende;
    }

    public Knoop geefVorige()
    {
        return vorige;
    }
        
    public int geefData()
    {
        return data;
    }
}
