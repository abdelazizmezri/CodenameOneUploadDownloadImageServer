/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author abdelazizmezri
 */
public class MainForm extends Form {

    public MainForm() {
        super("Home",BoxLayout.y());
        
        Button btnAdd = new Button("Upload Image");
        Button btnShow = new Button("Download Image");
        btnAdd.addActionListener((evt) -> {
            AddForm af = new AddForm(this);
            af.show();
        });
        btnShow.addActionListener((evt) -> {
            ShowForm sf = new ShowForm(this);
            sf.show();
        });
        addAll(btnAdd,btnShow);
    }

}
