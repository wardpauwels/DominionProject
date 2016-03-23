/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Dirk.Vandycke
 */
public class GelinkteLijstTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Knoop eenKnoop = new Knoop(13);
        GelinkteLijst gl = new GelinkteLijst();
        gl.voegAchteraanToe(3);
        gl.voegToe(13);
        gl.voegToe(7);
        gl.voegToe(5);
        gl.voegAchteraanToe(17);
        System.out.println(gl.geefUzelfAlsString(false));
        System.out.println(gl.geefUzelfAlsString(true));
        System.out.println(gl.geefNdeWaarde(7));
    }

}
