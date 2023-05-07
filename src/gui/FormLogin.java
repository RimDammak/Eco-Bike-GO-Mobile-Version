/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author houss
 */
public class FormLogin extends Form{
    public FormLogin(){
        Toolbar tb = getToolbar();
        tb.setUIID("aaa");
        setUIID("LoginForm");
        setLayout(new FlowLayout(CENTER, BOTTOM));
        Button btnLogin = new Button("Bienvenue");
        btnLogin.setUIID("LoginButton");
        Container cn = new Container(BoxLayout.y());
        btnLogin.addActionListener(e->{
            new FormAuth(this).show();
        });
        cn.addAll(btnLogin);
        add(cn);
    }
}
