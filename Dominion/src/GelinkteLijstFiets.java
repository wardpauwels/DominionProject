/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Dirk.Vandycke
 */
public class GelinkteLijstFiets {
    private Knoop huidige;
    private boolean vooruit;

    public GelinkteLijstFiets(Knoop huidige, boolean vooruit) {
        this.huidige = huidige;
        this.vooruit = vooruit;//false
    }

    public int geefData() {
        return huidige.geefData();
    }

    public boolean heeftEenVolgende() {
        if (vooruit)
            return huidige.geefVolgende() != null;
        else
            return huidige.geefVorige() != null;
    }

    public void gaNaarVolgende() {
        if (vooruit) {
            if (heeftEenVolgende())
                huidige = huidige.geefVolgende();
        } else if (heeftEenVorige())
            huidige = huidige.geefVorige();
    }

    public boolean heeftEenVorige() {
        if (vooruit)
            return huidige.geefVorige() != null;
        else
            return huidige.geefVolgende() != null;
    }

    public void gaNaarVorige() {
        if (vooruit) {
            if (heeftEenVorige())
                huidige = huidige.geefVorige();
        } else if (heeftEenVolgende())
            huidige = huidige.geefVolgende();

    }
}