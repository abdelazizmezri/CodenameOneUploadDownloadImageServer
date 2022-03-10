/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.tools.Statics;
import java.io.IOException;

/**
 *
 * @author abdelazizmezri
 */
public class ShowForm extends Form {

    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    public ShowForm(Form previous) {
        super("Download Image", BoxLayout.y());

        TextField tfImage = new TextField("", "Image name");
        Button btDownload = new Button("Download");

        try {
            imgv = new ImageViewer(Image.createImage("/load.png"));
        } catch (IOException ex) {
            Dialog.show("Error", ex.getMessage(), "Ok", null);
        }

        btDownload.addActionListener((evt) -> {
            if (!"".equals(tfImage.getText())) {
                try {
                    enc = EncodedImage.create("/load.png");
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "Ok", null);
                }
                String url = Statics.URL_REP_IMAGES + tfImage.getText() + ".jpg";
                imgs = URLImage.createToStorage(enc, tfImage.getText(), url, URLImage.RESIZE_SCALE);
                imgv.setImage(imgs);
            }
        });

        addAll(imgv, tfImage, btDownload);

        getToolbar().addCommandToLeftBar("Back", null, (evt) -> {
            previous.showBack();
        });
    }

}
