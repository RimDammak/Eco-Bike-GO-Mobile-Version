/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author houss
 */
public class FormMesRV extends Form{
    public FormMesRV(){
                    Container contenue=new Container(BoxLayout.y());
        contenue.setUIID("Formma3loumet");
        contenue.add(new Label("Bike SOS\n" +
"EcoBikeGo répare également vos vélos !.\n" +
"\n" +
"1 - Apportez votre vélo dans l'une de nos succursales,\n" +
"2 - Nous effectuons un diagnostic précis des réparations à effectuer,\n" +
"3 - Nous vous envoyons un devis détaillé,\n" +
"4 - Vous récupérez votre vélo en moins d'une semaine !"));
        add(contenue);
    }
}
