/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.tools.Statics;
import java.io.IOException;

/**
 *
 * @author abdelazizmezri
 */
public class AddForm extends Form {
    
    public AddForm(Form previous) {
        super("Upload Image", BoxLayout.y());
        
        TextField tfImageName = new TextField("", "Image name");
        Button btnUpload = new Button("Upload");
        btnUpload.addActionListener((evt) -> {
            if (!"".equals(tfImageName.getText())) {
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                
                cr.setUrl(Statics.URL_UPLOAD);
                cr.setPost(true);
                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
                cr.setFilename("file", tfImageName.getText() + ".jpg");//any unique name you want

                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                Dialog.show("Success", "Image uploaded", "OK", null);
            }else{
                Dialog.show("Error", "Invalid image name", "Ok", null);
            }
        });
        add(tfImageName);
        add(btnUpload);
        
        getToolbar().addCommandToLeftBar("Back", null, (evt) -> {
            previous.showBack();
        });
    }

}
